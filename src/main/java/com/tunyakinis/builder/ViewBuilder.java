package com.tunyakinis.builder;

import com.tunyakinis.model.Cuisine;
import com.tunyakinis.model.Drink;
import com.tunyakinis.model.Meal;
import com.tunyakinis.model.Order;
import com.tunyakinis.model.OrderedItem;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ViewBuilder {

  private static final String NEW_LINE = "\n";

  public String buildLunchMenu(List<Cuisine> cuisineList, List<Meal> mainCourseList,
      List<Meal> dessertList) {
    StringBuilder result = new StringBuilder();

    for (Cuisine cuisine : cuisineList) {
      appendCuisineHeader(result, cuisine);
      appendMainCourseMenu(result, mainCourseList, cuisine);
      appendDessertMenu(result, dessertList, cuisine);

      result.append(NEW_LINE);
    }

    return result.toString();
  }

  public String buildDrinkMenu(List<Drink> drinkList) {
    StringBuilder result = new StringBuilder();
    appendDrinkMenuHeader(result);

    for (Drink drink : drinkList) {
      appendDrinkDetails(result, drink);
    }

    result.append(NEW_LINE);

    return result.toString();
  }

  public String buildOrdersReport(List<Order> orderList) {
    StringBuilder result = new StringBuilder();

    appendAllOrdersHeader(result);

    for (Order order : orderList) {
      appendOrderDetails(result, order);
    }

    double allOrdersSum = calculateAllOrdersSum(orderList);
    appendAllOrdersSum(result, allOrdersSum);

    return result.toString();
  }

  public String buildSingleOrderReport(List<OrderedItem> orderedItemList) {
    StringBuilder result = new StringBuilder();
    double finalPrice = calculateFinalPrice(orderedItemList, result);
    appendBillHeader(result);

    for (OrderedItem orderedItem : orderedItemList) {
      appendOrderedItemDetails(result, orderedItem);
    }

    appendSummary(result, finalPrice);

    return result.toString();
  }

  private void appendCuisineHeader(StringBuilder result, Cuisine cuisine) {
    result.append(NEW_LINE)
        .append("CUISINE NAME: ")
        .append(cuisine.getName())
        .append(NEW_LINE)
        .append(NEW_LINE);
  }

  private void appendMainCourseMenu(StringBuilder result, List<Meal> mainCourseList,
      Cuisine cuisine) {
    result.append("MAIN COURSE MENU: ");

    mainCourseList.stream()
        .filter(mainCourse -> cuisine.getName().equalsIgnoreCase(mainCourse.getCuisine().getName()))
        .filter(mainCourse -> "main course".equalsIgnoreCase(mainCourse.getType()))
        .forEach(mainCourse -> {
          result.append(NEW_LINE)
              .append(mainCourse.getName())
              .append(": ")
              .append(mainCourse.getPrice().toString());
        });

    result.append(NEW_LINE)
        .append(NEW_LINE);
  }

  private void appendDessertMenu(StringBuilder result, List<Meal> dessertList, Cuisine cuisine) {
    result.append("DESSERT MENU: ");

    dessertList.stream()
        .filter(dessert -> cuisine.getName().equalsIgnoreCase(dessert.getCuisine().getName()))
        .filter(dessert -> "dessert".equalsIgnoreCase(dessert.getType()))
        .forEach(dessert -> {
          result.append(NEW_LINE)
              .append(dessert.getName())
              .append(": ")
              .append(dessert.getPrice().toString());
        });
    result.append(NEW_LINE);
  }

  private void appendDrinkMenuHeader(StringBuilder result) {
    result.append(NEW_LINE)
        .append("DRINK MENU: ")
        .append(NEW_LINE);
  }

  private void appendDrinkDetails(StringBuilder result, Drink drink) {
    result.append(NEW_LINE)
        .append(drink.getName())
        .append(": ")
        .append(drink.getPrice().toString());
  }

  private void appendAllOrdersHeader(StringBuilder result) {
    result.append(NEW_LINE)
        .append("ALL ORDERS: ")
        .append(NEW_LINE);
  }

  private void appendOrderDetails(StringBuilder result, Order order) {
    List<OrderedItem> orderedItemList = order.getOrderedItems();
    result.append(NEW_LINE)
        .append("ORDER: ")
        .append(order.getId())
        .append(NEW_LINE)
        .append(buildSingleOrderReport(orderedItemList));
  }

  private double calculateAllOrdersSum(List<Order> orderList) {
    return orderList.stream()
        .mapToDouble(Order::getSummaryPrice)
        .sum();
  }

  private void appendAllOrdersSum(StringBuilder result, double allOrdersSum) {
    result.append(NEW_LINE)
        .append("ALL ORDERS SUM: ")
        .append(allOrdersSum)
        .append(NEW_LINE);
  }

  private double calculateFinalPrice(List<OrderedItem> orderedItemList, StringBuilder result) {
    double finalPrice = orderedItemList.stream()
        .mapToDouble(OrderedItem::getPrice)
        .sum();

    result.append(NEW_LINE)
        .append("SUMMARY: ")
        .append(finalPrice)
        .append(NEW_LINE);

    return finalPrice;
  }

  private void appendBillHeader(StringBuilder result) {
    result.append(NEW_LINE)
        .append("BILL: ")
        .append(NEW_LINE)
        .append(NEW_LINE);
  }

  private void appendOrderedItemDetails(StringBuilder result, OrderedItem orderedItem) {
    result.append(NEW_LINE)
        .append(orderedItem.getName())
        .append(": ")
        .append(orderedItem.getPrice());

    if (orderedItem.getItemType().equals("drink")) {
      result.append(", ADDITION: ")
          .append(orderedItem.getAddition());
    }
  }

  private void appendSummary(StringBuilder result, double finalPrice) {
    result.append(NEW_LINE)
        .append(NEW_LINE)
        .append("SUMMARY: ")
        .append(finalPrice)
        .append(NEW_LINE);
  }
}
