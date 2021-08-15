package com.vk.api.sdk.objects.utils;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Interval.
 */
public enum GetLinkStatsInterval implements EnumParam {
    @SerializedName("day")
    DAY("day"),

    @SerializedName("forever")
    FOREVER("forever"),

    @SerializedName("hour")
    HOUR("hour"),

    @SerializedName("month")
    MONTH("month"),

    @SerializedName("week")
    WEEK("week");

    private final String value;

    GetLinkStatsInterval(String value) {
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
