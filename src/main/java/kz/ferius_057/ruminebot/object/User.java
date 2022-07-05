package kz.ferius_057.ruminebot.object;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.additional.NameCase;
import kz.ferius_057.ruminebot.Manager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Charles_Grozny
 */

@Getter
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class User {
    int userId;
    Object[] firstName;
    Object[] lastName;
    List<FullName> fullName = new ArrayList<>();
    String github;
    String nicknameMinecraft;
    long date;

    public User(int userId, Object[] firstName, Object[] lastName, String github, String nicknameMinecraft, long date) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.github = github;
        this.nicknameMinecraft = nicknameMinecraft;
        this.date = date;

        for (int i = 0; i < this.firstName.length; i++) {
            FullName fullName = new FullName();
            fullName.setNoPush(this.firstName[i] + " " + this.lastName[i]);
            fullName.setPush("[id" + userId + "|" + this.firstName[i] + " " + this.lastName[i] + "]");
            this.fullName.add(fullName);
        }
    }

    public static User get(final Manager manager, final int userId) throws VkApiException {
        User user = manager.localData().users.get(userId);

        if (user == null) {
            User userNew = manager.chatRepository().getUser(userId);

            if (userNew == null) {
                User.registrationUser(manager, String.valueOf(userId));
                get(manager, userId);
            }

            manager.localData().users.put(userId, userNew);
            return userNew;
        } else return user;
    }

    public static void registrationUser(final Manager manager, final String... userId) throws VkApiException {

        // TODO: 27.06.2022 | сделать по нормальному

        HashMap<Integer, String[]> fistName = new HashMap<>();
        HashMap<Integer, String[]> lastName = new HashMap<>();

        for (int i = 0; i < 6; i++) {
            List<api.longpoll.bots.model.objects.basic.User> responseObject = manager.vk().users.get()
                    .setUserIds(userId)
                    .setNameCase(NameCase.values()[i])
                    .execute().getResponseObject();

            for (api.longpoll.bots.model.objects.basic.User response : responseObject) {
                String[] f = fistName.get(response.getId());
                if (f == null) f = new String[6];
                String[] l = lastName.get(response.getId());
                if (l == null) l = new String[6];
                f[i] = response.getFirstName();
                l[i] = response.getLastName();
                fistName.put(response.getId(), f);
                lastName.put(response.getId(), l);
            }
        }

        fistName.forEach((k, v) -> {
            manager.getUsers().add(k);
            manager.chatRepository().registerUserInBot(k, v, lastName.get(k));
        });

        System.out.println("Сохранил " + fistName.size() + " пользователей в базе бота.");
    }
}