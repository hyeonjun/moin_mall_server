package com.buying.back.application.product.exception;

import com.buying.back.application.common.exception.CommonException;
import com.buying.back.util.response.ResponseCode;

public class ProductException extends CommonException {
    public ProductException(ResponseCode responseCode) {
        super(responseCode);
    }

    public ProductException(ResponseCode responseCode, String detailMessage) {
        super(responseCode, detailMessage);
    }

    public enum ProductExceptionCode implements ResponseCode {
        INVALID_PRODUCT("PEC-001", "invalid account"),
        NOT_FOUND_PRODUCT("PEC-002", "product not found"),
        NOT_FOUND_ITEM("PEC-003", "item not found"),
        NOT_FOUND_OPTION("PEC-004", "option not found"),
        ;

        private final String code;
        private final String message;

        ProductExceptionCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getCode() {
            return null;
        }

        @Override
        public String getMessage() {
            return null;
        }
    }
}
