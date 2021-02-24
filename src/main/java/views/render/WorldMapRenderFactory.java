package views.render;

import javafx.scene.paint.Color;


public final class WorldMapRenderFactory implements RenderFactory {

    /**
     * the background layer number.
     */
    public static final int BACKGROUND_LAYER = 2;
    /**
     * the entity layer number.
     */
    public static final int ENTITY_LAYER = 1;
    /**
     * size rendered for player.
     */
    public static final int PLAYER_RENDER_SIZE = 100;
    /**
     * size rendered the enemy.
     */
    public static final int ENEMY_RENDER_SIZE = 100;

    @Override
    public Render renderPlayer() {
        return new RenderBuilder()
                .setHeigth(PLAYER_RENDER_SIZE)
                .setWidth(PLAYER_RENDER_SIZE)
                .setLayer(ENTITY_LAYER)
                .setColor(Color.GREEN)
                .build();
    }

    @Override
    public Render renderEnemy() {
        return new RenderBuilder()
                .setHeigth(ENEMY_RENDER_SIZE)
                .setWidth(ENEMY_RENDER_SIZE)
                .setLayer(ENTITY_LAYER)
                .setColor(Color.RED)
                .build();
    }

    @Override
    public Render renderEnemyBoss() {
        return new RenderBuilder()
                .setHeigth(ENEMY_RENDER_SIZE)
                .setWidth(ENEMY_RENDER_SIZE)
                .setLayer(ENTITY_LAYER)
                .setColor(Color.BLUE)
                .build();
    }

}
