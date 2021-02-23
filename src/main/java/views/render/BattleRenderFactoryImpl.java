package views.render;

import javafx.scene.paint.Color;

public final class BattleRenderFactoryImpl implements RenderFactory {
    /**
     * The width of the object to render.
     * */
    public static final int RENDER_WIDTH = 100;

    /**
     * The height of the object to render.
     * */
    public static final int RENDER_HEIGHT = 100;

    /**
     * The entity's layer.
     * */
    public static final int ENTITY_LAYER = 1;

    private Render r;
    @Override
    public Render renderPlayer() {
        r = new RenderBuilder()
                .setHeigth(RENDER_HEIGHT)
                .setWidth(RENDER_WIDTH)
                .setLayer(ENTITY_LAYER)
                .setColor(Color.DARKGREEN)
                .build();
        return r;
    }

    @Override
    public Render renderEnemy() {
        r = new RenderBuilder()
                .setHeigth(RENDER_HEIGHT)
                .setWidth(RENDER_WIDTH)
                .setLayer(ENTITY_LAYER)
                .setColor(Color.BLACK)
                .build();
        return r;
    }

    @Override
    public Render renderEnemyBoss() {
        r = new RenderBuilder()
                .setHeigth(RENDER_HEIGHT)
                .setWidth(RENDER_WIDTH)
                .setLayer(ENTITY_LAYER)
                .setColor(Color.RED)
                .build();
        return r;
    }

}
