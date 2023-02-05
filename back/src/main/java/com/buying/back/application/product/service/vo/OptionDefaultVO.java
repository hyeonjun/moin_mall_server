package com.buying.back.application.product.service.vo;

import com.buying.back.application.product.domain.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OptionDefaultVO {
    private Long id;
    private String name;

    public OptionDefaultVO(Option option) {
        this.id = option.getId();
        this.name = option.getName();
    }
}
