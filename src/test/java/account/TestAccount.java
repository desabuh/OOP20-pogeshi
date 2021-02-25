package account;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import models.Account.Account;
import models.Account.AccountImp;
import models.deck.card.Card;
import models.deck.card.CardImpl;

public class TestAccount {

    private Account account;

    /**
     * Test if removing a card from the deck actually test if the card is present
     * and if the card get actually removed from the deck and added to the remainingCards.
     */
    @Test
    public void testRemoveCardFromDeck() {
        account = new AccountImp();
        account.deleteSaves();      //Reset to default status, remainingCards is empty.
        try {
            Card card = new CardImpl.Builder().
                    attack(1).cost(1).description("test").
                    name("test").resourcePath("res" + File.separator + "images" + File.separator + "card15.png").shield(1).build();
            this.account.removeCardFromDeck(card);

            /**
             * The card shouldn't be in the remaingCards since it's a dummy card
             * and isn't in the deck.
             */
            assertFalse(this.account.getRemainingCards().contains(card));

            card = this.account.getDeck().getCard().get();
            account.removeCardFromDeck(card);

            /**
             * The card should now be in the remaingCards.
             */
            assertTrue(this.account.getRemainingCards().contains(card));

            /**
             * The card shouldn't be in the deck now (There are no copies in the default deck).
             */
            assertFalse(this.account.getDeck().getCards().contains(card));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Test if adding a card to the deck actually test if the card is present
     * and if the card get actually removed from the remaingCards and added to the deck.
     */
    @Test
    public void testAddCardToDeck() {
        account = new AccountImp();
        account.deleteSaves();      //Reset to default status, remainingCards is empty.
        this.account.getDeck().getCards().remove(0);    //Otherwise the deck would be full.
        try {
            Card card = new CardImpl.Builder().
                    attack(1).cost(1).description("test").
                    name("test").resourcePath("res" + File.separator + "images" + File.separator + "card15.png").shield(1).build();
            this.account.addCardToDeck(card);

            /**
             * The card shouldn't be in the deck since it's a dummy card
             * and isn't in the remaingCards.
             */
            assertFalse(this.account.getDeck().getCards().contains(card));

            this.account.getRemainingCards().add(card);
            account.addCardToDeck(card);

            /**
             * The card should now be in the deck.
             */
            assertTrue(this.account.getDeck().getCards().contains(card));

            /**
             * The card shouldn't be in the remaingCards now.
             */
            assertFalse(this.account.getRemainingCards().contains(card));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Testing if after saving the deck and the remaining cards on File
     * loading them would give the same result.
     */
    @Test
    public void testSaveCards() {
        account = new AccountImp();
        account.deleteSaves();
        account.removeCardFromDeck(account.getDeck().getCard().get());
        account.removeCardFromDeck(account.getDeck().getCard().get());
        account.removeCardFromDeck(account.getDeck().getCard().get());
        account.removeCardFromDeck(account.getDeck().getCard().get());
        account.addCardToDeck(account.getRemainingCards().get(0));
        account.save();
        Account account2 = new AccountImp();

        /**
         * The cards in the deck should be the same in the account who saved them
         * and in the account2 who loaded them.
         */
        assertEquals(account.getDeck().getCards(), account2.getDeck().getCards());
        /**
         * The cards in the remaingCards should be the same in the account who
         * saved them and in the account2 who loaded them.
         */
        assertEquals(account.getRemainingCards(), account2.getRemainingCards());
    }

    /**
     * Testing if after winning in the remainingCards there is a new card and
     * if the wins in the statistics increment.
     * Also testing if the statistics.wins are saved.
     */
    @Test
    public void testWinning() {
        account = new AccountImp();
        account.deleteSaves();
        final int remainingCardsSize = account.getRemainingCards().size();
        final int wins = account.getStatistics().getWins();
        try {
            account.win();

            /**
             * The number of cards in the remaingCards should be the same as
             * the number saved in remainingCardsSize + 1.
             */
            assertEquals(remainingCardsSize + 1, account.getRemainingCards().size());

            /**
             * The number of wins in statistics should be the same as
             * the number saved in wins + 1.
             */
            assertEquals(wins + 1, account.getStatistics().getWins());
            Account account2 = new AccountImp();

            /**
             * The number of wins in statistics should be the same in
             * the account who saved them and in the account2 who loaded them.
             */
            assertEquals(account.getStatistics().getWins(), account2.getStatistics().getWins());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing if after losing the loses in the statistics increment.
     * Also testing if the statistics.loses are saved.
     */
    @Test
    public void testLosing() {
        account = new AccountImp();
        account.deleteSaves();
        final int loses = account.getStatistics().getLoses();
        account.lose();

        /**
         * The number of loses in statistics should be the same as
         * the number saved in loses + 1.
         */
        assertEquals(loses + 1, account.getStatistics().getLoses());
        Account account2 = new AccountImp();

        /**
         * The number of loses in statistics should be the same in
         * the account who saved them and in the account2 who loaded them.
         */
        assertEquals(account.getStatistics().getLoses(), account2.getStatistics().getLoses());
    }

}
