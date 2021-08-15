package com.vk.api.sdk.objects.podcast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import java.util.Objects;

/**
 * ExternalData object
 */
public class ExternalData implements Validable {
    /**
     * Url of the podcast page
     */
    @SerializedName("url")
    private String url;

    /**
     * Url of the podcasts owner community
     */
    @SerializedName("owner_url")
    private String ownerUrl;

    /**
     * Podcast title
     */
    @SerializedName("title")
    private String title;

    /**
     * Name of the podcasts owner community
     */
    @SerializedName("owner_name")
    private String ownerName;

    /**
     * Podcast cover
     */
    @SerializedName("cover")
    private Cover cover;

    public String getUrl() {
        return url;
    }

    public ExternalData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getOwnerUrl() {
        return ownerUrl;
    }

    public ExternalData setOwnerUrl(String ownerUrl) {
        this.ownerUrl = ownerUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ExternalData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public ExternalData setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public Cover getCover() {
        return cover;
    }

    public ExternalData setCover(Cover cover) {
        this.cover = cover;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cover, ownerName, title, ownerUrl, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalData externalData = (ExternalData) o;
        return Objects.equals(cover, externalData.cover) &&
                Objects.equals(ownerUrl, externalData.ownerUrl) &&
                Objects.equals(ownerName, externalData.ownerName) &&
                Objects.equals(title, externalData.title) &&
                Objects.equals(url, externalData.url);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("ExternalData{");
        sb.append("cover=").append(cover);
        sb.append(", ownerUrl='").append(ownerUrl).append("'");
        sb.append(", ownerName='").append(ownerName).append("'");
        sb.append(", title='").append(title).append("'");
        sb.append(", url='").append(url).append("'");
        sb.append('}');
        return sb.toString();
    }
}
