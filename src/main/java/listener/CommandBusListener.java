package listener;

import com.google.common.eventbus.Subscribe;

/**
 * {@link CommandListener} implementation that permits to listen to a 
 * {@link com.google.common.eventbus.EventBus} publisher object.
 * 
 */
public abstract class CommandBusListener implements CommandListener {

    @Subscribe
    @Override
    public final void handleEvent(final Command event) {
        event.execute();
    }

    @Override
    public abstract Class<?> getCommandType();

}
