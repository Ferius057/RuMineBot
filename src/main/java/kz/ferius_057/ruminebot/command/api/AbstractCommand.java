package kz.ferius_057.ruminebot.command.api;

public abstract class AbstractCommand implements Command {

    protected final String name;
    protected final String[] aliases;

    protected AbstractCommand(final String name, final String... aliases) {
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