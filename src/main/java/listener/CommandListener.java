package listener;

/**
 * extends {@link Listener} interface narrowing the listener object only to {@link Command}.
 */
public interface CommandListener extends Listener<Command> {

    Class<?> getCommandType();
}
