package com.example.coffeemakerapi.serviceImp;

import com.example.coffeemakerapi.enums.Ingredients;
import com.example.coffeemakerapi.model.Maker;
import com.example.coffeemakerapi.repo.MakerRepo;
import com.example.coffeemakerapi.service.MakerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MakerServiceImpTest {

    @Mock
    private MakerRepo makerRepo = Mockito.mock(MakerRepo.class);

    @InjectMocks
    private MakerService makerService;

    @BeforeEach
    public void setUp() {
        Mockito.when(makerRepo.findById(1)).thenReturn(new Maker());
        makerService = new MakerServiceImp(makerRepo);
    }

    @Test
    public void checkShouldReturnMaker() {
        Assertions.assertNotNull(makerService.check());
    }

    @Test
    public void clearShouldReturnCleanAndEmptyMaker() {
        Maker actual = makerService.clear();
        if(actual.getPollutionLevel() == 0 && actual.getWaterLevel() == 0 && actual.getCoffeeBeansLevel() == 0
           && actual.getMilkLevel() == 0 && actual.getMapleSyrupLevel() == 0 && actual.getCoconutSyrupLevel() == 0
           && actual.getAlmondSyrupLevel() == 0 && actual.getVanillaSyrupLevel() == 0 && actual.getSugarLevel() == 0) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertFalse(false);
        }
    }

    @Test
    public void refillShouldReturnFull() {
        Maker actual = makerService.refill();
        if(actual.getPollutionLevel() == 8 && actual.getWaterLevel() == 100 && actual.getCoffeeBeansLevel() == 100
                && actual.getMilkLevel() == 50 && actual.getMapleSyrupLevel() == 15 && actual.getCoconutSyrupLevel() == 15
                && actual.getAlmondSyrupLevel() == 15 && actual.getVanillaSyrupLevel() == 15 && actual.getSugarLevel() == 100) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertFalse(false);
        }
    }

    @ParameterizedTest
    @EnumSource(Ingredients.class)
    public void refillParameterizedShouldFillCertainIngredients(Ingredients ingredients) {
        Maker maker = makerService.refillParameterized(ingredients);
        switch (ingredients) {
            case COFFEE_BEANS:
                Assertions.assertEquals(100, maker.getCoffeeBeansLevel());
                break;
            case SUGAR:
                Assertions.assertEquals(100, maker.getSugarLevel());
                break;
            case WATER:
                Assertions.assertEquals(100, maker.getWaterLevel());
                break;
            case MILK:
                Assertions.assertEquals(50, maker.getMilkLevel());;
                break;
            case SYRUP_MAPLE:
                Assertions.assertEquals(15, maker.getMapleSyrupLevel());
                break;
            case SYRUP_COCONUT:
                Assertions.assertEquals(15, maker.getCoconutSyrupLevel());
                break;
            case SYRUP_ALMOND:
                Assertions.assertEquals(15, maker.getAlmondSyrupLevel());
                break;
            case SYRUP_VANILLA:
                Assertions.assertEquals(15, maker.getVanillaSyrupLevel());
                break;
        }
    }
}
