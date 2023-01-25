package com.buying.back.application.product.domain;

import com.buying.back.application.product.controller.dto.OptionDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Option {
    @Id @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    @Column(length = 191, nullable = false)
    private String name;
    private int order;

    // 크게 변경점이 일어나지 않을 것으로 판단 양방향 설정함
    // 쿼리 수를 줄이기 위함, 조회의 간편함
    // 단점 : N + 1 문제 해결을 요함
    @OneToMany(mappedBy = "option", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<OptionDetail> details = new ArrayList<>();

    @Builder
    public Option(Long productId, String name, int order) {
        this.productId = productId;
        this.name = name;
        this.order = order;
    }

    public static Option create(OptionDto.Create create) {
        return Option.builder()
                .name(create.getOptionName())
                .build();
    }
}
