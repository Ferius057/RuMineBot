package com.vk.api.sdk.objects.store;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import java.util.Objects;

/**
 * StickersKeywordSticker object
 */
public class StickersKeywordSticker implements Validable {
    /**
     * Pack id
     */
    @SerializedName("pack_id")
    private Integer packId;

    /**
     * Sticker id
     */
    @SerializedName("sticker_id")
    private Integer stickerId;

    public Integer getPackId() {
        return packId;
    }

    public StickersKeywordSticker setPackId(Integer packId) {
        this.packId = packId;
        return this;
    }

    public Integer getStickerId() {
        return stickerId;
    }

    public StickersKeywordSticker setStickerId(Integer stickerId) {
        this.stickerId = stickerId;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(packId, stickerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StickersKeywordSticker stickersKeywordSticker = (StickersKeywordSticker) o;
        return Objects.equals(stickerId, stickersKeywordSticker.stickerId) &&
                Objects.equals(packId, stickersKeywordSticker.packId);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("StickersKeywordSticker{");
        sb.append("stickerId=").append(stickerId);
        sb.append(", packId=").append(packId);
        sb.append('}');
        return sb.toString();
    }
}
