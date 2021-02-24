package views.scene.layout;

import java.io.File;

/**
 *  Enum to provide layout path string.
 */
public enum LAYOUT {
    /**
     * BATTLE layout.
     */
    BATTLE("layouts" + File.separator + "battle.fxml"),
    /**
     * WORLDMAP layout.
     */
    WORLDMAP("layouts" + File.separator + "worldmap.fxml"),
    /**
     * ACCOUNT layout.
     */
    ACCOUNT("layouts" + File.separator + "MainMenu.fxml"),
    /**
     * ACCOUNT layout.
     */
    DECKCREATION("layouts" + File.separator + "deckCreation.fxml"),
    /**
     * ACCOUNT layout.
     */
    STATISTICS("layouts" + File.separator + "StatisticsMenu.fxml");

    private final String pathString;

    public String getPathString() {
        return this.pathString;
    }

    LAYOUT(final String pathString) {
        this.pathString = pathString;
    }

}
