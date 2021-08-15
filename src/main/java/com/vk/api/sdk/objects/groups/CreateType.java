package com.vk.api.sdk.objects.groups;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Community type. Possible values: *'group' - group,, *'event' - event,, *'public' - public page
 */
public enum CreateType implements EnumParam {
    @SerializedName("event")
    EVENT("event"),

    @SerializedName("group")
    GROUP("group"),

    @SerializedName("public")
    PUBLIC("public");

    private final String value;

    CreateType(String value) {
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
