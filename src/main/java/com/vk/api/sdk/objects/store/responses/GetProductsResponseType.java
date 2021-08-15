package com.vk.api.sdk.objects.store.responses;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Product type
 */
public enum GetProductsResponseType implements EnumParam {
    @SerializedName("stickers")
    STICKERS("stickers");

    private final String value;

    GetProductsResponseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toLowerCase();
    }
}
