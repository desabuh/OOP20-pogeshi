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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + heigth;
        result = prime * result + layer;
        result = prime * result + width;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Render other = (Render) obj;
        if (heigth != other.heigth)
            return false;
        if (layer != other.layer)
            return false;
        if (width != other.width)
            return false;
        return true;
    }

    public int getHeigth() {
        return this.heigth;
    }


    public int getLayer() {
        return this.layer;
    }



}
