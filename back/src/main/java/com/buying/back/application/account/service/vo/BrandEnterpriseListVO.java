package com.buying.back.application.account.service.vo;

import com.buying.back.application.account.domain.Brand;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandEnterpriseListVO {

  private Long brandId;
  private String brandName;
  private String url;

  private String representativeName;
  private String representativeEmail;

  private Boolean activated;

  @QueryProjection
  public BrandEnterpriseListVO(Long brandId, String brandName, String url,
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
