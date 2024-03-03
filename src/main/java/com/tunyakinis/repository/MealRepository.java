package com.tunyakinis.repository;

import com.tunyakinis.model.Meal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

  Meal findMealByName(String mainCourseName);

  List<Meal> findAllByType(String type);
}
