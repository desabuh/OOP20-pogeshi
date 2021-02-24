package controllers;


import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.base.Suppliers;
import com.google.inject.Inject;

import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import controllers.maincontroller.SwitchControllerRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import models.Character.EnemyImp;
import models.Character.Player;
import models.Character.PlayerImp;
import models.GameMap.Point2D;
import models.GameMap.WorldMap;
import models.deck.Deck;
import notifier.EventBus;
import views.View;
import views.render.Render;
import views.render.RenderFactory;
import views.render.TransformPositionFunction;
import views.render.WorldMapRenderFactory;
import views.scene.SceneManager;
import views.scene.layout.LAYOUT;

public final class WorldMapController implements Controller {

    /**
     * provide the rapport between a visual movement unit and a logical one.
     */
    public static final int DELTA = 100;

    /**
     * function to provide a mapping from logic movement to visual movement.
     */
    public static final TransformPositionFunction<Point2D, Point2D> DISPLAY_FUN = p -> p.sum((DELTA - 1) * p.getX(), (DELTA - 1) * p.getY());


    private View worldView;
    private WorldMap worldMap;

    private EventBus<Request<LAYOUT, ? extends Object>> notifier;


    private RenderFactory renderFactory = new WorldMapRenderFactory();

    private Point2D playerPosition;

    @Inject
    public WorldMapController(final WorldMap worldMap, final View worldView, final MainController mainController, final EventBus<Request<LAYOUT, ? extends Object>> notifier) {
        this.worldMap = worldMap;
        this.playerPosition = DISPLAY_FUN.applyTransform(this.worldMap.getPlayer().getPosition());
        this.worldView = worldView;
        this.notifier = notifier;
        this.notifier.register(mainController);
    }

    @FXML 
    private void initialize() {
        /**
         * the scene is setted for the view only when javaFX MainThread is loaded
         */
        Platform.runLater(() ->  this.initializeView());
    }

    private void initializeView() {

        this.worldView.setScene(SceneManager.of(LAYOUT.WORLDMAP).getScene());

        this.worldView.updateEntity(this.renderFactory.renderPlayer(), playerPosition, playerPosition);

        this.worldMap.getEnemies().stream()
        .map(EnemyImp::getPosition)
        .map(DISPLAY_FUN::applyTransform)
        .collect(Collectors.toList())
        .forEach(p -> this.worldView.updateEntity(this.renderFactory.renderEnemy(), p, p));
    }



    private void defeatEnemy(final EnemyImp enemy) {
        this.worldMap.removeEnemy(enemy);
        Point2D newPos = DISPLAY_FUN.applyTransform(enemy.getPosition());
        this.worldView.updateEntity(new Render(0, 0, 1, Color.WHITE), newPos, newPos);


        Optional<EnemyImp> boss = this.worldMap.getBoss();

        if (boss.isPresent()) {
            boss.map(EnemyImp::getPosition)
            .map(DISPLAY_FUN::applyTransform)
            .ifPresentOrElse(p -> this.worldView.updateEntity(this.renderFactory.renderEnemyBoss(), p, p), () -> { });

        }

        this.checkRemaingEnitities();


        this.notifier
        .notifyListener(new SwitchControllerRequest<LAYOUT, Player>(LAYOUT.BATTLE, Suppliers.ofInstance(this.worldMap.getPlayer())));

    }


    private void checkRemaingEnitities() {
        if (this.worldMap.getEnemies().isEmpty()) {
            this.notifier
            .notifyListener(new SwitchControllerRequest<LAYOUT, Boolean>(LAYOUT.ACCOUNT, Suppliers.ofInstance(true)));
        }
    }

    @FXML
    void interact() {
        this.worldMap.playerInteract()
        .ifPresentOrElse(this::defeatEnemy, () -> { });
    }

    @FXML
    void movePlayer(final KeyEvent keyEvent) {

        Optional<Point2D> newPos = Optional.empty();

        if (keyEvent.getCode().equals(KeyCode.D)) { 
            newPos = this.worldMap.updatePlayerPosition(MOVEMENT.RIGTH); 
        }

        else if (keyEvent.getCode().equals(KeyCode.A)) {
            newPos = this.worldMap.updatePlayerPosition(MOVEMENT.LEFT);
        }

        else if (keyEvent.getCode().equals(KeyCode.W)) {
            newPos = this.worldMap.updatePlayerPosition(MOVEMENT.UP);
        }

        else if (keyEvent.getCode().equals(KeyCode.S)) {
            newPos = this.worldMap.updatePlayerPosition(MOVEMENT.DOWN);
        }


        if (newPos.isPresent()) {
            newPos = Optional.of(DISPLAY_FUN.applyTransform(newPos.get()));
            this.worldView.updateEntity(renderFactory.renderPlayer(), this.playerPosition, newPos.get());
            this.playerPosition = newPos.get();
        }


    }

    @Override
    public View getView() {
        return this.worldView;
    }

    @Override
    public void callBackAction(final Object data) {
        if (data instanceof Player) {
            this.worldMap.setPlayer((Player) data);
        }
        if (data instanceof Deck) {
            this.worldMap.setPlayer(new PlayerImp((Deck) data));
        }
        this.checkRemaingEnitities();

    }
}
