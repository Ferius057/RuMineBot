package kz.ferius_057.ruminebot.command;

public abstract class Command implements ICommand {
    private final String name;
    private final String[] aliases;

    public Command(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    @Override
    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }
}