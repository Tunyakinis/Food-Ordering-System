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
@Table(name = "meal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cuisine_id", foreignKey = @ForeignKey(name = "fk_dessert_cuisine"))
  private Cuisine cuisine;

  private String name;

  private Double price;

  private String type;
}
