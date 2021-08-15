package com.vk.api.sdk.objects.messages;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * 'typing' — user has started to type.
 */
public enum SetActivityType implements EnumParam {
    @SerializedName("audiomessage")
    AUDIOMESSAGE("audiomessage"),

    @SerializedName("file")
    FILE("file"),

    @SerializedName("photo")
    PHOTO("photo"),

    @SerializedName("typing")
    TYPING("typing"),

    @SerializedName("video")
    VIDEO("video");

    private final String value;

    SetActivityType(String value) {
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
