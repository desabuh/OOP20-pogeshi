package controllers.mainmenu;

import controllers.Controller;

public interface MainMenuController extends Controller {

    /**
     * Change {@code scene} to world map.
     */
    void playClick();

    /**
     * Change {@code Pane} to statistics.
     */
    void statisticsClick();

    /**
     * Change {@code scene} to world map but before that reset {@code Account} saves.
     */
    void newGameClick();

    /**
     * Change {@code scene} to deck creation.
     */
    void createDeckClick();

    /**
     * Close the application.
     */
    void exitClick();

    /**
     * Change {@code Pane} to main menu.
     */
    void backClick();

    /**
     * Change {@code Pane} to main menu.
     */
    void nextClick();

}
