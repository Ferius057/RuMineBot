package com.vk.api.sdk.objects.callback;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import java.util.Objects;

/**
 * MarketComment object
 */
public class MarketComment implements Validable {
    @SerializedName("id")
    @Required
    private Integer id;

    @SerializedName("from_id")
    private Integer fromId;

    @SerializedName("date")
    @Required
    private Integer date;

    @SerializedName("text")
    private String text;

    @SerializedName("market_owner_id")
    private Integer marketOwnerId;

    @SerializedName("photo_id")
    private Integer photoId;

    public Integer getId() {
        return id;
    }

    public MarketComment setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getFromId() {
        return fromId;
    }

    public MarketComment setFromId(Integer fromId) {
        this.fromId = fromId;
        return this;
    }

    public Integer getDate() {
        return date;
    }

    public MarketComment setDate(Integer date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public MarketComment setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getMarketOwnerId() {
        return marketOwnerId;
    }

    public MarketComment setMarketOwnerId(Integer marketOwnerId) {
        this.marketOwnerId = marketOwnerId;
        return this;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public MarketComment setPhotoId(Integer photoId) {
        this.photoId = photoId;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, marketOwnerId, photoId, id, text, fromId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketComment marketComment = (MarketComment) o;
        return Objects.equals(date, marketComment.date) &&
                Objects.equals(fromId, marketComment.fromId) &&
                Objects.equals(photoId, marketComment.photoId) &&
                Objects.equals(id, marketComment.id) &&
                Objects.equals(text, marketComment.text) &&
                Objects.equals(marketOwnerId, marketComment.marketOwnerId);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("MarketComment{");
        sb.append("date=").append(date);
        sb.append(", fromId=").append(fromId);
        sb.append(", photoId=").append(photoId);
        sb.append(", id=").append(id);
        sb.append(", text='").append(text).append("'");
        sb.append(", marketOwnerId=").append(marketOwnerId);
        sb.append('}');
        return sb.toString();
    }
}
