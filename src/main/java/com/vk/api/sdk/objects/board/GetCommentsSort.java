package com.vk.api.sdk.objects.board;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Sort order: 'asc' — by creation date in chronological order, 'desc' — by creation date in reverse chronological order,
 */
public enum GetCommentsSort implements EnumParam {
    @SerializedName("asc")
    CHRONOLOGICAL("asc"),

    @SerializedName("desc")
    REVERSE_CHRONOLOGICAL("desc");

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
