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

    @Override
    public Render renderPlayer() {
        return new RenderBuilder()
                    .setHeigth(RENDER_HEIGHT)
                    .setWidth(RENDER_WIDTH)
                    .setLayer(ENTITY_LAYER)
                    .setColor(Color.DARKGREEN)
                    .build();
    }

    @Override
    public Render renderEnemy() {
        return new RenderBuilder()
                    .setHeigth(RENDER_HEIGHT)
                    .setWidth(RENDER_WIDTH)
                    .setLayer(ENTITY_LAYER)
                    .setColor(Color.RED)
                    .build();
    }

    @Override
    public Render renderEnemyBoss() {
        return new RenderBuilder()
                    .setHeigth(RENDER_HEIGHT)
                    .setWidth(RENDER_WIDTH)
                    .setLayer(ENTITY_LAYER)
                    .setColor(Color.BLUE)
                    .build();
    }

}
