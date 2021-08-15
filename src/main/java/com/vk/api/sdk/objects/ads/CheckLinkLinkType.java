package com.vk.api.sdk.objects.ads;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Object type: *'community' — community,, *'post' — community post,, *'application' — VK application,, *'video' — video,, *'site' — external site.
 */
public enum CheckLinkLinkType implements EnumParam {
    @SerializedName("community")
    COMMUNITY("community"),

    @SerializedName("post")
    POST("post"),

    @SerializedName("application")
    APPLICATION("application"),

    @SerializedName("video")
    VIDEO("video"),

    @SerializedName("site")
    SITE("site");

    private final String value;

    CheckLinkLinkType(String value) {
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
