package com.vk.api.sdk.exceptions;

public class ApiStickersTooManyFavoritesException extends ApiException {
    public ApiStickersTooManyFavoritesException(String message) {
        super(2101, "Too many favorite stickers", message);
    }
}
