package com.vk.api.sdk.objects.market;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Sort order ('asc' — from old to new, 'desc' — from new to old)
 */
public enum GetCommentsSort implements EnumParam {
    @SerializedName("asc")
    OLD_TO_NEW("asc"),

    @SerializedName("desc")
    NEW_TO_OLD("desc");

    private final String value;

    GetCommentsSort(String value) {
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
