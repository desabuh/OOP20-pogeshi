package views;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.GameMap.Point2D;
import views.render.Render;
import views.scene.layout.LAYOUT;

public final class AccountView extends JavafxView {

    private Label labelEnd;

    private Label labelWins;

    private Label labelLoses;

    private Label labelUnlockedCards;

    private Pane paneEnd;

    private Pane paneMenu;

    private Pane paneStatistics;

    public AccountView(final Stage stage) {
        super(stage, LAYOUT.ACCOUNT);
    }

    public void initializeParams() {
        Scene scene = this.getScene();
        this.labelEnd = (Label) scene.lookup("#labelEnd");
        this.labelWins = (Label) scene.lookup("#labelWins");
        this.labelLoses = (Label) scene.lookup("#labelLoses");
        this.labelUnlockedCards = (Label) scene.lookup("#labelUnlockedCards");
        this.paneEnd = (Pane) scene.lookup("#paneEnd");
        this.paneMenu = (Pane) scene.lookup("#paneMenu");
        this.paneStatistics = (Pane) scene.lookup("#paneStatistics");
    }

    @Override
    public void updateEntity(final Render render, final Point2D x, final Point2D y) { }

    public void changeToStatistics(final int wins, final int loses, final int unlockedCards) {
        this.paneMenu.setVisible(false);
        this.paneStatistics.setVisible(true);
        this.labelWins.setText("Vittorie: " + String.valueOf(wins));
        this.labelLoses.setText("Sconfitte:" + String.valueOf(loses));
        this.labelUnlockedCards.setText("Unlocked cards: " + String.valueOf(unlockedCards));
    }

    public void changeBackFromStatistics() {
        this.paneMenu.setVisible(true);
        this.paneStatistics.setVisible(false);
    }

    public void changeToEnd() {
        this.paneMenu.setVisible(false);
        this.paneEnd.setVisible(true);
    }

    public void changeEndLabelText(final String text) {
        this.labelEnd.setText(text);
    }

    public void changeBackFromEnd() {
        this.paneEnd.setVisible(false);
        this.paneMenu.setVisible(true);
    }
}
