package com.vk.api.sdk.objects.callback;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import java.util.Objects;

/**
 * VideoComment object
 */
public class VideoComment implements Validable {
    @SerializedName("id")
    @Required
    private Integer id;

    @SerializedName("from_id")
    private Integer fromId;

    @SerializedName("date")
    @Required
    private Integer date;

    @SerializedName("text")
    @Required
    private String text;

    @SerializedName("video_owner_id")
    private Integer videoOwnerId;

    public Integer getId() {
        return id;
    }

    public VideoComment setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getFromId() {
        return fromId;
    }

    public VideoComment setFromId(Integer fromId) {
        this.fromId = fromId;
        return this;
    }

    public Integer getDate() {
        return date;
    }

    public VideoComment setDate(Integer date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public VideoComment setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getVideoOwnerId() {
        return videoOwnerId;
    }

    public VideoComment setVideoOwnerId(Integer videoOwnerId) {
        this.videoOwnerId = videoOwnerId;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, id, text, fromId, videoOwnerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoComment videoComment = (VideoComment) o;
        return Objects.equals(date, videoComment.date) &&
                Objects.equals(fromId, videoComment.fromId) &&
                Objects.equals(id, videoComment.id) &&
                Objects.equals(text, videoComment.text) &&
                Objects.equals(videoOwnerId, videoComment.videoOwnerId);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("VideoComment{");
        sb.append("date=").append(date);
        sb.append(", fromId=").append(fromId);
        sb.append(", id=").append(id);
        sb.append(", text='").append(text).append("'");
        sb.append(", videoOwnerId=").append(videoOwnerId);
        sb.append('}');
        return sb.toString();
    }
}
