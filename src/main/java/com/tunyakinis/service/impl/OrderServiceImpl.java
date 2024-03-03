package com.tunyakinis.service.impl;

import com.tunyakinis.command.MultiSelector;
import com.tunyakinis.command.SingleSelector;
import com.tunyakinis.exception.EmptyBucketException;
import com.tunyakinis.exception.EmptyOrdersListException;
import com.tunyakinis.model.Cuisine;
import com.tunyakinis.model.Drink;
import com.tunyakinis.model.Meal;
import com.tunyakinis.model.Order;
import com.tunyakinis.model.OrderedItem;
import com.tunyakinis.repository.CuisineRepository;
import com.tunyakinis.repository.DrinkRepository;
import com.tunyakinis.repository.MealRepository;
import com.tunyakinis.repository.OrderRepository;
import com.tunyakinis.repository.OrderedItemRepository;
import com.tunyakinis.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private static List<OrderedItem> tmpBucket = new ArrayList<>();
  private final CuisineRepository cuisineRepository;
  private final DrinkRepository drinkRepository;
  private final OrderRepository orderRepository;
  private final MealRepository mealRepository;
  private final OrderedItemRepository orderedItemRepository;
  private final MultiSelector multiSelector;
  private final SingleSelector singleSelector;

  @Override
  public void orderLunch() {
    System.out.println("Please choose your main course:");
    orderMainCourse();
    System.out.println("Please choose your dessert:");
    orderDesert();
  }

  @Override
  public void orderDrink() {
    System.out.println("Please choose your drinks:");
    List<Drink> drinkList = drinkRepository.findAll();
    List<String> selectedDrinks = multiSelector.multiselectDrinks(drinkList);
    Map<String, String> selectedDrinksWithAddition = multiSelector.multiselectAdditionForDrinks(
        selectedDrinks);

    selectedDrinksWithAddition.entrySet().stream()
        .map(entry -> {
          String drinkName = entry.getKey();
          String addition = entry.getValue();
          Drink drink = drinkRepository.findByName(drinkName);

          OrderedItem orderedItem = new OrderedItem();
          orderedItem.setOrderedItemId(drink.getId());
          orderedItem.setItemType("drink");
          orderedItem.setName(drink.getName());
          orderedItem.setPrice(drink.getPrice());
          orderedItem.setAddition(addition);

          return orderedItem;
        })
        .forEach(tmpBucket::add);
  }

  @Override
  public List<OrderedItem> submitOrder() {
    validateBucketNotEmpty();

    Order order = new Order();

    double finalPrice = tmpBucket.stream()
        .mapToDouble(OrderedItem::getPrice)
        .sum();

    order.setSummaryPrice(finalPrice);
    orderRepository.save(order);
    tmpBucket.forEach(orderedItem -> orderedItem.setOrder(order));
    orderedItemRepository.saveAll(tmpBucket);
    List<OrderedItem> orderedItemList = new ArrayList<>(tmpBucket);
    tmpBucket.clear();

    return orderedItemList;
  }

  @Override
  public void cleanBucket() {
    tmpBucket.clear();
  }

  @Override
  public List<Order> displayOrdersReport() {
    validateOrdersExist();
    return orderRepository.findAll();
  }

  private Cuisine chooseCuisine() {
    List<Cuisine> cuisineList = cuisineRepository.findAll();
    String chosenCuisineName = singleSelector.singleCuisineSelector(cuisineList);

    return cuisineRepository.findCuisineByName(chosenCuisineName);
  }

  private void orderMainCourse() {
    Cuisine chosenCuisine = chooseCuisine();
    List<Meal> mainCourseList = chosenCuisine.getMealList().stream()
        .filter(meal -> "main course".equals(meal.getType()))
        .collect(Collectors.toList());
    String mainCourseName = singleSelector.singleMainDishSelector(mainCourseList);
    addMealToBucket(mainCourseName);
  }

  private void orderDesert() {
    Cuisine chosenCuisine = chooseCuisine();
    List<Meal> dessertList = chosenCuisine.getMealList().stream()
        .filter(meal -> "dessert".equals(meal.getType()))
        .collect(Collectors.toList());
    String dessertName = singleSelector.singleDessertSelector(dessertList);
    addMealToBucket(dessertName);
  }

  private void addMealToBucket(String mealName) {
    Meal orderedMeal = mealRepository.findMealByName(mealName);

    OrderedItem orderedItem = new OrderedItem();
    orderedItem.setOrderedItemId(orderedMeal.getId());
    orderedItem.setName(orderedMeal.getName());
    orderedItem.setItemType(orderedMeal.getType());
    orderedItem.setPrice(orderedMeal.getPrice());

    tmpBucket.add(orderedItem);
  }

  private void validateBucketNotEmpty() {
    if (tmpBucket.isEmpty()) {
      throw new EmptyBucketException();
    }
  }

  private void validateOrdersExist() {
    if (orderRepository.findAll().isEmpty()) {
      throw new EmptyOrdersListException();
    }
  }
}
