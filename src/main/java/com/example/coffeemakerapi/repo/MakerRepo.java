package com.example.coffeemakerapi.repo;

import com.example.coffeemakerapi.model.CoffeeMaker;
import org.springframework.data.repository.CrudRepository;

public interface MakerRepo extends CrudRepository<CoffeeMaker, Long> {
    CoffeeMaker findById(Integer id);
}
