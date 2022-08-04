package com.example.coffeemakerapi.serviceImp;

import com.example.coffeemakerapi.enums.Ingredients;
import com.example.coffeemakerapi.model.Maker;
import com.example.coffeemakerapi.repo.MakerRepo;
import com.example.coffeemakerapi.service.MakerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MakerServiceImp implements MakerService {

    private final MakerRepo makerRepo;

    private Maker maker;

    public MakerServiceImp(MakerRepo makerRepo) {
        this.makerRepo = makerRepo;
        maker = makerRepo.findById(1);
    }

    public Maker check() {
        if(maker == null) {
            maker = new Maker();
            makerRepo.save(maker);
        }
        return maker;
    }

    public Maker clear() {
        maker.setCoffeeBeansLevel(0);
        maker.setSugarLevel(0);
        maker.setWaterLevel(0);
        maker.setMilkLevel(0);
        maker.setMapleSyrupLevel(0);
        maker.setCoconutSyrupLevel(0);
        maker.setAlmondSyrupLevel(0);
        maker.setVanillaSyrupLevel(0);
        maker.setPollutionLevel(0);
        maker.setDateLastCleaning(LocalDateTime.now());
        makerRepo.save(maker);
        return maker;
    }

    public Maker refill() {
        maker.setCoffeeBeansLevel(100);
        maker.setSugarLevel(100);
        maker.setWaterLevel(100);
        maker.setMilkLevel(50);
        maker.setMapleSyrupLevel(15);
        maker.setCoconutSyrupLevel(15);
        maker.setAlmondSyrupLevel(15);
        maker.setVanillaSyrupLevel(15);
        maker.setPollutionLevel(maker.getPollutionLevel()+8);
        maker.setDateLastRefill(LocalDateTime.now());
        makerRepo.save(maker);
        return maker;
    }

    public Maker refillParameterized(Ingredients ingredients) {
        switch (ingredients) {
            case COFFEE_BEANS:
                maker.setCoffeeBeansLevel(100);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
            case SUGAR:
                maker.setSugarLevel(100);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
            case WATER:
                maker.setWaterLevel(100);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
            case MILK:
                maker.setMilkLevel(50);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
            case SYRUP_MAPLE:
                maker.setMapleSyrupLevel(15);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
            case SYRUP_COCONUT:
                maker.setCoconutSyrupLevel(15);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
            case SYRUP_ALMOND:
                maker.setAlmondSyrupLevel(15);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
            case SYRUP_VANILLA:
                maker.setVanillaSyrupLevel(15);
                maker.setPollutionLevel(maker.getPollutionLevel()+1);
                maker.setDateLastPartialFilling(LocalDateTime.now());
                break;
        }
        makerRepo.save(maker);
        return maker;
    }
}
