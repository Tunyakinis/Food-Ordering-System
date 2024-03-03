package com.tunyakinis.repository;

import com.tunyakinis.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

  Cuisine findCuisineByName(String cuisineName);
}
