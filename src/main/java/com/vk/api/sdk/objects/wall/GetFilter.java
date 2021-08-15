package com.vk.api.sdk.objects.wall;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Filter to apply: 'owner' — posts by the wall owner, 'others' — posts by someone else, 'all' — posts by the wall owner and others (default), 'postponed' — timed posts (only available for calls with an 'access_token'), 'suggests' — suggested posts on a community wall
 */
public enum GetFilter implements EnumParam {
    @SerializedName("owner")
    OWNER("owner"),

    @SerializedName("others")
    OTHERS("others"),

    @SerializedName("all")
    ALL("all"),

    @SerializedName("postponed")
    POSTPONED("postponed"),

    @SerializedName("suggests")
    SUGGESTS("suggests"),

    @SerializedName("archived")
    ARCHIVED("archived"),

    @SerializedName("donut")
    DONUT("donut");

    private final String value;

    GetFilter(String value) {
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
