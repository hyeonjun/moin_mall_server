package com.buying.back.application.product.service.vo;

public class ItemOptionVO extends OptionDefaultVO {
    private String value;

    public ItemOptionVO(Long id, String name, String value) {
        super(id, name);
        this.value = value;
    }
}
