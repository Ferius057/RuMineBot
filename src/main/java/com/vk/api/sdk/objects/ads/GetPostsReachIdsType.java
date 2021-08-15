package com.vk.api.sdk.objects.ads;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Type of requested objects listed in 'ids' parameter: *ad — ads,, *campaign — campaigns.
 */
public enum GetPostsReachIdsType implements EnumParam {
    @SerializedName("ad")
    AD("ad"),

    @SerializedName("campaign")
    CAMPAIGN("campaign");

    private final String value;

    GetPostsReachIdsType(String value) {
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
