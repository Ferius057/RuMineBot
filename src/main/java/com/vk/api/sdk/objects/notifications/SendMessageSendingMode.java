package com.vk.api.sdk.objects.notifications;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Type of sending (delivering) notifications: 'immediately' — push and bell notifications will be delivered as soon as possible, 'delayed' — push and bell notifications will be delivered in the most comfortable time for the user, 'delayed_push' — only push notifications will be delivered in the most comfortable time, while the bell notifications will be delivered as soon as possible
 */
public enum SendMessageSendingMode implements EnumParam {
    @SerializedName("delayed")
    DELAYED("delayed"),

    @SerializedName("delayed_push")
    DELAYED_PUSH("delayed_push"),

    @SerializedName("immediately")
    IMMEDIATELY("immediately");

    private final String value;

    SendMessageSendingMode(String value) {
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
