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
    int userId, peerId, role, reputation;
    boolean exist, banrep;

    @Override
    public int compareTo(UserChat userChat) {
        return Integer.compare(reputation, userChat.getReputation());
    }
}