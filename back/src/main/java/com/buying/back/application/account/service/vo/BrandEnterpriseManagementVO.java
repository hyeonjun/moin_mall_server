package com.buying.back.application.account.service.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandEnterpriseManagementVO {

  private Long brandId;
  private String brandName;
  private String url;

  private String representativeName;
  private String representativeEmail;

  private Boolean activated;

  @QueryProjection
  public BrandEnterpriseManagementVO(Long brandId, String brandName, String url,
    String representativeName,
    String representativeEmail, Boolean activated) {
    this.brandId = brandId;
    this.brandName = brandName;
    this.url = url;
    this.representativeName = representativeName;
    this.representativeEmail = representativeEmail;
    this.activated = activated;
  }
}
