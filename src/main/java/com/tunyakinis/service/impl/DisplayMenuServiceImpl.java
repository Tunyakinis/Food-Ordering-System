package com.tunyakinis.service.impl;

import com.tunyakinis.model.Cuisine;
import com.tunyakinis.model.Drink;
import com.tunyakinis.model.Meal;
import com.tunyakinis.repository.CuisineRepository;
import com.tunyakinis.repository.DrinkRepository;
import com.tunyakinis.repository.MealRepository;
import com.tunyakinis.service.DisplayMenuService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DisplayMenuServiceImpl implements DisplayMenuService {

  private final CuisineRepository cuisineRepository;
  private final MealRepository mealRepository;
  private final DrinkRepository drinkRepository;

  @Override
  public List<Cuisine> displayCuisineList() {
    return cuisineRepository.findAll();
  }

  @Override
  public List<Meal> displayMainCourseList() {
    return mealRepository.findAllByType("main course");
  }

  @Override
  public List<Meal> displayDessertList() {
    return mealRepository.findAllByType("dessert");
  }

  @Override
  public List<Drink> displayDrinkList() {
    return drinkRepository.findAll();
  }
}
