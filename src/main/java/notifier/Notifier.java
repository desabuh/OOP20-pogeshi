package notifier;

import listener.Listener;

/**
 * interface to notify an object that implements {@link Listener} and is registered to the Notifier.
 * @param <T> type of listener that this notifier is authorized to notify
 */
public interface Notifier<T> {

    void register(Listener<T> listener);

    void notifyListener();
}
