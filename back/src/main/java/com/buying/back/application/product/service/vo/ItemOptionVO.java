package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Option;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemOptionVO extends OptionDefaultVO {
    private String value;

    @QueryProjection
    public ItemOptionVO(Option option) {
        super(option.getId(), option.getName(), option.getOrderBy());
        this.value = option.getValue();
    }
}
