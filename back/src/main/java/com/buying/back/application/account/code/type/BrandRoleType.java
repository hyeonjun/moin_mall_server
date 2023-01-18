package com.buying.back.application.account.code.type;

public enum BrandRoleType {

  ADMIN("ADMIN"),

  CREW("CREW");

  private final String value;

  BrandRoleType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
