package application;

import com.google.inject.Guice;



import com.google.inject.Injector;

import controllers.BattleController;
import controllers.BattleControllerImpl;
import controllers.Controller;
import guicemodule.BattleModule;
import guicemodule.ControllerModule;
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
        Injector injector = Guice.createInjector(new BattleModule());
        SceneManager.provideControllerFactory(injector::getInstance);

        BattleController b = injector.getInstance(BattleControllerImpl.class);
        BattleController b1 = injector.getInstance(BattleControllerImpl.class);
        System.out.println(b.equals(b1));
        System.exit(1);
        //b.setTest();

        View view = new JavafxView(stage);
        view.loadScene(INITIAL_LAYOUT);
    }

    /**
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}
