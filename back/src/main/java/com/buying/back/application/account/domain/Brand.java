package com.buying.back.application.account.domain;

import com.buying.back.application.common.domain.Base;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "brand",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "businessRegistrationNumber")
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

    @Column(name = "Business_registration_number", nullable = false)
    private Long businessRegistrationNumber;

    @Column(name = "site_domain")
    private String domain;

    private String logo;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 191, nullable = false)
    private String brandRoleType;




}
