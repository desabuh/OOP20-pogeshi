package models.battle;

import java.util.Optional;

import models.CardIterator;
import models.Account.AccountImp;
import models.Character.Character;
import models.Character.EnemyImp;
import models.Character.Player;
import models.Character.PlayerImp;
import models.GameMap.Point2DImp;
import models.deck.DeckImpl;
import models.deck.card.Card;

public final class BattleImpl implements Battle {

    /**
     * The number of cards the player starts a new battle with.
     * */
    public static final int BASE_STARTING_CARDS = 3;
    /**
     * The maximum amount of cards the player can have in his hand at the same time.
     * */
    public static final int MAX_CARDS_IN_HAND = 5;
    private int playerUnusedCombatMana;
    private Turn turn = Turn.PLAYER; 
    private boolean hasBeenInitialized = false;
    private boolean hasBattleFinished = false;
    private Player p;
    private EnemyImp e;
    private CardIterator deckIterator;
    private CardIterator enemyDeckIterator;

    public BattleImpl(final Player p, final EnemyImp e) {
        this.p = p;
        this.e = e;
    }

    public BattleImpl() {
        p = new PlayerImp(new DeckImpl());
        //e = new EnemyImp(new DeckImpl(), 1, Point2DImp.setPoint(0, 0));
    }

    public BattleImpl(final Player p) {
        this.p = p;
        //e = new EnemyImp(new DeckImpl(), new Point2DImp(0, 0));
    }

    public void setPlayer(final Player player) {
        this.checkBattleStatus();
        this.p = player;
    }

    @Override
    public void endTurn() {
        this.checkBattleStatus();
        this.turn = (turn == Turn.PLAYER) ? Turn.ENEMY : Turn.PLAYER;
        if (turn == Turn.PLAYER) {
            drawCard();
            p.setMana(p.getMana() + 1);
            playerUnusedCombatMana = p.getMana();
        }
    }

    public void initializeCharacters() {
        //checkBattleStatus();
        e = new EnemyImp(new DeckImpl(), 10, Point2DImp.setPoint(0, 0));
        if (hasBeenInitialized) {
            throw new IllegalStateException("Both opponents of the battle have already been initialized.");
        }
        deckIterator = new CardIterator(p.getDeck().getCards());
        enemyDeckIterator = new CardIterator(e.getDeck().getCards());
        loadEnemysDeck();
        for (int i = 0; i < BASE_STARTING_CARDS; i++) {
            drawCard();
        }
        playerUnusedCombatMana = p.getMana();
        hasBeenInitialized = true;
    }

    public boolean playCard(final int index) {
        checkBattleStatus();
        Card played;
        if (turn == Turn.PLAYER) {
            if (index >= p.getHand().getCards().size()) {
                throw new IndexOutOfBoundsException("Tried to play a card that's not in the player's hand");
            }
            if (isPlayable(p.getHand().getCards().get(index))) {
                played = p.getHand().getCards().get(index);
                setPlayerUnusedCombatMana((getPlayerUnusedCombatMana() - played.getCost()));
                inflictDamage(played);
                p.getHand().removeCard(index);
            } else {
               return false;
            }
        } else {
            played = enemyDeckIterator.next();
            inflictDamage(played);
        }
        return true;
    }

    @Override
    public Character currentTurn() {
        return turn == Turn.PLAYER ? new PlayerImp(new DeckImpl()) : new EnemyImp(new DeckImpl(), new Point2DImp(0, 0));
    }

    private boolean isPlayable(final Card c) {
        return turn == Turn.ENEMY ? true : getPlayerUnusedCombatMana() >= c.getCost();
    }

    @Override
    public boolean checkBattleEnd() {
        if (p.getHealth() <= 0 || e.getHealth() <= 0) {
            hasBattleFinished = true;
        }
        return hasBattleFinished;
    }

    public Player getPlayer() {
        return p;
    }

    public EnemyImp getEnemy() {
        return e;
    }

    public int getPlayerUnusedCombatMana() {
        return playerUnusedCombatMana;
    }

    private void setPlayerUnusedCombatMana(final int amount) {
        playerUnusedCombatMana = amount;
    }

    private void inflictDamage(final Card c) {
        int damage = c.getAttack();
        if (turn == Turn.PLAYER) {
            if (e.getShield() > damage) {
                e.setShield(e.getShield() - damage);
                damage = 0;
            } else if (e.getShield() > 0) {
                damage -= e.getShield();
                e.setShield(0);
            }
            e.setHealth(e.getHealth() - damage);
            if (c.getShield() > 0) {
                p.setShield(p.getShield() + c.getShield());
            }
        } else {
            if (p.getShield() > damage) {
                p.setShield(p.getShield() - damage);
                damage = 0;
            } else if (p.getShield() > 0) {
                damage -= p.getShield();
                p.setShield(0);
            }
            p.setHealth(p.getHealth() - damage);
            if (c.getShield() > 0) {
                e.setShield(e.getShield() + c.getShield());
            }
        }
    }

    private void drawCard() {
        Optional<Card> next = Optional.empty();

        while (next.isEmpty()) {
            next = Optional.of(deckIterator.next());
            if (p.getHand().getCards().contains(next.get())) {
                next = Optional.empty();
            }
        }

        if (turn == Turn.PLAYER && p.getHand().getCards().size() < MAX_CARDS_IN_HAND) {
            p.getHand().addCard(next.get());
        }
    }

    /**
     * Fills the enemy's deck with the default deck's cards.
     * */
    private void loadEnemysDeck() {
        new AccountImp().getDeck().getCards().stream()
                                                .forEach((card) -> e.getDeck().addCard(card));
    }

    private void checkBattleStatus() {
        if (hasBattleFinished) {
            throw new IllegalStateException("You cannot perform any more actions because the battle already ended.");
        }
        if (!hasBeenInitialized) {
            throw new IllegalStateException("The opponents have not been initialized.");
        }
    }

    public boolean hasPlayerWon() {
        if (!hasBattleFinished) {
            throw new IllegalStateException("The battle has not ended yet.");
        }
        return p.getHealth() > 0;
    }

    public void reset() {
        p.setShield(0);
        hasBattleFinished = false;
        hasBeenInitialized = false;
        int cardsInHand = p.getHand().getCards().size();
        for (int i = 0; i < cardsInHand; i++) {
            p.getHand().removeCard(0);
        }
        p.setMana(1);
        this.playerUnusedCombatMana = p.getMana();
        this.initializeCharacters();
    }

}