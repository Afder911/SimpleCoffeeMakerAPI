package com.example.coffeemakerapi.serviceImp;

import com.example.coffeemakerapi.coffeeMakerException.MissingIngredientsException;
import com.example.coffeemakerapi.coffeeMakerException.PollutionException;
import com.example.coffeemakerapi.enums.CoffeeType;
import com.example.coffeemakerapi.model.Coffee;
import com.example.coffeemakerapi.model.Maker;
import com.example.coffeemakerapi.repo.CoffeeRepo;
import com.example.coffeemakerapi.repo.MakerRepo;
import com.example.coffeemakerapi.service.CoffeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CoffeeServiceImp implements CoffeeService {


    private final MakerRepo makerRepo;

    private final CoffeeRepo coffeeRepo;

    private final Maker maker;

    public CoffeeServiceImp(MakerRepo makerRepo, CoffeeRepo coffeeRepo) {
        this.makerRepo = makerRepo;
        this.coffeeRepo = coffeeRepo;
        maker = makerRepo.findById(1);
    }

    @Override
    public Coffee cookYourCoffeeOption(int amountCoffeeBeans, int amountWater, int amountMilk, int amountSyrupMaple,
                                       int amountSyrupCoconut, int amountSyrupAlmond, int amountSyrupVanilla, int sugar)
                                       throws MissingIngredientsException, PollutionException {
        //Проверяем уровень загрязнения кофе-машины
        if(maker.getPollutionLevel() == 150) {
            throw new PollutionException("Необходимо выполнить чистку кофе-машины!");
        }
        Coffee otherCoffee = new Coffee();
        otherCoffee.setCoffeeType(null);

        //Проверяем хватает ли кофейных зерен
        if(maker.getCoffeeBeansLevel() < amountCoffeeBeans) {
            throw new MissingIngredientsException("Не хватает кофейных зерен");
        }
        maker.setCoffeeBeansLevel(maker.getCoffeeBeansLevel() - amountCoffeeBeans);
        otherCoffee.setCoffeeBeans(amountCoffeeBeans);

        //Проверяем хватает ли воды
        if(maker.getWaterLevel() < amountWater) {
            throw new MissingIngredientsException("Не хватает воды");
        }
        maker.setWaterLevel(maker.getWaterLevel() - amountWater);
        otherCoffee.setWater(amountWater);

        //Проверяем хватает ли молока
        if(maker.getMilkLevel() < amountMilk) {
            throw new MissingIngredientsException("Не хватает молока");
        }
        maker.setMilkLevel(maker.getMilkLevel() - amountMilk);
        otherCoffee.setMilk(amountMilk);

        //Проверяем хватает ли кленового сиропа
        if(maker.getMapleSyrupLevel() < amountSyrupMaple) {
            throw new MissingIngredientsException("Не хватает кленового сиропа");
        }
        maker.setMapleSyrupLevel(maker.getMapleSyrupLevel() - amountSyrupMaple);
        otherCoffee.setAmountSyrupMaple(amountSyrupMaple);

        //Проверяем хватает ли кокосового сиропа
        if(maker.getCoconutSyrupLevel() < amountSyrupCoconut) {
            throw new MissingIngredientsException("Не хватает кокосового сиропа");
        }
        maker.setCoconutSyrupLevel(maker.getCoconutSyrupLevel() - amountSyrupCoconut);
        otherCoffee.setAmountSyrupCoconut(amountSyrupCoconut);

        //Проверяем хватает ли миндального сиропа
        if(maker.getAlmondSyrupLevel() < amountSyrupAlmond) {
            throw new MissingIngredientsException("Не хватает миндального сиропа");
        }
        maker.setAlmondSyrupLevel(maker.getAlmondSyrupLevel() - amountSyrupAlmond);
        otherCoffee.setAmountSyrupAlmond(amountSyrupAlmond);

        //Проверяем хватает ли ванильного сиропа
        if(maker.getVanillaSyrupLevel() < amountSyrupVanilla) {
            throw new MissingIngredientsException("Не хватает ванильного сиропа");
        }
        maker.setVanillaSyrupLevel(maker.getVanillaSyrupLevel() - amountSyrupVanilla);
        otherCoffee.setAmountSyrupVanilla(amountSyrupVanilla);

        //Проверяем хватает ли сахара
        if(maker.getSugarLevel() < sugar) {
            throw new MissingIngredientsException("Не хватает сахара");
        }
        maker.setSugarLevel(maker.getSugarLevel() - sugar);
        otherCoffee.setSugar(sugar);

        otherCoffee.setDateCooking(LocalDateTime.now());
        maker.setPollutionLevel(maker.getPollutionLevel() + 1);
        makerRepo.save(maker);
        coffeeRepo.save(otherCoffee);
        return otherCoffee;
    }

    @Override
    public Coffee cookReadyCoffee(CoffeeType type, int amountSyrupMaple, int amountSyrupCoconut,
                                  int amountSyrupAlmond, int amountSyrupVanilla, int sugar)
                                  throws MissingIngredientsException, PollutionException {
        //Проверяем уровень загрязнения кофе-машины
        if(maker.getPollutionLevel() == 150) {
            throw new PollutionException("Необходимо выполнить чистку кофе-машины!");
        }
        Coffee coffee = new Coffee();

        switch(type) {
            case CAPPUCCINO:
                coffee.setCoffeeType(CoffeeType.CAPPUCCINO);
                //Проверяем хватает ли основных ингредиентов для приготовления капучино
                if(maker.getCoffeeBeansLevel() < 1 || maker.getWaterLevel() < 2 || maker.getMilkLevel() < 1) {
                    throw new MissingIngredientsException("Не хватает ингредиентов для капучино");
                }
                maker.setCoffeeBeansLevel(maker.getCoffeeBeansLevel() - 1);
                coffee.setCoffeeBeans(1);
                maker.setWaterLevel(maker.getWaterLevel() - 2);
                coffee.setWater(2);
                maker.setMilkLevel(maker.getMilkLevel() - 1);
                coffee.setMilk(1);
                break;
            case LATTE:
                coffee.setCoffeeType(CoffeeType.LATTE);
                //Проверяем хватает ли основных ингредиентов для приготовления латте
                if(maker.getCoffeeBeansLevel() < 1 || maker.getWaterLevel() < 2 || maker.getMilkLevel() < 1) {
                    throw new MissingIngredientsException("Не хватает ингредиентов для латте");
                }
                maker.setCoffeeBeansLevel(maker.getCoffeeBeansLevel() - 1);
                coffee.setCoffeeBeans(1);
                maker.setWaterLevel(maker.getWaterLevel() - 2);
                coffee.setWater(2);
                maker.setMilkLevel(maker.getMilkLevel() - 1);
                coffee.setMilk(1);
                break;
            case AMERICANO:
                coffee.setCoffeeType(CoffeeType.AMERICANO);
                //Проверяем хватает ли основных ингредиентов для приготовления американо
                if(maker.getCoffeeBeansLevel() < 1 || maker.getWaterLevel() < 3) {
                    throw new MissingIngredientsException("Не хватает ингредиентов для Американо");
                }
                maker.setCoffeeBeansLevel(maker.getCoffeeBeansLevel() - 1);
                coffee.setCoffeeBeans(1);
                maker.setWaterLevel(maker.getWaterLevel() - 3);
                coffee.setWater(3);
                break;
            case ESPRESSO:
                coffee.setCoffeeType(CoffeeType.ESPRESSO);
                //Проверяем хватает ли основных ингредиентов для приготовления эспрессо
                if(maker.getCoffeeBeansLevel() < 2 || maker.getWaterLevel() < 1) {
                    throw new MissingIngredientsException("Не хватает ингредиентов для эспрессо");
                }
                maker.setCoffeeBeansLevel(maker.getCoffeeBeansLevel() - 2);
                coffee.setCoffeeBeans(2);
                maker.setWaterLevel(maker.getWaterLevel() - 1);
                coffee.setWater(1);
                break;
        }
        //Проверяем хватает ли кленового сиропа
        if(maker.getMapleSyrupLevel() < amountSyrupMaple) {
            throw new MissingIngredientsException("Не хватает кленового сиропа");
        }
        maker.setMapleSyrupLevel(maker.getMapleSyrupLevel() - amountSyrupMaple);
        coffee.setAmountSyrupMaple(amountSyrupMaple);

        //Проверяем хватает ли кокосового сиропа
        if(maker.getCoconutSyrupLevel() < amountSyrupCoconut) {
            throw new MissingIngredientsException("Не хватает кокосового сиропа");
        }
        maker.setCoconutSyrupLevel(maker.getCoconutSyrupLevel() - amountSyrupCoconut);
        coffee.setAmountSyrupCoconut(amountSyrupCoconut);

        //Проверяем хватает ли миндального сиропа
        if(maker.getAlmondSyrupLevel() < amountSyrupAlmond) {
            throw new MissingIngredientsException("Не хватает миндального сиропа");
        }
        maker.setAlmondSyrupLevel(maker.getAlmondSyrupLevel() - amountSyrupAlmond);
        coffee.setAmountSyrupAlmond(amountSyrupAlmond);

        //Проверяем хватает ли ванильного сиропа
        if(maker.getVanillaSyrupLevel() < amountSyrupVanilla) {
            throw new MissingIngredientsException("Не хватает ванильного сиропа");
        }
        maker.setVanillaSyrupLevel(maker.getVanillaSyrupLevel() - amountSyrupVanilla);
        coffee.setAmountSyrupVanilla(amountSyrupVanilla);

        //Проверяем хватает ли сахара
        if(maker.getSugarLevel() < sugar) {
            throw new MissingIngredientsException("Не хватает сахара");
        }
        maker.setSugarLevel(maker.getSugarLevel() - sugar);
        coffee.setSugar(sugar);

        coffee.setDateCooking(LocalDateTime.now());
        maker.setPollutionLevel(maker.getPollutionLevel() + 1);
        makerRepo.save(maker);
        coffeeRepo.save(coffee);
        return coffee;
    }
}
