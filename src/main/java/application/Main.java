package application;

import java.util.concurrent.CompletableFuture;


import com.google.inject.Guice;



import com.google.inject.Injector;

import controllers.Controller;
import controllers.maincontroller.MainController;
import controllers.maincontroller.SwitchControllerRequest;
import guicemodule.BattleModule;
import guicemodule.ComunicationModule;
import guicemodule.MenuModule;
import guicemodule.WorldMapModule;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    /**
     * starting layout to be loaded.
     */
    public static final LAYOUT INITIAL_LAYOUT = LAYOUT.BATTLE;

    @Override
    public void start(final Stage stage) throws Exception {
        Injector injector = Guice.createInjector(new WorldMapModule(stage),
                new ComunicationModule(),
                new MenuModule(stage),
                new BattleModule(stage));


        SceneManager.provideControllerFactory(injector::getInstance);


        CompletableFuture<Scene> completableScene = 
                CompletableFuture.supplyAsync(() -> SceneManager.of(LAYOUT.ACCOUNT).getScene());
        Scene scene = completableScene.get();


        stage.sizeToScene();
        stage.setTitle("Pogeshi");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();  

    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
