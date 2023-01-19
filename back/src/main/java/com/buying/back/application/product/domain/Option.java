package com.buying.back.application.product.domain;

import com.buying.back.application.product.controller.dto.OptionDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Option {
    @Id @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 191, nullable = false)
    private String name;
    @Column(length = 191, nullable = false)
    private String value;
    @Column(nullable = false)
    private Long productId;

    @Builder
    private Option(String name, String value, Long productId) {
        this.name = name;
        this.value = value;
        this.productId = productId;
    }

    public static Option create(OptionDto.Create create) {
        return Option.builder()
                .name(create.getOptionName())
                .value(create.getOptionValue())
                .productId(create.getProductId())
                .build();
    }
}
