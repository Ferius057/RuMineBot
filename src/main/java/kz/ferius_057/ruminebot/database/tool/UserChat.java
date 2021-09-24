package kz.ferius_057.ruminebot.database.tool;

/**
 * @author Charles_Grozny
 */
public class UserChat {
    private final int userId;
    private final int peerId;
    private final String nickname;
    private final int role;
    private final int reputation;
    private final boolean exist;
    private final boolean banrep;

    public UserChat(final int userId, final int peerId, String nickname, final int role, final int reputation, final boolean exist, final boolean banrep) {
        this.userId = userId;
        this.peerId = peerId;
        this.nickname = nickname;
        this.role = role;
        this.reputation = reputation;
        this.exist = exist;
        this.banrep = banrep;
    }

    public int getUserId() {
        return userId;
    }

    public int getPeerId() {
        return peerId;
    }

    public String getNickname() {
        return nickname;
    }

    public int getRole() {
        return role;
    }

    public int getReputation() {
        return reputation;
    }

    public boolean isExist() {
        return exist;
    }

    public boolean isBanrep() {
        return banrep;
    }
}