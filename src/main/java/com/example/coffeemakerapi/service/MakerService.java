package com.example.coffeemakerapi.service;

import com.example.coffeemakerapi.enums.Ingredients;
import com.example.coffeemakerapi.model.Maker;

public interface MakerService {

    public Maker check();

    public Maker clear();

    public Maker refill();

    public Maker refillParameterized(Ingredients ingredients);
}
