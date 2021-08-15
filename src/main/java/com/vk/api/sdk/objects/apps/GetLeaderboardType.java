package com.vk.api.sdk.objects.apps;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Leaderboard type. Possible values: *'level' — by level,, *'points' — by mission points,, *'score' — by score ().
 */
public enum GetLeaderboardType implements EnumParam {
    @SerializedName("level")
    LEVEL("level"),

    @SerializedName("points")
    POINTS("points"),

    @SerializedName("score")
    SCORE("score");

    private final String value;

    GetLeaderboardType(String value) {
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
