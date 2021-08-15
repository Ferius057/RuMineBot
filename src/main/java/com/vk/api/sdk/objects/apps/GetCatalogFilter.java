package com.vk.api.sdk.objects.apps;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * 'installed' — to return list of installed apps (only for mobile platform).
 */
public enum GetCatalogFilter implements EnumParam {
    @SerializedName("favorite")
    FAVORITE("favorite"),

    @SerializedName("featured")
    FEATURED("featured"),

    @SerializedName("installed")
    INSTALLED("installed"),

    @SerializedName("new")
    NEW("new");

    private final String value;

    GetCatalogFilter(String value) {
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
