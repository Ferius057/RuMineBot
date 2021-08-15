package com.vk.api.sdk.objects.market;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * '0' — do not use reverse order, '1' — use reverse order
 */
public enum SearchRev implements EnumParam {
    @SerializedName("0")
    NORMAL(0),

    @SerializedName("1")
    REVERSE(1);

    private final Integer value;

    SearchRev(Integer value) {
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
