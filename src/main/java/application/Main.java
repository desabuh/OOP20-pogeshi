package application;

import java.util.concurrent.CompletableFuture;

import com.google.inject.Guice;



import com.google.inject.Injector;

import controllers.BattleController;
import controllers.BattleControllerImpl;
import controllers.Controller;
import guicemodule.BattleModule;
import guicemodule.ControllerModule;
import guicemodule.WorldMapModule;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.JavafxView;
import views.View;
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
        
        Injector injector = Guice.createInjector(new WorldMapModule(stage));
        
        SceneManager.provideControllerFactory(injector::getInstance);
        CompletableFuture<Scene> completableScene = 
                CompletableFuture.supplyAsync(() -> SceneManager.of(LAYOUT.WORLDMAP).getScene());
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
