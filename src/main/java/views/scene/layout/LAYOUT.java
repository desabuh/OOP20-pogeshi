package views.scene.layout;

/**
 *  Enum to provide layout path string.
 */
public enum LAYOUT {
    /**
     * BATTLE layout.
     */
    BATTLE("/layouts/battle.fxml"),
    /**
     * WORLDMAP layout.
     */
    WORLDMAP("/layouts/worldmap.fxml"),
    /**
     * ACCOUNT layout.
     */
    ACCOUNT("/layouts/MainMenu.fxml"),
    /**
     * ACCOUNT layout.
     */
    DECKCREATION("/layouts/deckCreation.fxml");

    private final String pathString;

    /**
     * retrieve path string.
     * @return path string
     */
    public String getPathString() {
        return this.pathString;
    }

    LAYOUT(final String pathString) {
        this.pathString = pathString;
    }

}
