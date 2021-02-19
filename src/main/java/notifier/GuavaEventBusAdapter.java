package notifier;

import com.google.common.eventbus.EventBus;

import listener.Command;
import listener.Listener;

/**
 * implementation of {@link EventBus}
 * to notify his {@link Listener}s, adapter for Guava EventBus API.
 * (this adapter is used as a wrapper to allow {@link EventBus} subtypes and Guava Eventbus to work together) 
 * 
 * @param <T> event type to notify its listeners
 * 
 * @see com.google.common.eventbus.EventBus
 * 
 */
public final class GuavaEventBusAdapter<T> implements notifier.EventBus<T> {

    /**
     * {@link com.google.common.eventbus.EventBus} object.
     */
    private EventBus eventBus;

    /**
     * CommandBusNotifier constructor.
     * @param eventBus {@link com.google.common.eventbus.EventBus} injected instance
     */
    public GuavaEventBusAdapter(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * function used to register a listener for this notifier.
     * @param listener a {@link Listener} for {@link Command} object
     */
    @Override
    public void register(final Listener<T> listener) {
        this.eventBus.register(listener);
    }

    /**
     * global notification to all registered listeners.
     * @param event to send to its listeners
     */
    @Override
    public void notifyListener(final T event) {
        this.eventBus.post(event);
    }

}
