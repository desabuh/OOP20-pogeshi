package views.drawer;

/**
 * decorator interface to provide movement functionality to {@link Shape} instances.
 *
 */
public interface MovableShape extends Shape  {

    /**
     * move shape object to the new destination.
     * @param x target xAxis
     * @param y target yAxis
     */
    void moveTo(int x, int y);
}
