package com.vk.api.sdk.objects.store.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.base.Image;
import com.vk.api.sdk.objects.base.Sticker;
import java.util.List;
import java.util.Objects;

/**
 * GetProductsResponse object
 */
public class GetProductsResponse implements Validable {
    /**
     * Id of the product
     */
    @SerializedName("id")
    @Required
    private Integer id;

    /**
     * Product type
     */
    @SerializedName("type")
    @Required
    private GetProductsResponseType type;

    /**
     * Information whether sticker product wasn't used after being purchased
     */
    @SerializedName("is_new")
    private Boolean isNew;

    /**
     * Information whether the product is purchased (1 - yes, 0 - no)
     */
    @SerializedName("purchased")
    private BoolInt purchased;

    /**
     * Information whether the product is active (1 - yes, 0 - no)
     */
    @SerializedName("active")
    private BoolInt active;

    /**
     * Information whether the product is promoted (1 - yes, 0 - no)
     */
    @SerializedName("promoted")
    private BoolInt promoted;

    /**
     * Date (Unix time) when the product was purchased
     */
    @SerializedName("purchase_date")
    private Integer purchaseDate;

    /**
     * Title of the product
     */
    @SerializedName("title")
    private String title;

    @SerializedName("stickers")
    private List<Sticker> stickers;

    /**
     * Array of style sticker ids (for sticker pack styles)
     */
    @SerializedName("style_sticker_ids")
    private List<Integer> styleStickerIds;

    /**
     * Array of icon images or icon set object of the product (for stickers product type)
     */
    @SerializedName("icon")
    private List<Image> icon;

    /**
     * Array of preview images of the product (for stickers product type)
     */
    @SerializedName("previews")
    private List<Image> previews;

    /**
     * Information whether the product is an animated sticker pack (for stickers product type)
     */
    @SerializedName("has_animation")
    private Boolean hasAnimation;

    /**
     * Subtitle of the product
     */
    @SerializedName("subtitle")
    private String subtitle;

    public Integer getId() {
        return id;
    }

    public GetProductsResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public GetProductsResponseType getType() {
        return type;
    }

    public GetProductsResponse setType(GetProductsResponseType type) {
        this.type = type;
        return this;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public GetProductsResponse setIsNew(Boolean isNew) {
        this.isNew = isNew;
        return this;
    }

    public boolean isPurchased() {
        return purchased == BoolInt.YES;
    }

    public BoolInt getPurchased() {
        return purchased;
    }

    public boolean isActive() {
        return active == BoolInt.YES;
    }

    public BoolInt getActive() {
        return active;
    }

    public boolean isPromoted() {
        return promoted == BoolInt.YES;
    }

    public BoolInt getPromoted() {
        return promoted;
    }

    public Integer getPurchaseDate() {
        return purchaseDate;
    }

    public GetProductsResponse setPurchaseDate(Integer purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public GetProductsResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public GetProductsResponse setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
        return this;
    }

    public List<Integer> getStyleStickerIds() {
        return styleStickerIds;
    }

    public GetProductsResponse setStyleStickerIds(List<Integer> styleStickerIds) {
        this.styleStickerIds = styleStickerIds;
        return this;
    }

    public List<Image> getIcon() {
        return icon;
    }

    public GetProductsResponse setIcon(List<Image> icon) {
        this.icon = icon;
        return this;
    }

    public List<Image> getPreviews() {
        return previews;
    }

    public GetProductsResponse setPreviews(List<Image> previews) {
        this.previews = previews;
        return this;
    }

    public Boolean getHasAnimation() {
        return hasAnimation;
    }

    public GetProductsResponse setHasAnimation(Boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public GetProductsResponse setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseDate, hasAnimation, icon, active, isNew, type, promoted, title, purchased, styleStickerIds, subtitle, previews, stickers, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetProductsResponse getProductsResponse = (GetProductsResponse) o;
        return Objects.equals(isNew, getProductsResponse.isNew) &&
                Objects.equals(icon, getProductsResponse.icon) &&
                Objects.equals(active, getProductsResponse.active) &&
                Objects.equals(type, getProductsResponse.type) &&
                Objects.equals(promoted, getProductsResponse.promoted) &&
                Objects.equals(title, getProductsResponse.title) &&
                Objects.equals(hasAnimation, getProductsResponse.hasAnimation) &&
                Objects.equals(purchased, getProductsResponse.purchased) &&
                Objects.equals(styleStickerIds, getProductsResponse.styleStickerIds) &&
                Objects.equals(subtitle, getProductsResponse.subtitle) &&
                Objects.equals(previews, getProductsResponse.previews) &&
                Objects.equals(stickers, getProductsResponse.stickers) &&
                Objects.equals(id, getProductsResponse.id) &&
                Objects.equals(purchaseDate, getProductsResponse.purchaseDate);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("GetProductsResponse{");
        sb.append("isNew=").append(isNew);
        sb.append(", icon=").append(icon);
        sb.append(", active=").append(active);
        sb.append(", type='").append(type).append("'");
        sb.append(", promoted=").append(promoted);
        sb.append(", title='").append(title).append("'");
        sb.append(", hasAnimation=").append(hasAnimation);
        sb.append(", purchased=").append(purchased);
        sb.append(", styleStickerIds=").append(styleStickerIds);
        sb.append(", subtitle='").append(subtitle).append("'");
        sb.append(", previews=").append(previews);
        sb.append(", stickers=").append(stickers);
        sb.append(", id=").append(id);
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append('}');
        return sb.toString();
    }
}
