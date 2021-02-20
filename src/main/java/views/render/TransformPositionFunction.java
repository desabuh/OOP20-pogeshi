package views.render;

/**
 * 
 *interface to provide a mapping rule to map logical transform position to its view display position.
 *
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface TransformPositionFunction<T, R> {

    /**
     * 
     * @param position logical position for a model entity
     * @return visual position for the visualization
     */
    R applyTransform(T position);
}
