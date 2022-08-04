package com.example.coffeemakerapi.controller;

import com.example.coffeemakerapi.enums.Ingredients;
import com.example.coffeemakerapi.model.Maker;
import com.example.coffeemakerapi.serviceImp.MakerServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maker")
@Api(description = "Контроллер управления кофе-машиной")
public class MakerController {

    private final MakerServiceImp makerServiceImp;

    public MakerController(MakerServiceImp makerServiceImp) {
        this.makerServiceImp = makerServiceImp;
    }

    @PostMapping("/check")
    @ApiOperation("Проверить заполненность кофе-машины")
    public Maker checkMakerForRefill() {
        return makerServiceImp.check();
    }

    @PutMapping("/clear")
    @ApiOperation("Почистить кофе-машину")
    public Maker clearMaker() {
        return makerServiceImp.clear();
    }

    @PutMapping("/refill")
    @ApiOperation("Полностью заправить кофе-машину")
    public Maker refillMaker() {
        return makerServiceImp.refill();
    }

    @PutMapping("/refill-params")
    @ApiOperation("Заправить кофе-машину выбранными ингредиентами")
    public Maker refillMakerParameterized(Ingredients ingredients) {
        return makerServiceImp.refillParameterized(ingredients);
    }
}
