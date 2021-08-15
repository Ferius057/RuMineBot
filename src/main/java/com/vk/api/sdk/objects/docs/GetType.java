package com.vk.api.sdk.objects.docs;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum GetType implements EnumParam {
    @SerializedName("0")
    ALL(0),

    @SerializedName("1")
    TEXT(1),

    @SerializedName("2")
    ARCHIVE(2),

    @SerializedName("3")
    GIF(3),

    @SerializedName("4")
    IMAGE(4),

    @SerializedName("5")
    AUDIO(5),

    @SerializedName("6")
    VIDEO(6),

    @SerializedName("7")
    EBOOK(7),

    @SerializedName("8")
    DEFAULT(8);

    private final Integer value;

    GetType(Integer value) {
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
