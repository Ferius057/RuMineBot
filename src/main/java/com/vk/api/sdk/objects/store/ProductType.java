package com.vk.api.sdk.objects.store;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Product type
 */
public enum ProductType implements EnumParam {
    @SerializedName("stickers")
    STICKERS("stickers");

    private final String value;

    ProductType(String value) {
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
