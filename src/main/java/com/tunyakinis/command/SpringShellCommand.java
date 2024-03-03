package com.tunyakinis.command;

import com.tunyakinis.builder.ViewBuilder;
import com.tunyakinis.model.Cuisine;
import com.tunyakinis.model.Drink;
import com.tunyakinis.model.Meal;
import com.tunyakinis.model.Order;
import com.tunyakinis.model.OrderedItem;
import com.tunyakinis.service.DisplayMenuService;
import com.tunyakinis.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

@ShellComponent
@Slf4j
@RequiredArgsConstructor
@Component
public class SpringShellCommand {

  private final OrderService orderService;
  private final DisplayMenuService displayMenuService;
  private final ViewBuilder viewBuilder;

  @ShellMethod(key = "1", value = "Enter 1 to Display lunch")
  public String openLunchMenu() {
    List<Cuisine> cuisine = displayMenuService.displayCuisineList();
    List<Meal> mainCourse = displayMenuService.displayMainCourseList();
    List<Meal> dessert = displayMenuService.displayDessertList();
    return viewBuilder.buildLunchMenu(cuisine, mainCourse, dessert);
  }

  @ShellMethod(key = "2", value = "Enter 2 to Display drinks")
  public String openDrinkList() {
    List<Drink> drinkList = displayMenuService.displayDrinkList();
    return viewBuilder.buildDrinkMenu(drinkList);
  }

  @ShellMethod(key = "3", value = "Enter 3 to Order drinks")
  public void orderDrinks() {
    orderService.orderDrink();
  }

  @ShellMethod(key = "4", value = "Enter 4 to Order food")
  public void orderFood() {
    orderService.orderLunch();
  }

  @ShellMethod(key = "5", value = "Enter 5 to Submit order")
  public String submitOrder() {
    List<OrderedItem> orderedItemList = orderService.submitOrder();
    return viewBuilder.buildSingleOrderReport(orderedItemList);
  }

  @ShellMethod(key = "6", value = "Enter 6 to Clean bucket to cancel current order")
  public void cleanBucket() {
    orderService.cleanBucket();
  }

  @ShellMethod(key = "7", value = "Enter 7 to Show all clients' orders")
  public String showAllOrders() {
    List<Order> orderList = orderService.displayOrdersReport();
    return viewBuilder.buildOrdersReport(orderList);
  }
}
