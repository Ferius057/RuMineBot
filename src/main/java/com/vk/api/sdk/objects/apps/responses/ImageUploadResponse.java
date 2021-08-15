package com.vk.api.sdk.objects.apps.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import java.util.Objects;

/**
 * ImageUploadResponse object
 */
public class ImageUploadResponse implements Validable {
    /**
     * Uploading hash
     */
    @SerializedName("hash")
    private String hash;

    /**
     * Uploaded photo data
     */
    @SerializedName("image")
    private String image;

    public String getHash() {
        return hash;
    }

    public ImageUploadResponse setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ImageUploadResponse setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, hash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageUploadResponse imageUploadResponse = (ImageUploadResponse) o;
        return Objects.equals(image, imageUploadResponse.image) &&
                Objects.equals(hash, imageUploadResponse.hash);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("ImageUploadResponse{");
        sb.append("image='").append(image).append("'");
        sb.append(", hash='").append(hash).append("'");
        sb.append('}');
        return sb.toString();
    }
}
