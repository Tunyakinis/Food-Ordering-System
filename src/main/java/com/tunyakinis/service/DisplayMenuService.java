package com.tunyakinis.service;

import com.tunyakinis.model.Cuisine;
import com.tunyakinis.model.Drink;
import com.tunyakinis.model.Meal;
import java.util.List;

public interface DisplayMenuService {

  List<Cuisine> displayCuisineList();

  List<Meal> displayMainCourseList();

  List<Meal> displayDessertList();

  List<Drink> displayDrinkList();
}
