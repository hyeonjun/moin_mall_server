package com.buying.back.application.account.domain;

import com.buying.back.application.account.controller.dto.brand.CreateBrandAdminAccountDTO;
import com.buying.back.application.common.domain.Base;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.Setter;

@Entity
@Table(
  name = "brand",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "business_number"),
    @UniqueConstraint(columnNames = "url")
  }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "brand_id")
  private Long id;

  @Column(name = "brand_name", length = 100, nullable = false)
  private String brandName;

  @Column(name = "representative_name", length = 191, nullable = false)
  private String representativeName;

  @Column(name = "representative_email", length = 191, nullable = false)
  private String representativeEmail;

  @Column(name = "business_number", nullable = false)
  private String businessNumber;

  @Column(name = "brand_password", length = 100, nullable = false)
  private String password;

  @Column(name = "url")
  private String url;

  @Setter
  @Column(name = "activated", nullable = false)
  private boolean activated;

  @Builder(builderClassName = "initBrand", builderMethodName = "initBrand")
  public Brand(CreateBrandAdminAccountDTO dto) {
    this.brandName = dto.getBrandName();
    this.representativeName = dto.getRepresentativeName();
    this.representativeEmail = dto.getRepresentativeEmail();
    this.businessNumber = dto.getBusinessNumber();
    this.password = dto.getBrandPassword();
    this.url = dto.getUrl();
    this.activated = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Brand)) {
      return false;
    }
    Brand brand = (Brand) o;
    return Objects.equals(getId(), brand.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
