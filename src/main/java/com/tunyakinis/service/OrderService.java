package com.tunyakinis.service;

import com.tunyakinis.model.Order;
import com.tunyakinis.model.OrderedItem;
import java.util.List;

public interface OrderService {

  void orderLunch();

  void orderDrink();

  List<OrderedItem> submitOrder();

  void cleanBucket();

  List<Order> displayOrdersReport();
}
