package com.buying.back.application.account.controller.dto.brand;

public interface CreateBrandDTO {

  String getBusinessNumber();
  String getBrandPassword();
  String getAccountEmail();
  String getAccountName();
  String getAccountPassword();

  void setBrandPassword(String password);
  void setAccountPassword(String password);

}
