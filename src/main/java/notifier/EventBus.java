package notifier;

import listener.Listener;

/**
 * interface to notify an object that implements {@link Listener} and register them to this EventBus.
 * @param <T> type of listener that this notifier is authorized to notify
 */
public interface EventBus<T> {

    void register(Listener<T> listener);

    void notifyListener(T data);
}
