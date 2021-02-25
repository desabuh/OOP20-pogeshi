package controllers;

import views.View;

/**
 * 
 * basic interface to define basic functionality for controllers.
 *
 */
public interface Controller {

    /**
     * provide the view associated with the controller.
     * @return the view the view associated with the controller
     */
    View getView();

    /**
     * callback function invoked by MainController. 
     * @param data to provide for the Controller 
     */
    void callBackAction(Object data);

}
