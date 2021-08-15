package com.vk.api.sdk.objects.store;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import java.util.List;
import java.util.Objects;

/**
 * StickersKeyword object
 */
public class StickersKeyword implements Validable {
    @SerializedName("words")
    @Required
    private List<String> words;

    @SerializedName("user_stickers")
    private StickersKeywordStickers userStickers;

    @SerializedName("promoted_stickers")
    private StickersKeywordStickers promotedStickers;

    @SerializedName("stickers")
    private List<StickersKeywordSticker> stickers;

    public List<String> getWords() {
        return words;
    }

    public StickersKeyword setWords(List<String> words) {
        this.words = words;
        return this;
    }

    public StickersKeywordStickers getUserStickers() {
        return userStickers;
    }

    public StickersKeyword setUserStickers(StickersKeywordStickers userStickers) {
        this.userStickers = userStickers;
        return this;
    }

    public StickersKeywordStickers getPromotedStickers() {
        return promotedStickers;
    }

    public StickersKeyword setPromotedStickers(StickersKeywordStickers promotedStickers) {
        this.promotedStickers = promotedStickers;
        return this;
    }

    public List<StickersKeywordSticker> getStickers() {
        return stickers;
    }

    public StickersKeyword setStickers(List<StickersKeywordSticker> stickers) {
        this.stickers = stickers;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotedStickers, userStickers, words, stickers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StickersKeyword stickersKeyword = (StickersKeyword) o;
        return Objects.equals(promotedStickers, stickersKeyword.promotedStickers) &&
                Objects.equals(userStickers, stickersKeyword.userStickers) &&
                Objects.equals(words, stickersKeyword.words) &&
                Objects.equals(stickers, stickersKeyword.stickers);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("StickersKeyword{");
        sb.append("promotedStickers=").append(promotedStickers);
        sb.append(", userStickers=").append(userStickers);
        sb.append(", words='").append(words).append("'");
        sb.append(", stickers=").append(stickers);
        sb.append('}');
        return sb.toString();
    }
}
