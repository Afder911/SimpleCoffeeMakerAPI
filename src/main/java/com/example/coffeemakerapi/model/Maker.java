package com.example.coffeemakerapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "coffee_maker_info")
public class Maker {

    @Id
    private final Integer id = 1;

    @Column(name = "coffee_beans_level")
    @Min(0) @Max(100)
    @NotNull
    private int coffeeBeansLevel;

    @Column(name = "sugar_level")
    @Min(0) @Max(100)
    @NotNull
    private int sugarLevel;

    @Column(name = "water_level")
    @Min(0) @Max(100)
    @NotNull
    private int waterLevel;

    @Column(name = "milk_level")
    @Min(0) @Max(50)
    @NotNull
    private int milkLevel;

    @Column(name = "maple_syrup_level")
    @Min(0) @Max(15)
    @NotNull
    private int mapleSyrupLevel;

    @Column(name = "coconut_syrup_level")
    @Min(0) @Max(15)
    @NotNull
    private int coconutSyrupLevel;

    @Column(name = "almond_syrup_level")
    @Min(0) @Max(15)
    @NotNull
    private int almondSyrupLevel;

    @Column(name = "vanilla_syrup_level")
    @Min(0) @Max(15)
    @NotNull
    private int vanillaSyrupLevel;

    @Column(name = "pollution_level")
    @Min(0) @Max(100)
    @NotNull
    private int pollutionLevel = 0;

    @Column(name = "date_last_refill")
    @NotNull
    private LocalDateTime dateLastRefill;

    @Column(name = "date_last_partial_filling")
    @NotNull
    private LocalDateTime dateLastPartialFilling;

    @Column(name = "date_last_cleaning")
    @NotNull
    private LocalDateTime dateLastCleaning;

    public Maker() {
        this.coffeeBeansLevel = 100;
        this.sugarLevel = 100;
        this.waterLevel = 100;
        this.milkLevel = 50;
        this.mapleSyrupLevel = 15;
        this.coconutSyrupLevel = 15;
        this.almondSyrupLevel = 15;
        this.vanillaSyrupLevel = 15;
        this.pollutionLevel = 1;
        this.dateLastRefill = LocalDateTime.now();
        this.dateLastPartialFilling = LocalDateTime.now();
        this.dateLastCleaning = LocalDateTime.now();
    }

    public int getCoffeeBeansLevel() {
        return coffeeBeansLevel;
    }

    public void setCoffeeBeansLevel(int coffeeBeansLevel) {
        this.coffeeBeansLevel = coffeeBeansLevel;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getMilkLevel() {
        return milkLevel;
    }

    public void setMilkLevel(int milkLevel) {
        this.milkLevel = milkLevel;
    }

    public int getMapleSyrupLevel() {
        return mapleSyrupLevel;
    }

    public void setMapleSyrupLevel(int mapleSyrupLevel) {
        this.mapleSyrupLevel = mapleSyrupLevel;
    }

    public int getCoconutSyrupLevel() {
        return coconutSyrupLevel;
    }

    public void setCoconutSyrupLevel(int coconutSyrupLevel) {
        this.coconutSyrupLevel = coconutSyrupLevel;
    }

    public int getAlmondSyrupLevel() {
        return almondSyrupLevel;
    }

    public void setAlmondSyrupLevel(int almondSyrupLevel) {
        this.almondSyrupLevel = almondSyrupLevel;
    }

    public int getVanillaSyrupLevel() {
        return vanillaSyrupLevel;
    }

    public void setVanillaSyrupLevel(int vanillaSyrupLevel) {
        this.vanillaSyrupLevel = vanillaSyrupLevel;
    }

    public int getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(int pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public LocalDateTime getDateLastRefill() {
        return dateLastRefill;
    }

    public void setDateLastRefill(LocalDateTime dateLastRefill) {
        this.dateLastRefill = dateLastRefill;
    }

    public LocalDateTime getDateLastPartialFilling() {
        return dateLastPartialFilling;
    }

    public void setDateLastPartialFilling(LocalDateTime dateLastPartialFilling) {
        this.dateLastPartialFilling = dateLastPartialFilling;
    }

    public LocalDateTime getDateLastCleaning() {
        return dateLastCleaning;
    }

    public void setDateLastCleaning(LocalDateTime dateLastCleaning) {
        this.dateLastCleaning = dateLastCleaning;
    }
}
