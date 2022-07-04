package kz.ferius_057.ruminebot.command.api;

import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.object.UserChat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 04.07.2022 | 17:22 ⭐
 */

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheDataMessage {
    User sender;
    UserChat senderUserChat;

    List<User> replySenders = new ArrayList<>();
    List<UserChat> replySendersUserChat = new ArrayList<>();
}