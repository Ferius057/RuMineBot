package com.vk.api.sdk.objects.newsfeed.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import java.util.Objects;

/**
 * IgnoreItemResponse object
 */
public class IgnoreItemResponse implements Validable {
    @SerializedName("status")
    @Required
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public IgnoreItemResponse setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IgnoreItemResponse ignoreItemResponse = (IgnoreItemResponse) o;
        return Objects.equals(status, ignoreItemResponse.status);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("IgnoreItemResponse{");
        sb.append("status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
