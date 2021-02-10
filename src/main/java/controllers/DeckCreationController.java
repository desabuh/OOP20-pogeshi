package controllers;

/**
 *
 */
public interface DeckCreationController extends Controller {
    void changeCardDescription();

    void removeCardFromDeck();

    void addCardToDeck();

    void saveDeck();

    void exitWhitoutSaving();
}
