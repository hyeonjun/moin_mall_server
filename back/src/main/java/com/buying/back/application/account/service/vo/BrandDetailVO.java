package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.domain.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandDetailVO {

  protected Long brandId;
  protected String brandName;
  protected String representativeName;
  protected String representativeEmail;
  protected String businessNumber;
  protected String url;

  public static BrandDetailVO valueOf(Brand brand) {
    BrandDetailVO vo = new BrandDetailVO();

    vo.setBrandId(brand.getId());
    vo.setBrandName(brand.getBrandName());
    vo.setRepresentativeName(brand.getRepresentativeName());
    vo.setRepresentativeEmail(brand.getRepresentativeEmail());
    vo.setBusinessNumber(brand.getBusinessNumber());
    vo.setUrl(brand.getUrl());

    return vo;
  }

  protected BrandDetailVO(Brand brand) {
    this.brandId = brand.getId();
    this.brandName = brand.getBrandName();
    this.representativeName = brand.getRepresentativeName();
    this.representativeEmail = brand.getRepresentativeEmail();
    this.businessNumber = brand.getBusinessNumber();
    this.url = brand.getUrl();
  }
}
