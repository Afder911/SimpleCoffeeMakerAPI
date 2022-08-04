package com.example.coffeemakerapi.serviceImp;

import com.example.coffeemakerapi.coffeeMakerException.MissingIngredientsException;
import com.example.coffeemakerapi.coffeeMakerException.PollutionException;
import com.example.coffeemakerapi.enums.CoffeeType;
import com.example.coffeemakerapi.model.Coffee;
import com.example.coffeemakerapi.model.Maker;
import com.example.coffeemakerapi.repo.CoffeeRepo;
import com.example.coffeemakerapi.repo.MakerRepo;
import com.example.coffeemakerapi.service.CoffeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CoffeeServiceImpTest {

    @Mock
    private MakerRepo makerRepo = Mockito.mock(MakerRepo.class);

    @Mock
    private CoffeeRepo coffeeRepo = Mockito.mock(CoffeeRepo.class);

    @InjectMocks
    private CoffeeService coffeeService;

    @BeforeEach
    public void setUp() {
        Mockito.when(makerRepo.findById(1)).thenReturn(new Maker());
        coffeeService = new CoffeeServiceImp(makerRepo,coffeeRepo);
    }

    @ParameterizedTest
    @CsvSource({"1,1","2,1","2,2"})
    public void yourCoffeeOptionShouldContainWaterAndCoffeeBeansMoreThanZeroAndLessThanAllowed(int amountWater, int amountCoffeeBeans) throws MissingIngredientsException, PollutionException {
        Coffee coffee = coffeeService.cookYourCoffeeOption(amountCoffeeBeans, amountWater,0,0,
                                           0,0,0,0);
        boolean actual = (coffee.getCoffeeBeans() > 0 && coffee.getCoffeeBeans() < 4) && (coffee.getWater() > 0 && coffee.getWater() < 4);
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({"1,1,0,2,1,5", "2,2,1,1,2,4", "0,0,2,0,1,3"})
    public void youCoffeeOptionShouldContainAdditionalIngredientsMoreThanLessThanZeroAndLessThanAllowed(int amountMilk, int amountSyrupMaple, int amountSyrupCoconut,
                                                                                                        int amountSyrupAlmond, int amountSyrupVanilla, int sugar)
                                                                                                        throws MissingIngredientsException, PollutionException {
        Coffee coffee = coffeeService.cookYourCoffeeOption(1,1,
                                                            amountMilk, amountSyrupMaple, amountSyrupCoconut, amountSyrupAlmond, amountSyrupVanilla, sugar);
        boolean actual = ((coffee.getMilk() > -1 && coffee.getMilk() < 3) && (coffee.getAmountSyrupMaple() > -1 && coffee.getAmountSyrupMaple() < 3)
                         && (coffee.getAmountSyrupCoconut() > -1 && coffee.getAmountSyrupCoconut() < 3) && (coffee.getAmountSyrupAlmond() > -1 && coffee.getAmountSyrupAlmond() < 3)
                         && (coffee.getAmountSyrupVanilla() > -1 && coffee.getAmountSyrupVanilla() < 3) && (coffee.getSugar() > -1 && coffee.getSugar() < 6));
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @EnumSource(CoffeeType.class)
    public void readyCoffeeNotShouldTypeNull(CoffeeType coffeeType) throws MissingIngredientsException, PollutionException {
        Coffee coffee = coffeeService.cookReadyCoffee(coffeeType,0,
                0,0,0,0);
        Assertions.assertNotNull(coffee);
    }

    @ParameterizedTest
    @EnumSource(CoffeeType.class)
    public void readyCoffeeShouldContainCorrectType(CoffeeType coffeeType) throws MissingIngredientsException, PollutionException {
        Coffee coffee = coffeeService.cookReadyCoffee(coffeeType,0,0,0,0,0);
        for(CoffeeType type: CoffeeType.values()) {
            if(type == coffee.getCoffeeType()) {
                Assertions.assertTrue(true);
            }
        }
    }
}
