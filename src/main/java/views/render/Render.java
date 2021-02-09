package views.render;

public final class Render {

    private int width;
    private int heigth;
    private int layer;

    public Render(final int width, final int height, final int layer) {
        this.width = width;
        this.heigth = height;
        this.layer = layer;
    }

    public int getWidth() {
        return this.width;
    }


    public int getHeigth() {
        return this.heigth;
    }


    public int getLayer() {
        return this.layer;
    }



}
