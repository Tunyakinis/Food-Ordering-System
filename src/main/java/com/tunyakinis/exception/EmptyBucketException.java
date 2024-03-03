package com.tunyakinis.exception;

public class EmptyBucketException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Bucket is empty";

  public EmptyBucketException() {
    this(DEFAULT_MESSAGE);
  }

  public EmptyBucketException(String message) {
    super(message);
  }

  public EmptyBucketException(String message, Exception e) {
    super(message, e);
  }
}
