package com.tunyakinis.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tunyakinis.model.Cuisine;
import com.tunyakinis.model.Drink;
import com.tunyakinis.model.Meal;
import com.tunyakinis.repository.CuisineRepository;
import com.tunyakinis.repository.DrinkRepository;
import com.tunyakinis.repository.MealRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DisplayMenuServiceImplTest {

  @Mock
  private CuisineRepository cuisineRepository;

  @Mock
  private MealRepository mealRepository;

  @Mock
  private DrinkRepository drinkRepository;

  @InjectMocks
  private DisplayMenuServiceImpl displayMenuService;

  private List<Meal> mealList = new ArrayList<>();
  private Cuisine italianCuisine = new Cuisine(1L, "Italian", mealList);
  private Cuisine polishCuisine = new Cuisine(2L, "Polish", mealList);

  @Test
  public void testDisplayCuisineList() {
    List<Cuisine> expectedCuisineList = Arrays.asList(
        new Cuisine(1L, "Italian", mealList),
        new Cuisine(2L, "Polish", mealList));
    when(cuisineRepository.findAll()).thenReturn(expectedCuisineList);

    List<Cuisine> result = displayMenuService.displayCuisineList();

    assertThat(result).isEqualTo(expectedCuisineList);
    verify(cuisineRepository).findAll();
  }

  @Test
  public void testDisplayMainCourseList() {
    List<Meal> expectedMainCourseList = Arrays.asList(
        new Meal(1L, italianCuisine, "Apple", 10.0, "dessert"),
        new Meal(2L, polishCuisine, "Pie", 12.0, "dessert")
    );
    when(mealRepository.findAllByType("main course")).thenReturn(expectedMainCourseList);

    List<Meal> result = displayMenuService.displayMainCourseList();

    assertThat(result).isEqualTo(expectedMainCourseList);
    verify(mealRepository).findAllByType("main course");
  }

  @Test
  public void testDisplayDessertList() {
    List<Meal> expectedDessertList = Arrays.asList(
        new Meal(1L, italianCuisine, "Apple", 10.0, "dessert"),
        new Meal(2L, polishCuisine, "Pie", 12.0, "dessert")
    );
    when(mealRepository.findAllByType("dessert")).thenReturn(expectedDessertList);

    List<Meal> result = displayMenuService.displayDessertList();

    assertThat(result).isEqualTo(expectedDessertList);
    verify(mealRepository).findAllByType("dessert");
  }

  @Test
  public void testDisplayDrinkList() {
    List<Drink> expectedDrinkList = Arrays.asList(
        new Drink(1L, "Cola", 10.0),
        new Drink(2L, "Pepsi", 12.0)
    );
    when(drinkRepository.findAll()).thenReturn(expectedDrinkList);

    List<Drink> result = displayMenuService.displayDrinkList();

    assertThat(result).isEqualTo(expectedDrinkList);
    verify(drinkRepository).findAll();
  }
}
