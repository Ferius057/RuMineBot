package com.vk.api.sdk.objects.docs;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.queries.EnumParam;

/**
 * Document type.
 */
public enum GetMessagesUploadServerType implements EnumParam {
    @SerializedName("audio_message")
    AUDIO_MESSAGE("audio_message"),

    @SerializedName("doc")
    DOC("doc"),

    @SerializedName("graffiti")
    GRAFFITI("graffiti");

    private final String value;

    GetMessagesUploadServerType(String value) {
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
