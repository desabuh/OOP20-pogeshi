package controllers.maincontroller;

/**
 * object to define the next component to dispatch the control to based on a request.
 *
 */
public interface Dispatcher {

    /**
     * function dispatch a component.
     * @param request on the basis of which dispatch is performed
     *
     */
    void dispatch(String request);
}
