package kz.ferius_057.ruminebot.command.tool;

/**
 * @author Charles_Grozny
 */
public class User {
    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String github;
    private final String nicknameMinecraft;
    private final long date;

    public User(int userId, String firstName, String lastName, String github, String nicknameMinecraft, long date) {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
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
}