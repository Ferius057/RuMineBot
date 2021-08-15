package com.vk.api.sdk.exceptions;

public class ApiStickersNotPurchasedException extends ApiException {
    public ApiStickersNotPurchasedException(String message) {
        super(2100, "Stickers are not purchased", message);
    }
}
