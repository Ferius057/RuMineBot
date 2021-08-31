package kz.ferius_057.ruminebot.command.tool;

/**
 * @author Charles_Grozny
 */
public class UserInPeerId {
    private final long peerIdUserId;
    private final String nickname;
    private final String role;
    private final int reputation;
    private final boolean exist;
    private final boolean banrep;

    public UserInPeerId(long peerIdUserId, String nickname, String role, int reputation, boolean exist, boolean banrep) {
        this.peerIdUserId = peerIdUserId;
        this.nickname = nickname;
        this.role = role;
        this.reputation = reputation;
        this.exist = exist;
        this.banrep = banrep;
    }

    public long getPeerIdUserId() {
        return peerIdUserId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRole() {
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