package com.vk.api.sdk.objects.stickers;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import java.net.URI;
import java.util.Objects;

/**
 * ImageSet object
 */
public class ImageSet implements Validable {
    /**
     * Base URL for images in set
     */
    @SerializedName("base_url")
    private URI baseUrl;

    /**
     * Version number to be appended to the image URL
     */
    @SerializedName("version")
    private Integer version;

    public URI getBaseUrl() {
        return baseUrl;
    }

    public ImageSet setBaseUrl(URI baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public ImageSet setVersion(Integer version) {
        this.version = version;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseUrl, version);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageSet imageSet = (ImageSet) o;
        return Objects.equals(baseUrl, imageSet.baseUrl) &&
                Objects.equals(version, imageSet.version);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("ImageSet{");
        sb.append("baseUrl=").append(baseUrl);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
