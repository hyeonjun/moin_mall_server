package com.buying.back.application.account.code.type;

public enum RoleType {

  SYSTEM("SYSTEM"),
  USER("USER"),
  BRAND("BRAND");

  private final String value;

  RoleType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
