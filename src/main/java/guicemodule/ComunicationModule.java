package guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import notifier.EventBus;
import notifier.GuavaEventBusAdapter;
import views.scene.layout.LAYOUT;

public final class ComunicationModule extends AbstractModule {


    @Provides
    @Singleton
    MainController provideMainController() {
        return new MainController();
    }

    @Provides
    EventBus<Request<LAYOUT, ? extends Object>> provideEventBus() {
        return new GuavaEventBusAdapter<Request<LAYOUT, ? extends Object>>(new com.google.common.eventbus.EventBus());
    }
}
