package notifier;

import com.google.common.eventbus.EventBus;

import listener.Command;
import listener.Listener;

/**
 * implementation of {@link Notifier} that internally use {@link com.google.common.eventbus.EventBus}
 * not notify his {@link Listener}s.
 */
public final class CommandBusNotifier implements Notifier<Command> {

    /**
     * {@link com.google.common.eventbus.EventBus} object.
     */
    private EventBus eventBus;

    /**
     * CommandBusNotifier constructor.
     * @param eventBus {@link com.google.common.eventbus.EventBus} injected instance
     */
    public CommandBusNotifier(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * function used to register a listener for this notifier.
     * @param listener a {@link Listener} for {@link Command} object
     */
    @Override
    public void register(final Listener<Command> listener) {
        this.eventBus.register(listener);
    }

    /**
     * global notification to all registered listeners.
     * @param command {@link Command} to send to its listeners
     */
    @Override
    public void notifyListener(final Command command) {
        this.eventBus.post(eventBus);
    }

}
