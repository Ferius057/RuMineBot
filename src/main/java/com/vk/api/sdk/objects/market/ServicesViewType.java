package com.vk.api.sdk.objects.market;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Type of view. 1 - cards, 2 - rows
 */
public enum ServicesViewType implements EnumParam {
    @SerializedName("1")
    CARDS(1),

    @SerializedName("2")
    ROWS(2);

    private final Integer value;

    ServicesViewType(Integer value) {
        this.value = value;
    }

    public String getValue() {
        return value.toString();
    }

    @Override
    public String toString() {
        return value.toString().toLowerCase();
    }
}
