package com.vk.api.sdk.objects.video;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum SearchFilters implements EnumParam {
    @SerializedName("youtube")
    YOUTUBE("youtube"),

    @SerializedName("vimeo")
    VIMEO("vimeo"),

    @SerializedName("short")
    SHORT("short"),

    @SerializedName("long")
    LONG("long");

    private final String value;

    SearchFilters(String value) {
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
