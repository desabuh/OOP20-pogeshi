package listener;

import com.google.common.eventbus.Subscribe;

/**
 * {@link Listener} interface that permits to listen to a 
 * {@link com.google.common.eventbus.EventBus} publisher object.
 * 
 * @param <T> event object
 */
public interface GuavaEventBusListener<T> extends Listener<T> {

    /**
     * function to receive an event response if registered to a specific {@link EventBus}.
     * @see com.google.common.eventbus.Subscribe 
     * 
     * 
     */
    @Subscribe
    void handleEvent(T event);
}
