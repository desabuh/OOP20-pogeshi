package views.render;

import models.Enemy;
import models.Player;

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
    public static final int ENEMY_RENDER_SIZE = 50;

    /**
     * board size.
     */
    public static final int BOARD_SIZE = 600;

    @Override
    public Render render(final Player player) {
        return new RenderBuilder()
                .setHeigth(PLAYER_RENDER_SIZE)
                .setWeigth(PLAYER_RENDER_SIZE)
                .setLayer(ENTITY_LAYER)
                .build();
    }

    @Override
    public Render render(final Enemy enemy) {
        return new RenderBuilder()
                .setHeigth(ENEMY_RENDER_SIZE)
                .setWeigth(ENEMY_RENDER_SIZE)
                .setLayer(ENTITY_LAYER)
                .build();
    }

    @Override
    public Render renderBackGround() {
        return new RenderBuilder()
                .setHeigth(ENEMY_RENDER_SIZE)
                .setWeigth(ENEMY_RENDER_SIZE)
                .setLayer(ENTITY_LAYER)
                .build();
    }

}
