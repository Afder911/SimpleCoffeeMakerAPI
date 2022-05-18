package com.example.coffeemakerapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "coffee_log")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "coffee_name")
    @Pattern(regexp = "(^Капучино$)|(^Латте$)|(^Американо$)|(^Эспрессо$)|(^Свой вариант$)", message = "Введите Капучино|Латте|Американо|Эспрессо" +
            " или Свой вариант")
    private String nameCoffee;

    @Column(name = "milk")
    @Pattern(regexp = "(^Добавить$)|(^Без молока$)", message = "Введите Добавить если хотите с молоком")
    private String milk;

    @Column(name = "syrup")
    @Pattern(regexp = "(^Кленовый$)|(^Кокосовый$)|(^Миндальный$)|(^Без сиропа$)", message = "Введите: Кленовый|Кокосовый|Миндальный если хотите с сиропом")
    private String syrup;

    @Column(name = "sugar")
    @Pattern(regexp = "(^Добавить$)|(^Без сахара$)", message = "Введите Добавить если хотите с сахаром")
    private String sugar;

    @Column(name = "date_cooking")
    @NotNull
    private LocalDateTime date;

    public Coffee() {
    }
    public Coffee(String nameCoffee, String milk, String syrup, String sugar) {
        this.nameCoffee = nameCoffee;
        this.milk = milk;
        this.syrup = syrup;
        this.sugar = sugar;
        this.date = LocalDateTime.now();
    }

    public String getNameCoffee() {
        return nameCoffee;
    }

    public void setNameCoffee(String nameCoffee) {
        this.nameCoffee = nameCoffee;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public String getSyrup() {
        return syrup;
    }

    public void setSyrup(String syrup) {
        this.syrup = syrup;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
