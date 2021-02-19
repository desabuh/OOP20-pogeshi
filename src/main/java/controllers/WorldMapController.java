package controllers;

import java.util.Optional;

import com.google.inject.Inject;

import controllers.maincontroller.MainController;
import controllers.maincontroller.Request;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import listener.Listener;
import models.Character;
import models.GameMap.Point2D;
import models.GameMap.Point2DImp;
import models.GameMap.WorldMap;
import notifier.EventBus;
import notifier.GuavaEventBusAdapter;
import views.View;
import views.render.Render;
import views.render.TransformPositionFunction;
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
    public static final TransformPositionFunction<Point2D, Point2D> DISPLAY_FUN = 
        p -> p.sum((DELTA - 1) * p.getX(), (DELTA - 1) * p.getY());
    
    
    private View worldView;
    private WorldMap worldMap;
    private final GuavaEventBusAdapter<Integer> event = new GuavaEventBusAdapter<Integer>(new com.google.common.eventbus.EventBus());
    private MainController mc = new MainController();

    private Point2D pos = Point2DImp.setPoint(0, 0);

    @Inject
    public WorldMapController(final WorldMap worldMap, final View worldView) {
        this.worldMap = worldMap;
        this.worldView = worldView;
        this.event.register(mc);
        System.out.println(this);
    }

    @FXML 
    private void initialize() {
        /**
         * the scene is setted for the view only when javaFX MainThread is loaded
         */
        Platform.runLater(() ->  this.worldView.setScene(SceneManager.of(LAYOUT.WORLDMAP).getScene()));
    }


    @FXML
    void drawPlayer() {
        //this.worldView.updateEntity(new Render(100, 100, 1), pos, pos);
        
       // System.out.println(this.worldMap.getPlayer().getPosition().toString());
       // System.out.println(this.worldMap.updatePlayerPosition(MOVEMENT.DOWN));
       // System.out.println(this.worldMap.getPlayer().getPosition().toString());



        //event.notifyListener(4);

        //event.notifyListener(4);
        /*
        event.notifyListener(new Request<Object>() {

            @Override
            public String getMessageCode() {
                return "";
            }

            @Override
            public Object supplyData() {
                return null;
            }
        }); */

    }

    @FXML
    void movePlayer(final KeyEvent keyEvent) {
        /*temporany for testing, add enum*/

        Optional<Point2D> newPos = Optional.empty();
        
        if (keyEvent.getCode().equals(KeyCode.D)) {
            newPos = this.worldMap.updatePlayerPosition(MOVEMENT.RIGTH);
            if (newPos.isPresent()) {
                this.pos = DISPLAY_FUN.applyTransform(newPos.get());
                //this.worldView.updateEntity(new Render(100, 100, 1), pos, new Point2D(this.pos.getX(), this.pos.getY() - 100));
            }
        }
        
        /*
        if (keyEvent.getCode().equals(KeyCode.W)) {
            this.worldView.updateEntity(new Render(100, 100, 1), pos, new Point2D(this.pos.getX(), this.pos.getY() - 100));
            this.pos = new Point2D(this.pos.getX(), this.pos.getY() - 100);
        }

        else if (keyEvent.getCode().equals(KeyCode.S)) {
            this.worldView.updateEntity(new Render(100, 100, 1), pos, new Point2D(this.pos.getX(), this.pos.getY() + 100));
            this.pos = new Point2D(this.pos.getX(), this.pos.getY() + 100);
        }

        else if (keyEvent.getCode().equals(KeyCode.A)) {
            this.worldView.updateEntity(new Render(100, 100, 1), pos, new Point2D(this.pos.getX() - 100, this.pos.getY()));
            this.pos = new Point2D(this.pos.getX() - 100, this.pos.getY());
        }
        else if (keyEvent.getCode().equals(KeyCode.D)) {
            this.worldView.updateEntity(new Render(100, 100, 1), pos, new Point2D(this.pos.getX() + 100, this.pos.getY()));
            this.pos = new Point2D(this.pos.getX() + 100, this.pos.getY());
        } 
        */


    }

    @Override
    public View getView() {
        return this.worldView;
    }

    @Override
    public void callBackAction(Object data) {
        System.out.println(this);

    }
}
