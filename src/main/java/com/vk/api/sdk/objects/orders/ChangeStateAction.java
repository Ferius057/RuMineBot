package com.vk.api.sdk.objects.orders;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * action to be done with the order. Available actions: *cancel — to cancel unconfirmed order. *charge — to confirm unconfirmed order. Applies only if processing of [vk.com/dev/payments_status|order_change_state] notification failed. *refund — to cancel confirmed order.
 */
public enum ChangeStateAction implements EnumParam {
    @SerializedName("cancel")
    CANCEL("cancel"),

    @SerializedName("charge")
    CHARGE("charge"),

    @SerializedName("refund")
    REFUND("refund");

    private final String value;

    ChangeStateAction(String value) {
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
