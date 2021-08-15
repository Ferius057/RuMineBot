package com.vk.api.sdk.objects.video;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Sort order: 'asc' — oldest comment first, 'desc' — newest comment first
 */
public enum GetCommentsSort implements EnumParam {
    @SerializedName("asc")
    OLDEST_COMMENT_FIRST("asc"),

    @SerializedName("desc")
    NEWEST_COMMENT_FIRST("desc");

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
