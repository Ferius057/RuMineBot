package com.vk.api.sdk.objects.store;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import java.net.URI;
import java.util.Objects;

/**
 * ProductIcon object
 */
public class ProductIcon implements Validable {
    @SerializedName("id")
    private String id;

    /**
     * Image height
     */
    @SerializedName("height")
    @Required
    private Integer height;

    /**
     * Image url
     */
    @SerializedName("url")
    @Required
    private URI url;

    /**
     * Image width
     */
    @SerializedName("width")
    @Required
    private Integer width;

    public String getId() {
        return id;
    }

    public ProductIcon setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public ProductIcon setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public URI getUrl() {
        return url;
    }

    public ProductIcon setUrl(URI url) {
        this.url = url;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public ProductIcon setWidth(Integer width) {
        this.width = width;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, id, url, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductIcon productIcon = (ProductIcon) o;
        return Objects.equals(width, productIcon.width) &&
                Objects.equals(id, productIcon.id) &&
                Objects.equals(url, productIcon.url) &&
                Objects.equals(height, productIcon.height);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("ProductIcon{");
        sb.append("width=").append(width);
        sb.append(", id='").append(id).append("'");
        sb.append(", url=").append(url);
        sb.append(", height=").append(height);
        sb.append('}');
        return sb.toString();
    }
}
