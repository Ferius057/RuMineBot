package com.vk.api.sdk.objects.polls;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum GetByIdNameCase implements EnumParam {
    @SerializedName("abl")
    ABL("abl"),

    @SerializedName("acc")
    ACC("acc"),

    @SerializedName("dat")
    DAT("dat"),

    @SerializedName("gen")
    GEN("gen"),

    @SerializedName("ins")
    INS("ins"),

    @SerializedName("nom")
    NOM("nom");

    private final String value;

    GetByIdNameCase(String value) {
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
