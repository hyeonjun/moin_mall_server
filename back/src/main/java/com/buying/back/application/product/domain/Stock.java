package com.buying.back.application.product.domain;

import com.buying.back.application.product.code.StockStatus;
import com.buying.back.application.product.controller.dto.StockDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Stock {
    @Id @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private Long productId;
    @Column(nullable = false)
    private String optionCombination;
    @Column(nullable = false)
    private Integer quantity;
    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer price;
    @ColumnDefault("0")
    private Integer discountPrice;
    @ColumnDefault("0")
    private Integer discountRate;
    private StockStatus status;

    // TODO: 2023-01-18 재고수량, 가격, 할인가격, 할인율, 관련 Time 필드가 필요하지 않을까 (할인시작시간, 재고수량 변경 일자 등)

    @Builder
    private Stock(Long productId, String optionCombination, Integer quantity, Integer price, Integer discountPrice, Integer discountRate, StockStatus status) {
        this.productId = productId;
        this.optionCombination = optionCombination;
        this.quantity = quantity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.discountRate = discountRate;
        this.status = status;
    }

    public static Stock create(StockDto.Create create) {
        return Stock.builder()
                .productId(create.getProductId())
                .optionCombination(create.getOptionCombination())
                .quantity(create.getQuantity())
                .price(create.getPrice())
                .discountPrice(create.getDiscountPrice())
                .discountRate(create.getDiscountRate())
                .status(StockStatus.NORMAL)
                .build();
    }
}
