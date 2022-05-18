package com.example.coffeemakerapi.controller;

import com.example.coffeemakerapi.model.Coffee;
import com.example.coffeemakerapi.repo.CoffeeRepo;
import io.swagger.annotations.Api;;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api("Контроллер для получения кофе")
public class CoffeeController {

    @Autowired
    private CoffeeRepo coffeeRepo;

    @GetMapping("/coffee")
    @ApiOperation(value = "Приготовить кофе")
    public Coffee makeCoffee(@RequestParam(defaultValue = "Свой вариант", name = "Введите: Капучино|Латте|Американо|Эспрессо или Свой вариант") String nameCoffee,
                             @RequestParam(defaultValue = "Без молока",  name = "Введите Добавить если хотите с молоком") String milk,
                             @RequestParam(defaultValue = "Без сиропа", name = "Введите: Кленовый|Кокосовый|Миндальный если хотите с сиропом") String syrup,
                             @RequestParam(defaultValue = "Без сахара", name = "Введите Добавить если хотите с сахаром") String sugar) {
        Coffee coffee = new Coffee(nameCoffee, milk, syrup, sugar);
        coffeeRepo.save(coffee);
        return coffee;
    }

}
