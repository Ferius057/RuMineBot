package com.vk.api.sdk.objects.streaming;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum SetSettingsMonthlyTier implements EnumParam {
    @SerializedName("tier_1")
    TIER_1("tier_1"),

    @SerializedName("tier_2")
    TIER_2("tier_2"),

    @SerializedName("tier_3")
    TIER_3("tier_3"),

    @SerializedName("tier_4")
    TIER_4("tier_4"),

    @SerializedName("tier_5")
    TIER_5("tier_5"),

    @SerializedName("tier_6")
    TIER_6("tier_6"),

    @SerializedName("unlimited")
    UNLIMITED("unlimited");

    private final String value;

    SetSettingsMonthlyTier(String value) {
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
