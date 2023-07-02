package com.thanathip.training.backend.exception;

import java.io.Serial;

public class ProductException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProductException(String message) {
        super("product exception: " + message);
    }

    public static ProductException notFound() {
        return new ProductException("product.notfound");
    }


}
