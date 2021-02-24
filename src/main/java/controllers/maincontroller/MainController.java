package controllers.maincontroller;

import com.google.common.eventbus.Subscribe;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import listener.Listener;
import views.View;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

/**
 * 
 * This Class is used as router to reidirect the execution to a specific triads of
 * model,view and controller based on the requested layout.
 *
 */
public final class MainController implements Listener<Request<LAYOUT, ? extends Object>> {


    @Subscribe
    @Override
    public void handleEvent(final Request<LAYOUT, ? extends Object> event) {
        FXMLLoader loader =  SceneManager.of(event.getRoutingData()).getLoader();
        Controller controller = loader.<Controller>getController();



        controller.callBackAction(event.supplyData());

        View view = controller.getView();

        view.loadScene(event.getRoutingData());


    }






}
