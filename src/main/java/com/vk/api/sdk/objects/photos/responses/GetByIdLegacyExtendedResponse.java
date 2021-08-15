package com.vk.api.sdk.objects.photos.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.base.Likes;
import com.vk.api.sdk.objects.base.ObjectCount;
import com.vk.api.sdk.objects.base.RepostsInfo;
import com.vk.api.sdk.objects.photos.Image;
import java.util.List;
import java.util.Objects;

/**
 * GetByIdLegacyExtendedResponse object
 */
public class GetByIdLegacyExtendedResponse implements Validable {
    /**
     * Access key for the photo
     */
    @SerializedName("access_key")
    private String accessKey;

    /**
     * Album ID
     */
    @SerializedName("album_id")
    private Integer albumId;

    /**
     * Information whether current user can comment the photo
     */
    @SerializedName("can_comment")
    private BoolInt canComment;

    /**
     * Date when uploaded
     */
    @SerializedName("date")
    @Required
    private Integer date;

    /**
     * Original photo height
     */
    @SerializedName("height")
    private Integer height;

    /**
     * Photo ID
     */
    @SerializedName("id")
    @Required
    private Integer id;

    @SerializedName("images")
    private List<Image> images;

    /**
     * Latitude
     */
    @SerializedName("lat")
    private Float lat;

    @SerializedName("likes")
    private Likes likes;

    @SerializedName("reposts")
    private RepostsInfo reposts;

    @SerializedName("comments")
    private ObjectCount comments;

    /**
     * Longitude
     */
    @SerializedName("long")
    private Float lng;

    /**
     * Photo owner's ID
     */
    @SerializedName("owner_id")
    private Integer ownerId;

    /**
     * Post ID
     */
    @SerializedName("post_id")
    private Integer postId;

    @SerializedName("tags")
    private ObjectCount tags;

    /**
     * Photo caption
     */
    @SerializedName("text")
    private String text;

    /**
     * ID of the user who have uploaded the photo
     */
    @SerializedName("user_id")
    private Integer userId;

    /**
     * Original photo width
     */
    @SerializedName("width")
    private Integer width;

    public String getAccessKey() {
        return accessKey;
    }

    public GetByIdLegacyExtendedResponse setAccessKey(String accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public GetByIdLegacyExtendedResponse setAlbumId(Integer albumId) {
        this.albumId = albumId;
        return this;
    }

    public boolean canComment() {
        return canComment == BoolInt.YES;
    }

    public BoolInt getCanComment() {
        return canComment;
    }

    public Integer getDate() {
        return date;
    }

    public GetByIdLegacyExtendedResponse setDate(Integer date) {
        this.date = date;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public GetByIdLegacyExtendedResponse setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public GetByIdLegacyExtendedResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public GetByIdLegacyExtendedResponse setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public Float getLat() {
        return lat;
    }

    public GetByIdLegacyExtendedResponse setLat(Float lat) {
        this.lat = lat;
        return this;
    }

    public Likes getLikes() {
        return likes;
    }

    public GetByIdLegacyExtendedResponse setLikes(Likes likes) {
        this.likes = likes;
        return this;
    }

    public RepostsInfo getReposts() {
        return reposts;
    }

    public GetByIdLegacyExtendedResponse setReposts(RepostsInfo reposts) {
        this.reposts = reposts;
        return this;
    }

    public ObjectCount getComments() {
        return comments;
    }

    public GetByIdLegacyExtendedResponse setComments(ObjectCount comments) {
        this.comments = comments;
        return this;
    }

    public Float getLng() {
        return lng;
    }

    public GetByIdLegacyExtendedResponse setLng(Float lng) {
        this.lng = lng;
        return this;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public GetByIdLegacyExtendedResponse setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Integer getPostId() {
        return postId;
    }

    public GetByIdLegacyExtendedResponse setPostId(Integer postId) {
        this.postId = postId;
        return this;
    }

    public ObjectCount getTags() {
        return tags;
    }

    public GetByIdLegacyExtendedResponse setTags(ObjectCount tags) {
        this.tags = tags;
        return this;
    }

    public String getText() {
        return text;
    }

    public GetByIdLegacyExtendedResponse setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public GetByIdLegacyExtendedResponse setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public GetByIdLegacyExtendedResponse setWidth(Integer width) {
        this.width = width;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, images, comments, lng, albumId, postId, ownerId, userId, tags, accessKey, width, canComment, id, text, lat, reposts, height, likes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetByIdLegacyExtendedResponse getByIdLegacyExtendedResponse = (GetByIdLegacyExtendedResponse) o;
        return Objects.equals(date, getByIdLegacyExtendedResponse.date) &&
                Objects.equals(images, getByIdLegacyExtendedResponse.images) &&
                Objects.equals(comments, getByIdLegacyExtendedResponse.comments) &&
                Objects.equals(ownerId, getByIdLegacyExtendedResponse.ownerId) &&
                Objects.equals(lng, getByIdLegacyExtendedResponse.lng) &&
                Objects.equals(tags, getByIdLegacyExtendedResponse.tags) &&
                Objects.equals(canComment, getByIdLegacyExtendedResponse.canComment) &&
                Objects.equals(postId, getByIdLegacyExtendedResponse.postId) &&
                Objects.equals(userId, getByIdLegacyExtendedResponse.userId) &&
                Objects.equals(accessKey, getByIdLegacyExtendedResponse.accessKey) &&
                Objects.equals(width, getByIdLegacyExtendedResponse.width) &&
                Objects.equals(albumId, getByIdLegacyExtendedResponse.albumId) &&
                Objects.equals(id, getByIdLegacyExtendedResponse.id) &&
                Objects.equals(text, getByIdLegacyExtendedResponse.text) &&
                Objects.equals(lat, getByIdLegacyExtendedResponse.lat) &&
                Objects.equals(reposts, getByIdLegacyExtendedResponse.reposts) &&
                Objects.equals(height, getByIdLegacyExtendedResponse.height) &&
                Objects.equals(likes, getByIdLegacyExtendedResponse.likes);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("GetByIdLegacyExtendedResponse{");
        sb.append("date=").append(date);
        sb.append(", images=").append(images);
        sb.append(", comments=").append(comments);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", lng=").append(lng);
        sb.append(", tags=").append(tags);
        sb.append(", canComment=").append(canComment);
        sb.append(", postId=").append(postId);
        sb.append(", userId=").append(userId);
        sb.append(", accessKey='").append(accessKey).append("'");
        sb.append(", width=").append(width);
        sb.append(", albumId=").append(albumId);
        sb.append(", id=").append(id);
        sb.append(", text='").append(text).append("'");
        sb.append(", lat=").append(lat);
        sb.append(", reposts=").append(reposts);
        sb.append(", height=").append(height);
        sb.append(", likes=").append(likes);
        sb.append('}');
        return sb.toString();
    }
}
