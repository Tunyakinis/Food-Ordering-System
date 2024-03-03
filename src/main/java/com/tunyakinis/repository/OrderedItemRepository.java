package com.tunyakinis.repository;

import com.tunyakinis.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {

}
