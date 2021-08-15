package com.vk.api.sdk.objects.video;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum EditAlbumPrivacy implements EnumParam {
    @SerializedName("0")
    ALL("0"),

    @SerializedName("1")
    FRIENDS("1"),

    @SerializedName("2")
    FRIENDS_OF_FRIENDS("2"),

    @SerializedName("3")
    ONLY_ME("3");

    private final String value;

    EditAlbumPrivacy(String value) {
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
