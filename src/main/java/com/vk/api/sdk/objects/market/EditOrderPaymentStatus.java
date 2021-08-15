package com.vk.api.sdk.objects.market;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum EditOrderPaymentStatus implements EnumParam {
    @SerializedName("not_paid")
    NOT_PAID("not_paid"),

    @SerializedName("paid")
    PAID("paid"),

    @SerializedName("returned")
    RETURNED("returned");

    private final String value;

    EditOrderPaymentStatus(String value) {
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
