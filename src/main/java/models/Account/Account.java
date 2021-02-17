package models.Account;

import java.io.IOException;
import java.util.List;

import models.Card;
import models.Deck;

public interface Account {

    /**
     * Return the {@code deck} of the {@code Account}.
     * @return  Value of {@code Deck}.
     */
    Deck getDeck();

    /**
     * Return the {@code List<Crad>} of the cards not in the deck of the {@code Account}.
     * @return  {@code List<Card>} of {@code remaining cards}.
     */
    List<Card> getRemainingCards();

    /**
     * Return the {@code statistics} of the {@code Account}.
     * @return  {@code obj statistics}.
     */
    Statistics getStatistics();

    /**
     * Update the number of wins and add a new card to the {@code remaining cards}.
     * @throws IOException      Throw an IOExeption in case the ListOfCards.json default file is missing or corrupted.
     */
    void win() throws IOException;

    /**
     * Update the number of loses.
     */
    void lose();

    /**
     * Add a card to the {@code deck} and remove it from the {@code remaining cards}.
     * @param card  {@code obj card} to be added to the {@code deck} and removed from the {@code remaining cards}.
     */
    void addCardToDeck(Card card);

    /**
     * Remove a card from the {@code deck} and add it to the {@code remaining cards}.
     * @param card  {@code obj card} to be removed from the {@code deck} and added to the {@code remaining cards}.
     */
    void removeCardFromDeck(Card card);

    /**
     * Save all {@code Account} data on file.
     */
    void save();

    /**
     * Reset all {@code Account} data on file.
     */
    void deleteSaves();

}
