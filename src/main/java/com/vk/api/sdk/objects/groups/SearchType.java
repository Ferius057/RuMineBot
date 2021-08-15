package com.vk.api.sdk.objects.groups;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Community type. Possible values: 'group, page, event.'
 */
public enum SearchType implements EnumParam {
    @SerializedName("group")
    GROUP("group"),

    @SerializedName("page")
    PAGE("page"),

    @SerializedName("event")
    EVENT("event");

    private final String value;

    SearchType(String value) {
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
