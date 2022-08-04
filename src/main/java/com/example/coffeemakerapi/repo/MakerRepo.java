package com.example.coffeemakerapi.repo;

import com.example.coffeemakerapi.model.Maker;
import org.springframework.data.repository.CrudRepository;

public interface MakerRepo extends CrudRepository<Maker, Long> {
    Maker findById(Integer id);
}
