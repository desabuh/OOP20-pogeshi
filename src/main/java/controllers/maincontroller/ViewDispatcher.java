package controllers.maincontroller;

import javafx.scene.Scene;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;


public final class ViewDispatcher implements Dispatcher {

    /**
     * dispatch to a new scene for the specific request.
     */
    @Override
    public void dispatch(final String request) {
        Scene dispatchScene = SceneManager.of(LAYOUT.valueOf(request)).getScene();
        /*retrieve View for this Scene(working)*/
    }

}
