package views.scene.layout;


/**
 *  Enum to provide layout path string.
 */
public enum LAYOUT {
    /**
     * BATTLE layout.
     */
    BATTLE("layouts/battle.fxml"),
    /**
     * WORLDMAP layout.
     */
    WORLDMAP("layouts/worldmap.fxml"),
    /**
     * ACCOUNT layout.
     */
    ACCOUNT("layouts/account.fxml");

    private final String pathString;

    public String getPathString() {
        return this.pathString;
    }

    LAYOUT(final String pathString) {
        this.pathString = pathString;
    }

}
