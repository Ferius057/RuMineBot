package com.vk.api.sdk.objects.photos.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import java.util.Objects;

/**
 * OwnerCoverUploadResponse object
 */
public class OwnerCoverUploadResponse implements Validable {
    /**
     * Uploading hash
     */
    @SerializedName("hash")
    private String hash;

    /**
     * Uploaded photo data
     */
    @SerializedName("photo")
    private String photo;

    public String getHash() {
        return hash;
    }

    public OwnerCoverUploadResponse setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public OwnerCoverUploadResponse setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(photo, hash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerCoverUploadResponse ownerCoverUploadResponse = (OwnerCoverUploadResponse) o;
        return Objects.equals(photo, ownerCoverUploadResponse.photo) &&
                Objects.equals(hash, ownerCoverUploadResponse.hash);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("OwnerCoverUploadResponse{");
        sb.append("photo='").append(photo).append("'");
        sb.append(", hash='").append(hash).append("'");
        sb.append('}');
        return sb.toString();
    }
}
