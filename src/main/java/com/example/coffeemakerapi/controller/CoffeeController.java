package com.example.coffeemakerapi.controller;

import com.example.coffeemakerapi.coffeeMakerException.MissingIngredientsException;
import com.example.coffeemakerapi.coffeeMakerException.PollutionException;
import com.example.coffeemakerapi.model.Coffee;
import com.example.coffeemakerapi.model.CoffeeMaker;
import com.example.coffeemakerapi.repo.CoffeeRepo;
import com.example.coffeemakerapi.repo.MakerRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/coffee")
@Api(description = "Контроллер приготовления кофе")
public class CoffeeController {

    @Autowired
    private CoffeeRepo coffeeRepo;

    @Autowired
    private MakerRepo makerRepo;

    @GetMapping("/your-coffee")
    @ApiOperation("Приготовить свой вариант кофе")
    public Coffee cookYourCoffee(@RequestParam(name = "Размер порции от 1 до 3", defaultValue = "1") @Valid int amountCoffeeBeans,
                                 @RequestParam(name = "Размер порции от 1 до 3", defaultValue = "1") @Valid int amountWater,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountMilk,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupMaple,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupCoconut,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupAlmond,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupVanilla,
                                 @RequestParam(name = "Размер порции от 0 до 5", defaultValue = "0") @Valid int sugar) throws MissingIngredientsException, PollutionException {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);
        //Проверяем уровень загрязнения кофе-машины
        if(coffeeMaker.getPollutionLevel() == 150) {
            throw new PollutionException("Необходимо выполнить чистку кофе-машины!");
        }
        Coffee otherCoffee = new Coffee();
        otherCoffee.setNameCoffee("Свой вариант");

        //Проверяем хватает ли кофейных зерен
        if(coffeeMaker.getCoffeeBeansLevel() < amountCoffeeBeans) {
            throw new MissingIngredientsException("Не хватает кофейных зерен");
        }
        coffeeMaker.setCoffeeBeansLevel(coffeeMaker.getCoffeeBeansLevel() - amountCoffeeBeans);
        otherCoffee.setCoffeeBeans(amountCoffeeBeans);

        //Проверяем хватает ли воды
        if(coffeeMaker.getWaterLevel() < amountWater) {
            throw new MissingIngredientsException("Не хватает воды");
        }
        coffeeMaker.setWaterLevel(coffeeMaker.getWaterLevel() - amountWater);
        otherCoffee.setWater(amountWater);

        //Проверяем хватает ли молока
        if(coffeeMaker.getMilkLevel() < amountMilk) {
            throw new MissingIngredientsException("Не хватает молока");
        }
        coffeeMaker.setMilkLevel(coffeeMaker.getMilkLevel() - amountMilk);
        otherCoffee.setMilk(amountMilk);

        //Проверяем хватает ли кленового сиропа
        if(coffeeMaker.getMapleSyrupLevel() < amountSyrupMaple) {
            throw new MissingIngredientsException("Не хватает кленового сиропа");
        }
        coffeeMaker.setMapleSyrupLevel(coffeeMaker.getMapleSyrupLevel() - amountSyrupMaple);
        otherCoffee.setAmountSyrupMaple(amountSyrupMaple);

        //Проверяем хватает ли кокосового сиропа
        if(coffeeMaker.getCoconutSyrupLevel() < amountSyrupCoconut) {
            throw new MissingIngredientsException("Не хватает кокосового сиропа");
        }
        coffeeMaker.setCoconutSyrupLevel(coffeeMaker.getCoconutSyrupLevel() - amountSyrupCoconut);
        otherCoffee.setAmountSyrupCoconut(amountSyrupCoconut);

        //Проверяем хватает ли миндального сиропа
        if(coffeeMaker.getAlmondSyrupLevel() < amountSyrupAlmond) {
            throw new MissingIngredientsException("Не хватает миндального сиропа");
        }
        coffeeMaker.setAlmondSyrupLevel(coffeeMaker.getAlmondSyrupLevel() - amountSyrupAlmond);
        otherCoffee.setAmountSyrupAlmond(amountSyrupAlmond);

        //Проверяем хватает ли ванильного сиропа
        if(coffeeMaker.getVanillaSyrupLevel() < amountSyrupVanilla) {
            throw new MissingIngredientsException("Не хватает ванильного сиропа");
        }
        coffeeMaker.setVanillaSyrupLevel(coffeeMaker.getVanillaSyrupLevel() - amountSyrupVanilla);
        otherCoffee.setAmountSyrupVanilla(amountSyrupVanilla);

        //Проверяем хватает ли сахара
        if(coffeeMaker.getSugarLevel() < sugar) {
            throw new MissingIngredientsException("Не хватает сахара");
        }
        coffeeMaker.setSugarLevel(coffeeMaker.getSugarLevel() - sugar);
        otherCoffee.setSugar(sugar);

        otherCoffee.setDateCooking(LocalDateTime.now());
        coffeeMaker.setPollutionLevel(coffeeMaker.getPollutionLevel() + 1);
        makerRepo.save(coffeeMaker);
        coffeeRepo.save(otherCoffee);
        return otherCoffee;
    }

    @GetMapping("/cappuccino")
    @ApiOperation("Приготовить капучино")
    public Coffee cookCappuccino(@RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupMaple,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupCoconut,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupAlmond,
                                 @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupVanilla,
                                 @RequestParam(name = "Размер порции от 0 до 5", defaultValue = "0") @Valid int sugar) throws MissingIngredientsException, PollutionException {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);

        //Проверяем уровень загрязнения кофе-машины
        if(coffeeMaker.getPollutionLevel() == 150) {
            throw new PollutionException("Необходимо выполнить чистку кофе-машины!");
        }
        Coffee cappuccino = new Coffee();
        cappuccino.setNameCoffee("Капучино");

        //Проверям хватает ли основных ингредиентов для приготовления капучино
        if(coffeeMaker.getCoffeeBeansLevel() < 1 || coffeeMaker.getWaterLevel() < 2 || coffeeMaker.getMilkLevel() < 1) {
            throw new MissingIngredientsException("Не хватает ингредиентов для капучино");
        }
            coffeeMaker.setCoffeeBeansLevel(coffeeMaker.getCoffeeBeansLevel() - 1);
            cappuccino.setCoffeeBeans(1);
            coffeeMaker.setWaterLevel(coffeeMaker.getWaterLevel() - 2);
            cappuccino.setWater(2);
            coffeeMaker.setMilkLevel(coffeeMaker.getMilkLevel() - 1);
            cappuccino.setMilk(1);

        //Проверяем хватает ли кленового сиропа
        if(coffeeMaker.getMapleSyrupLevel() < amountSyrupMaple) {
            throw new MissingIngredientsException("Не хватает кленового сиропа");
        }
            coffeeMaker.setMapleSyrupLevel(coffeeMaker.getMapleSyrupLevel() - amountSyrupMaple);
            cappuccino.setAmountSyrupMaple(amountSyrupMaple);

        //Проверяем хватает ли кокосового сиропа
        if(coffeeMaker.getCoconutSyrupLevel() < amountSyrupCoconut) {
            throw new MissingIngredientsException("Не хватает кокосового сиропа");
        }
            coffeeMaker.setCoconutSyrupLevel(coffeeMaker.getCoconutSyrupLevel() - amountSyrupCoconut);
            cappuccino.setAmountSyrupCoconut(amountSyrupCoconut);

        //Проверяем хватает ли миндального сиропа
        if(coffeeMaker.getAlmondSyrupLevel() < amountSyrupAlmond) {
            throw new MissingIngredientsException("Не хватает миндального сиропа");
        }
            coffeeMaker.setAlmondSyrupLevel(coffeeMaker.getAlmondSyrupLevel() - amountSyrupAlmond);
            cappuccino.setAmountSyrupAlmond(amountSyrupAlmond);

        //Проверяем хватает ли ванильного сиропа
        if(coffeeMaker.getVanillaSyrupLevel() < amountSyrupVanilla) {
            throw new MissingIngredientsException("Не хватает ванильного сиропа");
        }
        coffeeMaker.setVanillaSyrupLevel(coffeeMaker.getVanillaSyrupLevel() - amountSyrupVanilla);
        cappuccino.setAmountSyrupVanilla(amountSyrupVanilla);

        //Проверяем хватает ли сахара
        if(coffeeMaker.getSugarLevel() < sugar) {
            throw new MissingIngredientsException("Не хватает сахара");
        }
        coffeeMaker.setSugarLevel(coffeeMaker.getSugarLevel() - sugar);
        cappuccino.setSugar(sugar);

        cappuccino.setDateCooking(LocalDateTime.now());
        coffeeMaker.setPollutionLevel(coffeeMaker.getPollutionLevel() + 1);
        makerRepo.save(coffeeMaker);
        coffeeRepo.save(cappuccino);
        return cappuccino;
    }

    @GetMapping("/latte")
    @ApiOperation("Приготовить латте")
    public Coffee cookLatte(@RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupMaple,
                            @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupCoconut,
                            @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupAlmond,
                            @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupVanilla,
                            @RequestParam(name = "Размер порции от 0 до 5", defaultValue = "0") @Valid int sugar) throws MissingIngredientsException, PollutionException {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);

        //Проверяем уровень загрязнения кофе-машины
        if(coffeeMaker.getPollutionLevel() == 150) {
            throw new PollutionException("Необходимо выполнить чистку кофе-машины!");
        }
        Coffee latte = new Coffee();
        latte.setNameCoffee("Латте");

        //Проверям хватает ли основных ингредиентов для приготовления латте
        if(coffeeMaker.getCoffeeBeansLevel() < 1 || coffeeMaker.getWaterLevel() < 2 || coffeeMaker.getMilkLevel() < 1) {
            throw new MissingIngredientsException("Не хватает ингредиентов для латте");
        }
        coffeeMaker.setCoffeeBeansLevel(coffeeMaker.getCoffeeBeansLevel() - 1);
        latte.setCoffeeBeans(1);
        coffeeMaker.setWaterLevel(coffeeMaker.getWaterLevel() - 2);
        latte.setWater(2);
        coffeeMaker.setMilkLevel(coffeeMaker.getMilkLevel() - 1);
        latte.setMilk(1);

        //Проверяем хватает ли кленового сиропа
        if(coffeeMaker.getMapleSyrupLevel() < amountSyrupMaple) {
            throw new MissingIngredientsException("Не хватает кленового сиропа");
        }
        coffeeMaker.setMapleSyrupLevel(coffeeMaker.getMapleSyrupLevel() - amountSyrupMaple);
        latte.setAmountSyrupMaple(amountSyrupMaple);

        //Проверяем хватает ли кокосового сиропа
        if(coffeeMaker.getCoconutSyrupLevel() < amountSyrupCoconut) {
            throw new MissingIngredientsException("Не хватает кокосового сиропа");
        }
        coffeeMaker.setCoconutSyrupLevel(coffeeMaker.getCoconutSyrupLevel() - amountSyrupCoconut);
        latte.setAmountSyrupCoconut(amountSyrupCoconut);

        //Проверяем хватает ли миндального сиропа
        if(coffeeMaker.getAlmondSyrupLevel() < amountSyrupAlmond) {
            throw new MissingIngredientsException("Не хватает миндального сиропа");
        }
        coffeeMaker.setAlmondSyrupLevel(coffeeMaker.getAlmondSyrupLevel() - amountSyrupAlmond);
        latte.setAmountSyrupAlmond(amountSyrupAlmond);

        //Проверяем хватает ли ванильного сиропа
        if(coffeeMaker.getVanillaSyrupLevel() < amountSyrupVanilla) {
            throw new MissingIngredientsException("Не хватает ванильного сиропа");
        }
        coffeeMaker.setVanillaSyrupLevel(coffeeMaker.getVanillaSyrupLevel() - amountSyrupVanilla);
        latte.setAmountSyrupVanilla(amountSyrupVanilla);

        //Проверяем хватает ли сахара
        if(coffeeMaker.getSugarLevel() < sugar) {
            throw new MissingIngredientsException("Не хватает сахара");
        }
        coffeeMaker.setSugarLevel(coffeeMaker.getSugarLevel() - sugar);
        latte.setSugar(sugar);

        latte.setDateCooking(LocalDateTime.now());
        coffeeMaker.setPollutionLevel(coffeeMaker.getPollutionLevel() + 1);
        makerRepo.save(coffeeMaker);
        coffeeRepo.save(latte);
        return latte;
    }

    @GetMapping("/americano")
    @ApiOperation("Приготовить американо")
    public Coffee cookAmericano(@RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupMaple,
                                @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupCoconut,
                                @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupAlmond,
                                @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupVanilla,
                                @RequestParam(name = "Размер порции от 0 до 5", defaultValue = "0") @Valid int sugar) throws MissingIngredientsException, PollutionException{
        CoffeeMaker coffeeMaker = makerRepo.findById(1);

        //Проверяем уровень загрязнения кофе-машины
        if(coffeeMaker.getPollutionLevel() == 150) {
            throw new PollutionException("Необходимо выполнить чистку кофе-машины!");
        }
        Coffee americano = new Coffee();
        americano.setNameCoffee("Американо");

        //Проверям хватает ли основных ингредиентов для приготовления американо
        if(coffeeMaker.getCoffeeBeansLevel() < 1 || coffeeMaker.getWaterLevel() < 3) {
            throw new MissingIngredientsException("Не хватает ингредиентов для Американо");
        }
        coffeeMaker.setCoffeeBeansLevel(coffeeMaker.getCoffeeBeansLevel() - 1);
        americano.setCoffeeBeans(1);
        coffeeMaker.setWaterLevel(coffeeMaker.getWaterLevel() - 3);
        americano.setWater(3);

        //Проверяем хватает ли кленового сиропа
        if(coffeeMaker.getMapleSyrupLevel() < amountSyrupMaple) {
            throw new MissingIngredientsException("Не хватает кленового сиропа");
        }
        coffeeMaker.setMapleSyrupLevel(coffeeMaker.getMapleSyrupLevel() - amountSyrupMaple);
        americano.setAmountSyrupMaple(amountSyrupMaple);

        //Проверяем хватает ли кокосового сиропа
        if(coffeeMaker.getCoconutSyrupLevel() < amountSyrupCoconut) {
            throw new MissingIngredientsException("Не хватает кокосового сиропа");
        }
        coffeeMaker.setCoconutSyrupLevel(coffeeMaker.getCoconutSyrupLevel() - amountSyrupCoconut);
        americano.setAmountSyrupCoconut(amountSyrupCoconut);

        //Проверяем хватает ли миндального сиропа
        if(coffeeMaker.getAlmondSyrupLevel() < amountSyrupAlmond) {
            throw new MissingIngredientsException("Не хватает миндального сиропа");
        }
        coffeeMaker.setAlmondSyrupLevel(coffeeMaker.getAlmondSyrupLevel() - amountSyrupAlmond);
        americano.setAmountSyrupAlmond(amountSyrupAlmond);

        //Проверяем хватает ли ванильного сиропа
        if(coffeeMaker.getVanillaSyrupLevel() < amountSyrupVanilla) {
            throw new MissingIngredientsException("Не хватает ванильного сиропа");
        }
        coffeeMaker.setVanillaSyrupLevel(coffeeMaker.getVanillaSyrupLevel() - amountSyrupVanilla);
        americano.setAmountSyrupVanilla(amountSyrupVanilla);

        //Проверяем хватает ли сахара
        if(coffeeMaker.getSugarLevel() < sugar) {
            throw new MissingIngredientsException("Не хватает сахара");
        }
        coffeeMaker.setSugarLevel(coffeeMaker.getSugarLevel() - sugar);
        americano.setSugar(sugar);

        americano.setDateCooking(LocalDateTime.now());
        coffeeMaker.setPollutionLevel(coffeeMaker.getPollutionLevel() + 1);
        makerRepo.save(coffeeMaker);
        coffeeRepo.save(americano);
        return americano;
    }

    @GetMapping("/espresso")
    @ApiOperation("Приготовить эспрессо")
    public Coffee cookEspresso(@RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupMaple,
                               @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupCoconut,
                               @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupAlmond,
                               @RequestParam(name = "Размер порции от 0 до 2", defaultValue = "0") @Valid int amountSyrupVanilla,
                               @RequestParam(name = "Размер порции от 0 до 5", defaultValue = "0") @Valid int sugar) throws MissingIngredientsException, PollutionException {
        CoffeeMaker coffeeMaker = makerRepo.findById(1);

        //Проверяем уровень загрязнения кофе-машины
        if(coffeeMaker.getPollutionLevel() == 150) {
            throw new PollutionException("Необходимо выполнить чистку кофе-машины!");
        }
        Coffee espresso = new Coffee();
        espresso.setNameCoffee("Эспрессо");

        //Проверям хватает ли основных ингредиентов для приготовления эспрессо
        if(coffeeMaker.getCoffeeBeansLevel() < 2 || coffeeMaker.getWaterLevel() < 1) {
            throw new MissingIngredientsException("Не хватает ингредиентов для эспрессо");
        }
        coffeeMaker.setCoffeeBeansLevel(coffeeMaker.getCoffeeBeansLevel() - 2);
        espresso.setCoffeeBeans(2);
        coffeeMaker.setWaterLevel(coffeeMaker.getWaterLevel() - 1);
        espresso.setWater(1);

        //Проверяем хватает ли кленового сиропа
        if(coffeeMaker.getMapleSyrupLevel() < amountSyrupMaple) {
            throw new MissingIngredientsException("Не хватает кленового сиропа");
        }
        coffeeMaker.setMapleSyrupLevel(coffeeMaker.getMapleSyrupLevel() - amountSyrupMaple);
        espresso.setAmountSyrupMaple(amountSyrupMaple);

        //Проверяем хватает ли кокосового сиропа
        if(coffeeMaker.getCoconutSyrupLevel() < amountSyrupCoconut) {
            throw new MissingIngredientsException("Не хватает кокосового сиропа");
        }
        coffeeMaker.setCoconutSyrupLevel(coffeeMaker.getCoconutSyrupLevel() - amountSyrupCoconut);
        espresso.setAmountSyrupCoconut(amountSyrupCoconut);

        //Проверяем хватает ли миндального сиропа
        if(coffeeMaker.getAlmondSyrupLevel() < amountSyrupAlmond) {
            throw new MissingIngredientsException("Не хватает миндального сиропа");
        }
        coffeeMaker.setAlmondSyrupLevel(coffeeMaker.getAlmondSyrupLevel() - amountSyrupAlmond);
        espresso.setAmountSyrupAlmond(amountSyrupAlmond);

        //Проверяем хватает ли ванильного сиропа
        if(coffeeMaker.getVanillaSyrupLevel() < amountSyrupVanilla) {
            throw new MissingIngredientsException("Не хватает ванильного сиропа");
        }
        coffeeMaker.setVanillaSyrupLevel(coffeeMaker.getVanillaSyrupLevel() - amountSyrupVanilla);
        espresso.setAmountSyrupVanilla(amountSyrupVanilla);

        //Проверяем хватает ли сахара
        if(coffeeMaker.getSugarLevel() < sugar) {
            throw new MissingIngredientsException("Не хватает сахара");
        }
        coffeeMaker.setSugarLevel(coffeeMaker.getSugarLevel() - sugar);
        espresso.setSugar(sugar);

        espresso.setDateCooking(LocalDateTime.now());
        coffeeMaker.setPollutionLevel(coffeeMaker.getPollutionLevel() + 1);
        makerRepo.save(coffeeMaker);
        coffeeRepo.save(espresso);
        return espresso;
    }

}
