package com.tunyakinis.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordered_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order_id"))
  private Order order;

  private Long orderedItemId;

  private String name;

  private String itemType;

  private String addition;

  private Double price;
}
