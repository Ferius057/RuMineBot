package com.vk.api.sdk.objects.friends;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Case for declension of user name and surname: 'nom' — nominative (default), 'gen' — genitive , 'dat' — dative, 'acc' — accusative , 'ins' — instrumental , 'abl' — prepositional
 */
public enum SearchNameCase implements EnumParam {
    @SerializedName("Nom")
    NOMINATIVE("Nom"),

    @SerializedName("Gen")
    GENITIVE("Gen"),

    @SerializedName("Dat")
    DATIVE("Dat"),

    @SerializedName("Acc")
    ACCUSATIVE("Acc"),

    @SerializedName("Ins")
    INSTRUMENTAL("Ins"),

    @SerializedName("Abl")
    PREPOSITIONAL("Abl");

    private final String value;

    SearchNameCase(String value) {
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
