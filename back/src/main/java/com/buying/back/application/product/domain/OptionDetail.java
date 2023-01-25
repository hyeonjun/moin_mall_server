package com.buying.back.application.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OptionDetail {
    @Id @Column(name = "option_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    @Builder
    private OptionDetail(String value) {
        this.value = value;
    }

    public static OptionDetail create(String value) {
        return OptionDetail.builder()
                .value(value)
                .build();
    }
}
