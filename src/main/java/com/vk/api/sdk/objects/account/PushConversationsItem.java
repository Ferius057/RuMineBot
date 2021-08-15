package com.vk.api.sdk.objects.account;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.Validable;
import com.vk.api.sdk.objects.annotations.Required;
import com.vk.api.sdk.objects.base.BoolInt;
import java.util.Objects;

/**
 * PushConversationsItem object
 */
public class PushConversationsItem implements Validable {
    /**
     * Time until that notifications are disabled in seconds
     */
    @SerializedName("disabled_until")
    private Integer disabledUntil;

    /**
     * Peer ID
     */
    @SerializedName("peer_id")
    private Integer peerId;

    /**
     * Information whether the sound are enabled
     */
    @SerializedName("sound")
    @Required
    private BoolInt sound;

    /**
     * Information whether the mentions are disabled
     */
    @SerializedName("disabled_mentions")
    private BoolInt disabledMentions;

    /**
     * Information whether the mass mentions (like '@all', '@online') are disabled. Can be affected by 'disabled_mentions'
     */
    @SerializedName("disabled_mass_mentions")
    private BoolInt disabledMassMentions;

    public Integer getDisabledUntil() {
        return disabledUntil;
    }

    public PushConversationsItem setDisabledUntil(Integer disabledUntil) {
        this.disabledUntil = disabledUntil;
        return this;
    }

    public Integer getPeerId() {
        return peerId;
    }

    public PushConversationsItem setPeerId(Integer peerId) {
        this.peerId = peerId;
        return this;
    }

    public boolean isSound() {
        return sound == BoolInt.YES;
    }

    public BoolInt getSound() {
        return sound;
    }

    public boolean isDisabledMentions() {
        return disabledMentions == BoolInt.YES;
    }

    public BoolInt getDisabledMentions() {
        return disabledMentions;
    }

    public boolean isDisabledMassMentions() {
        return disabledMassMentions == BoolInt.YES;
    }

    public BoolInt getDisabledMassMentions() {
        return disabledMassMentions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(peerId, sound, disabledMassMentions, disabledUntil, disabledMentions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PushConversationsItem pushConversationsItem = (PushConversationsItem) o;
        return Objects.equals(disabledUntil, pushConversationsItem.disabledUntil) &&
                Objects.equals(disabledMassMentions, pushConversationsItem.disabledMassMentions) &&
                Objects.equals(sound, pushConversationsItem.sound) &&
                Objects.equals(disabledMentions, pushConversationsItem.disabledMentions) &&
                Objects.equals(peerId, pushConversationsItem.peerId);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("PushConversationsItem{");
        sb.append("disabledUntil=").append(disabledUntil);
        sb.append(", disabledMassMentions=").append(disabledMassMentions);
        sb.append(", sound=").append(sound);
        sb.append(", disabledMentions=").append(disabledMentions);
        sb.append(", peerId=").append(peerId);
        sb.append('}');
        return sb.toString();
    }
}
