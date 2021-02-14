package models;

import java.io.File;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public final class BattleImpl implements Battle {

    /**
     * The number of cards the player starts a new battle with.
     * */
    public static final int BASE_STARTING_CARDS = 3;
    private int playerUnusedCombatMana;
    private Turn turn = Turn.PLAYER;
    private boolean hasBeenInitialized = false;
    private Player p;
    private Enemy e;

    @FXML
    private Label LBLEnemyDamage;

    public BattleImpl(final Player p, final Enemy e) {
        this.p = p;
        this.e = e;
    }

    public BattleImpl() {
        p = new PlayerImp(new DeckImpl());
        e = new EnemyImp(new DeckImpl());
    }

    @Override
    public void endTurn() {
        turn = (turn == Turn.PLAYER) ? Turn.ENEMY : Turn.PLAYER;
        if (turn == Turn.PLAYER) {
            Optional<Card> next = p.getDeck().popCard();
            if (next.isPresent()) {
                p.getHand().addCard(next.get());
            }
            p.setMana(p.getMana() + 1);
            playerUnusedCombatMana = p.getMana();
        } else {
            //playCard()
        }
    }

    // TODO: Add base cards to the enemy
    public void initializeCharacters() {
        if (hasBeenInitialized) {
            throw new IllegalStateException("Both opponents of the battle have already been initialized.");
        }
        p.getDeck().addCard(new CardImpl(3, 0, 1, "Carta prova", "res" + File.separator + "images" + File.separator + "AceOfHearts.png"));
        p.getDeck().addCard(new CardImpl(3, 0, 10, "Carta prova costosa", "res" + File.separator + "images" + File.separator + "AceOfHearts.png"));
        for (int i = 0; i < BASE_STARTING_CARDS; i++) {
            Optional<Card> next = p.getDeck().popCard();
            if (next.isPresent()) {
                p.getHand().addCard(next.get());
            }
        }
        playerUnusedCombatMana = p.getMana();
        hasBeenInitialized = true;
    }

    public boolean playCard(final int index) {
        if (turn == Turn.PLAYER && isPlayable(p.getHand().getCards().get(index))) {
            if (turn == Turn.PLAYER) {
                Card played = p.getHand().getCards().get(index);
                System.out.println("Player!");
                setPlayerUnusedCombatMana((getPlayerUnusedCombatMana() - played.getCost()));
                if (played.getDefense() > 0) {
                    p.setShield(p.getShield() + played.getDefense());
                }
                p.getHand().removeCard(index);
            } else {
                System.out.println("Enemy!");
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Character currentTurn() {
        return turn == Turn.PLAYER ? new PlayerImp(new DeckImpl()) : new EnemyImp(new DeckImpl());
    }

    private boolean isPlayable(final Card c) {
        return turn == Turn.ENEMY ? true : getPlayerUnusedCombatMana() >= c.getCost();
    }

    // TODO: Check also the enemy's health when implemented
    @Override
    public boolean checkBattleEnd() {
        return p.getHealt() <= 0 /*|| e.getHealt() <= 0*/;
    }

    public Player getPlayer() {
        return p;
    }

    public Enemy getEnemy() {
        return e;
    }

    public int getPlayerUnusedCombatMana() {
        return playerUnusedCombatMana;
    }

    private void setPlayerUnusedCombatMana(final int amount) {
        playerUnusedCombatMana = amount;
    }

}