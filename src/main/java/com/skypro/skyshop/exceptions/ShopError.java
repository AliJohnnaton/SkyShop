package com.skypro.skyshop.exceptions;

public class ShopError {
    private final String code = "404";
    private final String message;

    public ShopError(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
