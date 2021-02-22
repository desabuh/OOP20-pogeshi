package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public interface BattleView extends View {

    /**
     * Updates the label to display the player's amount of available mana to spend and the amount of max mana.
     * @param unspent The amount of mana available
     * @param max The maximum amount of mana
     * */
    void updateManaLabel(int unspent, int max);

    /**
     * Updates the enemy's health and shield labels.
     * @param health The amount of health to display
     * @param shield The amount of shield to display
     * */
    void updateEnemyStats(int health, int shield);

    /**
     * Updates the player's health and shield labels.
     * @param health The amount of health to display
     * @param shield The amount of shield to display
     * */
    void updatePlayerStats(int health, int shield);

    /**
     * Visually adds a card to the player's hand.
     * @param path The image to be displayed
     * @param description The description that will show up when the image is hovered with the mouse pointer
     * @param onClick the actions to perform when the image is clicked
     * */
    void addCardToHand(String path, Text description, EventHandler<MouseEvent> onClick);

    /**
     * Shows how much damage the enemy has taken and hides the player's damage counter.
     * @param amount The amount of damage to display. It will display as negative.
     * */
    void showDamageToEnemy(int amount);

    /**
     * Shows how much damage the player has taken and hides the enemy's damage counter.
     * @param amount The amount of damage to display. It will display as negative.
     * */
    void showDamageToPlayer(int amount);

    /**
     * Displays the text that warns the user that a card costs too much to be played for a certain amount of seconds.
     * */
    void displayNotEnoughMana();

    /**
     * Removes (visually) all the cards from the player's hand.
     * */
    void resetHand();

    /**
     * Sets the actions to execute when the button to end the current turn has been pressed.
     * @param buttonClicked the actions to perform
     * */
    void setEndTurnEvent(EventHandler<ActionEvent> buttonClicked);

    /**
     * Initialize all the fields to access the view's elements.
     * @implNote This is useful since when the controller instantiates the view, the stage and scene are not yet set, and would
     * crash if the params were used before so.
     * This function should be called by the controller when the stage and scene have been set.
     * */
    void initializeParams();
}
