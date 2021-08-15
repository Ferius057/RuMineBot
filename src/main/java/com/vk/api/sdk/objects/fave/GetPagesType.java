package com.vk.api.sdk.objects.fave;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum GetPagesType implements EnumParam {
    @SerializedName("groups")
    GROUPS("groups"),

    @SerializedName("hints")
    HINTS("hints"),

    @SerializedName("users")
    USERS("users");

    private final String value;

    GetPagesType(String value) {
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
