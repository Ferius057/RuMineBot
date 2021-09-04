package kz.ferius_057.ruminebot.command.api.tool;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.users.GetNameCase;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import kz.ferius_057.ruminebot.VkApi;

import java.util.HashMap;
import java.util.List;

/**
 * @author Charles_Grozny
 */
public class User {
    private final int userId;
    private final Object[] firstName;
    private final Object[] lastName;
    private final String github;
    private final String nicknameMinecraft;
    private final long date;

    public User(int userId, Object[] firstName, Object[] lastName, String github, String nicknameMinecraft, long date) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.github = github;
        this.nicknameMinecraft = nicknameMinecraft;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public Object[] getFirstName() {
        return firstName;
    }

    public Object[] getLastName() {
        return lastName;
    }

    public String getGithub() {
        return github;
    }

    public String getNicknameMinecraft() {
        return nicknameMinecraft;
    }

    public long getDate() {
        return date;
    }



    public static User user(final VkApi vkApi, final String userId) throws ClientException, ApiException {
        int j = 0;
        do {
            User user = vkApi.getChatDao().getUser(Integer.parseInt(userId));

            if (user == null) {
                User.registrationUserInTheBot(vkApi, userId);
            } else {
                return user;
            }
            ++j;
        } while (j < 3);
        return null;
    }

    public static void registrationUserInTheBot(final VkApi vkApi, final String... userId) throws ClientException, ApiException {
        HashMap<Integer, String[]> fistName = new HashMap<>();
        HashMap<Integer, String[]> lastName = new HashMap<>();

        for (int i = 0; i < 6; i++) {
            System.out.println(i);
            List<GetResponse> execute = vkApi.getClient().users().get(vkApi.getActor()).userIds(userId).nameCase(GetNameCase.values()[i]).execute();
            for (GetResponse response : execute) {
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
            vkApi.getUsers().add(k);
            vkApi.getChatDao().registrationUserInTheBot(k, v, lastName.get(k));
        });
    }
}