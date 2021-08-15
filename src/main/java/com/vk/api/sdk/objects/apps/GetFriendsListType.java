package com.vk.api.sdk.objects.apps;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * List type. Possible values: * 'invite' — available for invites (don't play the game),, * 'request' — available for request (play the game). By default: 'invite'.
 */
public enum GetFriendsListType implements EnumParam {
    @SerializedName("invite")
    INVITE("invite"),

    @SerializedName("request")
    REQUEST("request");

    private final String value;

    GetFriendsListType(String value) {
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
