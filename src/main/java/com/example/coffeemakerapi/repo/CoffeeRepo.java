package com.example.coffeemakerapi.repo;

import com.example.coffeemakerapi.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepo extends CrudRepository<Coffee,Long> {
    Coffee findById(Integer id);
}
