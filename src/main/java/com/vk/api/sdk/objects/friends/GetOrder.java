package com.vk.api.sdk.objects.friends;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Sort order: , 'name' — by name (enabled only if the 'fields' parameter is used), 'hints' — by rating, similar to how friends are sorted in My friends section, , This parameter is available only for [vk.com/dev/standalone|desktop applications].
 */
public enum GetOrder implements EnumParam {
    @SerializedName("hints")
    HINTS("hints"),

    @SerializedName("random")
    RANDOM("random"),

    @SerializedName("mobile")
    MOBILE("mobile"),

    @SerializedName("name")
    NAME("name"),

    @SerializedName("smart")
    SMART("smart");

    private final String value;

    GetOrder(String value) {
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
