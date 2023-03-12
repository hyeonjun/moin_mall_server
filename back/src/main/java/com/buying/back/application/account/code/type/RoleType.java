package com.buying.back.application.account.code.type;

import java.util.EnumSet;
import java.util.Set;

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

  private static final Set<RoleType> BRAND_ROLE_TYPE = EnumSet.of(BRAND_ADMIN, BRAND_CREW);

  public static boolean isBrandRoleType(RoleType type) {
    return BRAND_ROLE_TYPE.contains(type);
  }
}
