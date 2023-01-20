package com.buying.back.application.account.code.type;

public enum RoleType {

  SYSTEM("SYSTEM"),
  NORMAL("NORMAL"),
  BRAND_ADMIN("BRAND_ADMIN"),
  BRAND_CREW("BRAND_CREW");

  private final String value;

  RoleType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
