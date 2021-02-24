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

    /**
     * The color of the render to display the player.
     * */
    public static final Color PLAYER_COLOR = Color.DARKGREEN;

    /**
     * The color of the render to display the enemy.
     * */
    public static final Color ENEMY_COLOR = Color.RED;

    /**
     * The color of the render to display the boss.
     * */
    public static final Color BOSS_COLOR = Color.BLUE;

    @Override
    public Render renderPlayer() {
        return new RenderBuilder()
                    .setHeigth(RENDER_HEIGHT)
                    .setWidth(RENDER_WIDTH)
                    .setLayer(ENTITY_LAYER)
                    .setColor(PLAYER_COLOR)
                    .build();
    }

    @Override
    public Render renderEnemy() {
        return new RenderBuilder()
                    .setHeigth(RENDER_HEIGHT)
                    .setWidth(RENDER_WIDTH)
                    .setLayer(ENTITY_LAYER)
                    .setColor(ENEMY_COLOR)
                    .build();
    }

    @Override
    public Render renderEnemyBoss() {
        return new RenderBuilder()
                    .setHeigth(RENDER_HEIGHT)
                    .setWidth(RENDER_WIDTH)
                    .setLayer(ENTITY_LAYER)
                    .setColor(BOSS_COLOR)
                    .build();
    }

}
