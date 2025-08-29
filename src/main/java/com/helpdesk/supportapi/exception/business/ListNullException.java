package com.helpdesk.supportapi.exception.business;

public class ListNullException extends RuntimeException {
    public ListNullException(String message) {
        super(message);
    }

  public ListNullException() {
      super("The list is empty !");
  }
}
