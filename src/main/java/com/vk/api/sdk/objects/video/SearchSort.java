package com.vk.api.sdk.objects.video;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Sort order: '1' — by duration, '2' — by relevance, '0' — by date added
 */
public enum SearchSort implements EnumParam {
    @SerializedName("1")
    DURATION(1),

    @SerializedName("2")
    RELEVANCE(2),

    @SerializedName("0")
    DATE_ADDED(0);

    private final Integer value;

    SearchSort(Integer value) {
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
