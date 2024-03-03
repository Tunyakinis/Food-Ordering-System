package com.tunyakinis.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumAddition {

  ICE_CUBES("ice cubes"),
  LEMON("lemon");

  private final String addition;
}
