package com.vk.api.sdk.objects.client;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames;
import java.util.List;
import java.util.Objects;

/**
 * InfoForBots object
 */
public class InfoForBots implements Validable {
    @SerializedName("button_actions")
    private List<TemplateActionTypeNames> buttonActions;

    /**
     * client has support keyboard
     */
    @SerializedName("keyboard")
    private Boolean keyboard;

    /**
     * client has support inline keyboard
     */
    @SerializedName("inline_keyboard")
    private Boolean inlineKeyboard;

    /**
     * client has support carousel
     */
    @SerializedName("carousel")
    private Boolean carousel;

    /**
     * client or user language id
     */
    @SerializedName("lang_id")
    private Integer langId;

    public List<TemplateActionTypeNames> getButtonActions() {
        return buttonActions;
    }

    public InfoForBots setButtonActions(List<TemplateActionTypeNames> buttonActions) {
        this.buttonActions = buttonActions;
        return this;
    }

    public Boolean getKeyboard() {
        return keyboard;
    }

    public InfoForBots setKeyboard(Boolean keyboard) {
        this.keyboard = keyboard;
        return this;
    }

    public Boolean getInlineKeyboard() {
        return inlineKeyboard;
    }

    public InfoForBots setInlineKeyboard(Boolean inlineKeyboard) {
        this.inlineKeyboard = inlineKeyboard;
        return this;
    }

    public Boolean getCarousel() {
        return carousel;
    }

    public InfoForBots setCarousel(Boolean carousel) {
        this.carousel = carousel;
        return this;
    }

    public Integer getLangId() {
        return langId;
    }

    public InfoForBots setLangId(Integer langId) {
        this.langId = langId;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyboard, inlineKeyboard, buttonActions, langId, carousel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoForBots infoForBots = (InfoForBots) o;
        return Objects.equals(keyboard, infoForBots.keyboard) &&
                Objects.equals(inlineKeyboard, infoForBots.inlineKeyboard) &&
                Objects.equals(buttonActions, infoForBots.buttonActions) &&
                Objects.equals(langId, infoForBots.langId) &&
                Objects.equals(carousel, infoForBots.carousel);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("InfoForBots{");
        sb.append("keyboard=").append(keyboard);
        sb.append(", inlineKeyboard=").append(inlineKeyboard);
        sb.append(", buttonActions=").append(buttonActions);
        sb.append(", langId=").append(langId);
        sb.append(", carousel=").append(carousel);
        sb.append('}');
        return sb.toString();
    }
}
