package kz.ferius_057.ruminebot.command.reputation;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.ferius_057.ruminebot.command.api.AbstractCommand;
import kz.ferius_057.ruminebot.command.api.CacheDataMessage;
import kz.ferius_057.ruminebot.command.api.annotation.CommandAnnotation;
import kz.ferius_057.ruminebot.command.api.annotation.Permission;
import kz.ferius_057.ruminebot.object.User;
import kz.ferius_057.ruminebot.object.UserChat;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 12.07.2022 | 19:20 ⭐
 */
@Permission(value = 1)
@CommandAnnotation(aliases = { "banreplist", "repbanlist", "банреплист", "репбанлист" })
public class BanRepList extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        val list = chatRepository.getUsersFromChat(message.getPeerId())
                .stream()
                .filter(UserChat::isBanrep)
                .collect(Collectors.toList());

        val msg = new StringBuilder();
        if (list.size() != 0) {
            msg.append("⭕ Пользователи имеющие бан репутации:\n");
            for (int i = 0; i < list.size(); i++) {
                msg.append(User.get(manager, list.get(i).getUserId()).getFullName().get(0).getPush())
                        .append(i != (list.size() - 1) ? ", " : "");
            }
        } else msg.append("⭕ В беседе никто не имеет бан репутации.");

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setDisableMentions(true)
                .setMessage(msg.toString())
                .execute();
    }
}