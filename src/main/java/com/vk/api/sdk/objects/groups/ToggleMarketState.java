package com.vk.api.sdk.objects.groups;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum ToggleMarketState implements EnumParam {
    @SerializedName("advanced")
    ADVANCED("advanced"),

    @SerializedName("basic")
    BASIC("basic"),

    @SerializedName("none")
    NONE("none");

    private final String value;

    ToggleMarketState(String value) {
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
