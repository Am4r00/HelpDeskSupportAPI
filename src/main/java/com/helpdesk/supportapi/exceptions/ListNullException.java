package com.helpdesk.supportapi.exceptions;

public class ListNullException extends RuntimeException {
    public ListNullException(String message) {
        super(message);
    }

  public ListNullException() {
      super("The list is empty !");
  }
}
