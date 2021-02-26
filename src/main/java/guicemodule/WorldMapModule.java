package guicemodule;

import com.google.inject.AbstractModule;

import com.google.inject.Provides;
import com.google.inject.Singleton;

import controllers.Controller;
import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import controllers.worldmap.WorldMapController;
import javafx.stage.Stage;
import models.GameMap.WorldMap;
import models.GameMap.WorldMapImpl;
import notifier.EventBus;
import views.View;
import views.WorldMapView;
import views.scene.layout.LAYOUT;


public final class WorldMapModule extends AbstractModule {


    private Stage stage;

    public WorldMapModule(final Stage stage) {
        this.stage = stage;
    }

    @Provides
    @Singleton
    WorldMapController provideMapController(final WorldMap model, final View view, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        return new WorldMapController(model, view, mainController, notifier);
    }

    @Provides
    @Singleton
    WorldMap provideWorldMapModel() {
        return new WorldMapImpl();
    }

    @Provides
    @Singleton
    View provideWorldMapView() {
        return new WorldMapView(this.stage);
    }


}
