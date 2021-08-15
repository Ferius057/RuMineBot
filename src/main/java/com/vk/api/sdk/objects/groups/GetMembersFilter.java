package com.vk.api.sdk.objects.groups;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * *'friends' - only friends in this community will be returned,, *'unsure' - only those who pressed 'I may attend' will be returned (if it's an event).
 */
public enum GetMembersFilter implements EnumParam {
    @SerializedName("friends")
    FRIENDS("friends"),

    @SerializedName("unsure")
    UNSURE("unsure"),

    @SerializedName("managers")
    MANAGERS("managers"),

    @SerializedName("donut")
    DONUT("donut");

    private final String value;

    GetMembersFilter(String value) {
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
