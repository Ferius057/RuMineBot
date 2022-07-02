package kz.ferius_057.ruminebot.event.api;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 27.06.2022 | 15:55 ⭐
 *
 * TODO: 27.06.2022 | Временное решение, пока разраб либы не добавит это в либу
 */
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum MessageActionStatus  {
    CHAT_PHOTO_UPDATE("chat_photo_update"),

    CHAT_PHOTO_REMOVE("chat_photo_remove"),

    CHAT_CREATE("chat_create"),

    CHAT_TITLE_UPDATE("chat_title_update"),

    CHAT_INVITE_USER("chat_invite_user"),

    CHAT_KICK_USER("chat_kick_user"),

    CHAT_PIN_MESSAGE("chat_pin_message"),

    CHAT_UNPIN_MESSAGE("chat_unpin_message"),

    CHAT_INVITE_USER_BY_LINK("chat_invite_user_by_link"),

    CHAT_INVITE_USER_BY_MESSAGE_REQUEST("chat_invite_user_by_message_request");

    String value;
}