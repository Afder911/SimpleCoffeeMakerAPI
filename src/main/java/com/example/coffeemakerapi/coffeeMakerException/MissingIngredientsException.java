package com.example.coffeemakerapi.coffeeMakerException;

public class MissingIngredientsException extends Exception{

    public MissingIngredientsException(String message) {
        super(message);
    }
}
