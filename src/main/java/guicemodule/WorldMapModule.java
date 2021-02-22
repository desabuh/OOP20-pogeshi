package guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import controllers.Controller;
import controllers.WorldMapController;
import guicemodule.annotation.WorldModel;
import guicemodule.annotation.WorldView;
import javafx.stage.Stage;
import models.GameMap.WorldMap;
import models.GameMap.WorldMapImpl;
import views.View;
import views.WorldMapView;


public final class WorldMapModule extends AbstractModule {


    private Stage stage;

    public WorldMapModule(final Stage stage) {
        this.stage = stage;
    }

    @Provides
    @Singleton
   // @guicemodule.annotation.WorldMapController
    WorldMapController provideMapController(final WorldMap model, final View view) {
        return new WorldMapController(model, view);
        //return new WorldMapController();
    }

    @Provides
    @Singleton
    //@WorldModel
    WorldMap provideWorldMapModel() {
        return new WorldMapImpl();
    }

    @Provides
    @Singleton
    //@WorldView
    View provideWorldMapView() {
        return new WorldMapView(this.stage);
    }


}
