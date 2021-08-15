package com.vk.api.sdk.objects.market;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum SearchStatus implements EnumParam {
    @SerializedName("0")
    ACTIVE(0),

    @SerializedName("2")
    DISABLED(2);

    private final Integer value;

    SearchStatus(Integer value) {
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
