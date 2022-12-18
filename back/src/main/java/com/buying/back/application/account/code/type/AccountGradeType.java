package com.buying.back.application.account.code.type;

public enum AccountGradeType {

  LV1("NEWBIE", 1, 0),
  LV2("ROOKIE", 1, 1),
  LV3("MEMBER", 2, 1),
  LV4("BRONZE", 2, 2),
  LV5("SILVER", 3, 2),
  LV6("GOLD", 3, 3),
  LV7("PLATINUM", 4, 3),
  LV8("DIAMOND", 4, 4),
  ;

  private final String value;
  private final int addDiscount;
  private final int addAccumulate;

  AccountGradeType(String value, int addDiscount, int addAccumulate) {
    this.value = value;
    this.addDiscount = addDiscount;
    this.addAccumulate = addAccumulate;
  }

  public String getValue() {
    return value;
  }

  public int getAddDiscount() {
    return addDiscount;
  }

  public int getAddAccumulate() {
    return addAccumulate;
  }
}
