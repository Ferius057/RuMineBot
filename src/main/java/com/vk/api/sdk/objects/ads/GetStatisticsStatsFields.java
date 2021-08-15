package com.vk.api.sdk.objects.ads;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum GetStatisticsStatsFields implements EnumParam {
    @SerializedName("views_times")
    VIEWS_TIMES("views_times");

    private final String value;

    GetStatisticsStatsFields(String value) {
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
