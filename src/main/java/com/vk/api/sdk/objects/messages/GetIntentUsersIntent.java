package com.vk.api.sdk.objects.messages;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

public enum GetIntentUsersIntent implements EnumParam {
    @SerializedName("confirmed_notification")
    CONFIRMED_NOTIFICATION("confirmed_notification"),

    @SerializedName("non_promo_newsletter")
    NON_PROMO_NEWSLETTER("non_promo_newsletter"),

    @SerializedName("promo_newsletter")
    PROMO_NEWSLETTER("promo_newsletter");

    private final String value;

    GetIntentUsersIntent(String value) {
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
