package com.vk.api.sdk.objects.store;

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
 * Product object
 */
public class Product implements Validable {
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
    private ProductType type;

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

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProductType getType() {
        return type;
    }

    public Product setType(ProductType type) {
        this.type = type;
        return this;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public Product setIsNew(Boolean isNew) {
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

    public Product setPurchaseDate(Integer purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Product setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public Product setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
        return this;
    }

    public List<Integer> getStyleStickerIds() {
        return styleStickerIds;
    }

    public Product setStyleStickerIds(List<Integer> styleStickerIds) {
        this.styleStickerIds = styleStickerIds;
        return this;
    }

    public List<Image> getIcon() {
        return icon;
    }

    public Product setIcon(List<Image> icon) {
        this.icon = icon;
        return this;
    }

    public List<Image> getPreviews() {
        return previews;
    }

    public Product setPreviews(List<Image> previews) {
        this.previews = previews;
        return this;
    }

    public Boolean getHasAnimation() {
        return hasAnimation;
    }

    public Product setHasAnimation(Boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Product setSubtitle(String subtitle) {
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
        Product product = (Product) o;
        return Objects.equals(isNew, product.isNew) &&
                Objects.equals(icon, product.icon) &&
                Objects.equals(active, product.active) &&
                Objects.equals(type, product.type) &&
                Objects.equals(promoted, product.promoted) &&
                Objects.equals(title, product.title) &&
                Objects.equals(hasAnimation, product.hasAnimation) &&
                Objects.equals(purchased, product.purchased) &&
                Objects.equals(styleStickerIds, product.styleStickerIds) &&
                Objects.equals(subtitle, product.subtitle) &&
                Objects.equals(previews, product.previews) &&
                Objects.equals(stickers, product.stickers) &&
                Objects.equals(id, product.id) &&
                Objects.equals(purchaseDate, product.purchaseDate);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("Product{");
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
