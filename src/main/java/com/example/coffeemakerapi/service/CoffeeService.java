package com.example.coffeemakerapi.service;

import com.example.coffeemakerapi.coffeeMakerException.MissingIngredientsException;
import com.example.coffeemakerapi.coffeeMakerException.PollutionException;
import com.example.coffeemakerapi.enums.CoffeeType;
import com.example.coffeemakerapi.model.Coffee;

public interface CoffeeService {

    public Coffee cookYourCoffeeOption(int amountCoffeeBeans, int amountWater, int amountMilk, int amountSyrupMaple,
                                       int amountSyrupCoconut, int amountSyrupAlmond, int amountSyrupVanilla, int sugar)
                                       throws MissingIngredientsException, PollutionException;

    public Coffee cookReadyCoffee(CoffeeType type, int amountSyrupMaple, int amountSyrupCoconut, int amountSyrupAlmond,
                                  int amountSyrupVanilla, int sugar) throws MissingIngredientsException, PollutionException;
}
