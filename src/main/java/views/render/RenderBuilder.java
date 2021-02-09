package views.render;

/**
 * 
 * Builder to provide all data needed for render an entity.
 *
 */
public final class RenderBuilder {
    private int width;
    private int height;
    private int layer;

    /**
     * 
     * @param width of the rendered object.
     * @return a RenderBuilder
     */
    public RenderBuilder setWidth(final int width) {
        this.width = width;
        return this;
    }

    /**
     * 
     * @param height of the rendered object
     * @return a RenderBuilder
     */
    public RenderBuilder setHeigth(final int height) {
        this.height = height;
        return this;
    }

    /**
     * 
     * @param layer to display
     * @return a RenderBuilder
     */
    public RenderBuilder setLayer(final int layer) {
        this.layer = layer;
        return this;
    }

    /**
     * 
     * @return a new Render object
     */
    public Render build() {
        return new Render(width, height, layer);
    }


}
