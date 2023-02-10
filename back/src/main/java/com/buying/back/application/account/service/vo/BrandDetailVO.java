package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.domain.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandDetailVO {

  protected Long brandId;
  protected String brandName;
  protected String representativeName;
  protected String representativeEmail;
  protected String businessNumber;
  protected String url;

  public BrandDetailVO(Brand brand) {
    this.brandId = brand.getId();
    this.brandName = brand.getBrandName();
    this.representativeName = brand.getRepresentativeName();
    this.representativeEmail = brand.getRepresentativeEmail();
    this.businessNumber = brand.getBusinessNumber();
    this.url = brand.getUrl();
  }
}
