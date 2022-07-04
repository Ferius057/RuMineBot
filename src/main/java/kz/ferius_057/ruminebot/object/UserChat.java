package kz.ferius_057.ruminebot.object;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author Charles_Grozny
 */

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserChat implements Comparable<UserChat> {
    int userId;
    int peerId;
    String nickname;
    int role;
    int reputation;
    boolean exist;
    boolean banrep;

    @Override
    public int compareTo(UserChat userChat) {
        return Integer.compare(reputation, userChat.getReputation());
    }
}