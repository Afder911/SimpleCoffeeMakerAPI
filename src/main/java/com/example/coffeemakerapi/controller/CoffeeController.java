package com.example.coffeemakerapi.controller;

import com.example.coffeemakerapi.coffeeMakerException.MissingIngredientsException;
import com.example.coffeemakerapi.coffeeMakerException.PollutionException;
import com.example.coffeemakerapi.enums.CoffeeType;
import com.example.coffeemakerapi.model.Coffee;
import com.example.coffeemakerapi.service.CoffeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/coffee")
@Api(description = "Контроллер приготовления кофе")
public class CoffeeController {

    private final CoffeeService coffeeService;

    private Coffee coffee;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/your-coffee")
    @ApiOperation("Приготовить свой вариант кофе")
    public Coffee cookYourCoffee(@RequestParam(name = "Кофейные зёрна, от 1 до 3", defaultValue = "1") @Valid int amountCoffeeBeans,
                                 @RequestParam(name = "Вода, от 1 до 3", defaultValue = "1") @Valid int amountWater,
                                 @RequestParam(name = "Молоко, от 0 до 2", defaultValue = "0") @Valid int amountMilk,
                                 @RequestParam(name = "Кленовый сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupMaple,
                                 @RequestParam(name = "Кокосовый сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupCoconut,
                                 @RequestParam(name = "Миндальный сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupAlmond,
                                 @RequestParam(name = "Ванильный сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupVanilla,
                                 @RequestParam(name = "Сахар,от 0 до 5", defaultValue = "0") @Valid int sugar) {
        try {
            coffee = coffeeService.cookYourCoffeeOption(amountCoffeeBeans, amountWater, amountMilk, amountSyrupMaple,
                                                        amountSyrupCoconut, amountSyrupAlmond, amountSyrupVanilla, sugar);
        } catch (MissingIngredientsException|PollutionException e) {
            System.out.println(e.getMessage());
        }
        return coffee;
    }

    @GetMapping("/ready-coffee")
    @ApiOperation("Выбрать из готовых вариантов")
    public Coffee cookReadyCoffee(@RequestParam(name = "Выберете вариант кофе") @Valid CoffeeType type,
                                  @RequestParam(name = "Кленовый сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupMaple,
                                  @RequestParam(name = "Кокосовый сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupCoconut,
                                  @RequestParam(name = "Миндальный сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupAlmond,
                                  @RequestParam(name = "Ванильный сироп, от 0 до 2", defaultValue = "0") @Valid int amountSyrupVanilla,
                                  @RequestParam(name = "Сахар,от 0 до 5", defaultValue = "0") @Valid int sugar) {
        try {
            coffee = coffeeService.cookReadyCoffee(type, amountSyrupMaple, amountSyrupCoconut, amountSyrupAlmond,
                                                   amountSyrupVanilla, sugar);
        } catch (MissingIngredientsException|PollutionException e) {
            System.out.println(e.getMessage());
        }
        return coffee;
    }

}
