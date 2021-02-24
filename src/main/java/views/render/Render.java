package views.render;

import javafx.scene.paint.Color;

public final class Render {

    private int width;
    private int heigth;
    private int layer;
    private Color color;

    public Render(final int width, final int height, final int layer, final Color color) {
        this.width = width;
        this.heigth = height;
        this.layer = layer;
        this.color = color;
    }

    public int getWidth() {
        return this.width;
    }


    public Color getColor() {
        return this.color;
    }

    public int getHeigth() {
        return this.heigth;
    }


    public int getLayer() {
        return this.layer;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
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
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (heigth != other.heigth)
            return false;
        if (layer != other.layer)
            return false;
        if (width != other.width)
            return false;
        return true;
    }



}
