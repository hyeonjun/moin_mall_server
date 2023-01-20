package com.buying.back.application.account.domain;

import com.buying.back.application.account.controller.dto.CreateBrandDTO;
import com.buying.back.application.common.domain.Base;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "brand",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "company_registration_number")
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

    @Column(name = "president" , length = 191, nullable = false)
    private String president;

    @Column(name = "company_registration_number", nullable = false)
    private String companyRegistrationNumber;

    @Column(name = "site_url")
    private String url;

    @Builder(builderClassName = "init" , builderMethodName = "init")
    public Brand(CreateBrandDTO dto) {
        this.brandName = dto.getBrandName();
        this.president = dto.getPresident();
        this.companyRegistrationNumber = dto.getCompanyRegistrationNumber();
        if(dto.getSiteUrl() != null)
            this.url = dto.getSiteUrl();
        else this.url = null;
    }

}
