package com.vk.api.sdk.objects.podcast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import java.util.List;
import java.util.Objects;

/**
 * Cover object
 */
public class Cover implements Validable {
    @SerializedName("sizes")
    private List<PhotoSizes> sizes;

    public List<PhotoSizes> getSizes() {
        return sizes;
    }

    public Cover setSizes(List<PhotoSizes> sizes) {
        this.sizes = sizes;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cover cover = (Cover) o;
        return Objects.equals(sizes, cover.sizes);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("Cover{");
        sb.append("sizes=").append(sizes);
        sb.append('}');
        return sb.toString();
    }
}
