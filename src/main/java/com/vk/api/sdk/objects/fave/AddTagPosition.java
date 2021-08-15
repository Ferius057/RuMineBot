package com.vk.api.sdk.objects.fave;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum AddTagPosition implements EnumParam {
    @SerializedName("back")
    BACK("back"),

    @SerializedName("front")
    FRONT("front");

    private final String value;

    AddTagPosition(String value) {
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
