package com.vk.api.sdk.objects.groups;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Sort order. Available values: 'id_asc', 'id_desc', 'time_asc', 'time_desc'. 'time_asc' and 'time_desc' are availavle only if the method is called by the group's 'moderator'.
 */
public enum GetMembersSort implements EnumParam {
    @SerializedName("id_asc")
    ID_ASC("id_asc"),

    @SerializedName("id_desc")
    ID_DESC("id_desc"),

    @SerializedName("time_asc")
    TIME_ASC("time_asc"),

    @SerializedName("time_desc")
    TIME_DESC("time_desc");

    private final String value;

    GetMembersSort(String value) {
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
