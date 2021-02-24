package controllers.maincontroller;

/**
 * 
 * an interface to define basic request functionality.
 *
 * @param <T> type of data used by receiver to redirect the request
 * @param <R> data used by receiver to supply the request data
 */
public interface Request<T, R> {

    /**
     * retrieve destination for this request.
     * @return T the route destination
     */
    T getRoutingData();

    /**
     * retrieve data provided by this request.
     * @return R data 
     */
    R supplyData();

}
