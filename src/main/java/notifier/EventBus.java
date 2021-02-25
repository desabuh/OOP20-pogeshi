package notifier;

import listener.Listener;

/**
 * interface to notify all the object  registered that implements {@link Listener} and register them to this EventBus.
 * @param <T> type of listener that this notifier is authorized to notify
 */
public interface EventBus<T> {

    /**
     * register a listener.
     * @param listener
     */
    void register(Listener<T> listener);

    /**
     * notify the listener.
     * @param data
     */
    void notifyListener(T data);
}
