package com.buying.back.application.account.domain;

import com.buying.back.application.account.controller.dto.CreateBrandDTO;
import com.buying.back.application.common.domain.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(
  name = "brand",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "business_number")
  },
  indexes = {
    @Index(columnList = "business_number")
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

    @Column(name = "representative_name" , length = 191, nullable = false)
    private String representativeName;

    @Column(name = "business_number", nullable = false)
    private String businessNumber;

    @Column(name = "url")
    private String url;

    @Builder(builderClassName = "initBrand" , builderMethodName = "initBrand")
    public Brand(CreateBrandDTO dto) {
        this.brandName = dto.getBrandName();
        this.representativeName = dto.getRepresentativeName();
        this.businessNumber = dto.getBusinessNumber();
        this.url = dto.getUrl();
    }
}
