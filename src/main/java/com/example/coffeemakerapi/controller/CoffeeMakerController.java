package com.example.coffeemakerapi.controller;

import com.example.coffeemakerapi.model.CoffeeMaker;
import com.example.coffeemakerapi.repo.MakerRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/maker")
@Api(description = "Контроллер управления кофе-машиной")
public class CoffeeMakerController {

    @Autowired
    private MakerRepo makerRepo;


    @PostMapping("/check")
    @ApiOperation("Проверить заполненность кофе-машины")
    public CoffeeMaker checkCoffeeMakerForRefill() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        if(coffeeMaker == null) {
            coffeeMaker = new CoffeeMaker();
            makerRepo.save(coffeeMaker);
        }
        return coffeeMaker;
    }

    @PutMapping("/clear")
    @ApiOperation("Почистить кофе-машину")
    public CoffeeMaker clearCoffeeMaker() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.clear();
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/all")
    @ApiOperation("Полностью заправить кофе-машину")
    public CoffeeMaker refillCoffeeMakerAll() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.refillAll();
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/coffee-beans")
    @ApiOperation("Заправить кофе-машину кофейными зернами")
    public CoffeeMaker refillCoffeeMakerCoffeeBeans() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setCoffeeBeansLevel(100);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/water")
    @ApiOperation("Заправить кофе-машину водой")
    public CoffeeMaker refillCoffeeMakerWater() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setWaterLevel(100);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/milk")
    @ApiOperation("Заправить кофе-машину молоком")
    public CoffeeMaker refillCoffeeMakerMilk() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setMilkLevel(50);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/sugar")
    @ApiOperation("Заправить кофе-машину сахаром")
    public CoffeeMaker refillCoffeeMakerSugar() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setSugarLevel(100);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/syrup-maple")
    @ApiOperation("Заправить кофе-машину кленовым сиропом")
    public CoffeeMaker refillCoffeeMakerSyrupMaple() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setMapleSyrupLevel(15);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/syrup-coconut")
    @ApiOperation("Заправить кофе-машину кокосовым сиропом")
    public CoffeeMaker refillCoffeeMakerSyrupCoconut() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setCoconutSyrupLevel(15);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/syrup-almond")
    @ApiOperation("Заправить кофе-машину миндальным сиропом")
    public CoffeeMaker refillCoffeeMakerSyrupAlmond() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setAlmondSyrupLevel(15);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

    @PutMapping("/refill/syrup-vanilla")
    @ApiOperation("Заправить кофе-машину ванильным сиропом")
    public CoffeeMaker refillCoffeeMakerSyrupVanilla() {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        coffeeMaker.setVanillaSyrupLevel(15);
        coffeeMaker.setDateLastPartialFilling(LocalDateTime.now());
        makerRepo.save(coffeeMaker);
        return coffeeMaker;
    }

}
