package com.example.coffeemakerapi.model;

import com.example.coffeemakerapi.enums.CoffeeType;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "coffee_log")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "coffee_type")
    @Enumerated(EnumType.STRING)
    CoffeeType coffeeType;

    @Column(name = "amount_syrup_maple")
    @Min(0) @Max(2)
    private int amountSyrupMaple;

    @Column(name = "amount_syrup_coconut")
    @Min(0) @Max(2)
    private int amountSyrupCoconut;

    @Column(name = "amount_syrup_almond")
    @Min(0) @Max(2)
    private int amountSyrupAlmond;

    @Column(name = "amount_syrup_vanilla")
    @Min(0) @Max(2)
    private int amountSyrupVanilla;

    @Column(name = "coffee_beans")
    @Min(1) @Max(3)
    private int coffeeBeans;

    @Column(name = "water")
    @Min(1) @Max(3)
    private int water;

    @Column(name = "milk")
    @Min(0) @Max(2)
    private int milk;

    @Column(name = "sugar")
    @Min(0) @Max(5)
    private int sugar;

    @Column(name = "date_cooking")
    @NotNull
    private LocalDateTime dateCooking;

    public Coffee() {
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public int getAmountSyrupMaple() {
        return amountSyrupMaple;
    }

    public void setAmountSyrupMaple(int amountSyrupMaple) {
        this.amountSyrupMaple = amountSyrupMaple;
    }

    public int getAmountSyrupCoconut() {
        return amountSyrupCoconut;
    }

    public void setAmountSyrupCoconut(int amountSyrupCoconut) {
        this.amountSyrupCoconut = amountSyrupCoconut;
    }

    public int getAmountSyrupAlmond() {
        return amountSyrupAlmond;
    }

    public void setAmountSyrupAlmond(int amountSyrupAlmond) {
        this.amountSyrupAlmond = amountSyrupAlmond;
    }

    public int getAmountSyrupVanilla() {
        return amountSyrupVanilla;
    }

    public void setAmountSyrupVanilla(int amountSyrupVanilla) {
        this.amountSyrupVanilla = amountSyrupVanilla;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public LocalDateTime getDateCooking() {
        return dateCooking;
    }

    public void setDateCooking(LocalDateTime dateCooking) {
        this.dateCooking = dateCooking;
    }
}
