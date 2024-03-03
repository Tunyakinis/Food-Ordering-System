package com.tunyakinis.exception;

public class EmptyOrdersListException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "No orders have been placed";

  public EmptyOrdersListException() {
    this(DEFAULT_MESSAGE);
  }

  public EmptyOrdersListException(String message) {
    super(message);
  }

  public EmptyOrdersListException(String message, Exception e) {
    super(message, e);
  }
}
