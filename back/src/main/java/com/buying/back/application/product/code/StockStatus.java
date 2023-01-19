package com.buying.back.application.product.code;

public enum StockStatus {
    NORMAL("정상"),
    DEADLINE("품절임박"),
    SOLD_OUT("품절"),
    ;

    private final String status;

    StockStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
