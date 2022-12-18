package com.buying.back.application.account.code.type;

public enum RoleType {

  ADMIN("ADMIN"),
  USER("USER");

  private final String value;

  RoleType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
