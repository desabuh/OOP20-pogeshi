package listener;


/**
 * generic interface to allow an object to wait for an event.
 * @param <T> object for which you put yourself on hold
 */
public interface Listener<T> {

    void handleEvent(T event);
}
