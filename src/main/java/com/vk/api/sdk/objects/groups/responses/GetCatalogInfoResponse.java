package com.vk.api.sdk.objects.groups.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.groups.GroupCategory;
import java.util.List;
import java.util.Objects;

/**
 * GetCatalogInfoResponse object
 */
public class GetCatalogInfoResponse implements Validable {
    /**
     * Information whether catalog is enabled for current user
     */
    @SerializedName("enabled")
    @Required
    private BoolInt enabled;

    @SerializedName("categories")
    private List<GroupCategory> categories;

    public boolean isEnabled() {
        return enabled == BoolInt.YES;
    }

    public BoolInt getEnabled() {
        return enabled;
    }

    public List<GroupCategory> getCategories() {
        return categories;
    }

    public GetCatalogInfoResponse setCategories(List<GroupCategory> categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories, enabled);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCatalogInfoResponse getCatalogInfoResponse = (GetCatalogInfoResponse) o;
        return Objects.equals(categories, getCatalogInfoResponse.categories) &&
                Objects.equals(enabled, getCatalogInfoResponse.enabled);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("GetCatalogInfoResponse{");
        sb.append("categories=").append(categories);
        sb.append(", enabled=").append(enabled);
        sb.append('}');
        return sb.toString();
    }
}
