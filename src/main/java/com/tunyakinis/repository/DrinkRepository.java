package com.tunyakinis.repository;

import com.tunyakinis.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

  Drink findByName(String drink);
}
