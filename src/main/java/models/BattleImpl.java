package models;

import java.util.Iterator;
import java.util.Optional;

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
    private Iterator<Card> deckIterator;

    public BattleImpl(final Player p, final EnemyImp e) {
        this.p = p;
        this.e = e;
    }

    public BattleImpl() {
        p = new PlayerImp(new DeckImpl());
        e = new EnemyImp(new DeckImpl(), new Point2DImp(0, 0));
    }

    public BattleImpl(final Player p) {
        this.p = p;
        e = new EnemyImp(new DeckImpl(), new Point2DImp(0, 0));
    }


    @Override
    public void endTurn() {
        checkBattleStatus();
        if (!hasBeenInitialized) {
            throw new IllegalStateException("The opponents have not been initialized.");
        }
        turn = (turn == Turn.PLAYER) ? Turn.ENEMY : Turn.PLAYER;
        if (turn == Turn.PLAYER) {
            drawCard();
            p.setMana(p.getMana() + 1);
            playerUnusedCombatMana = p.getMana();
        }
    }

    public void initializeCharacters() {
        checkBattleStatus();
        deckIterator = p.getDeck().getCards().iterator();
        if (hasBeenInitialized) {
            throw new IllegalStateException("Both opponents of the battle have already been initialized.");
        }
        loadEnemysDeck();
        for (int i = 0; i < BASE_STARTING_CARDS; i++) {
            drawCard();
        }
        playerUnusedCombatMana = p.getMana();
        hasBeenInitialized = true;
    }

    public boolean playCard(final int index) {
        checkBattleStatus();
        if (!hasBeenInitialized) {
            throw new IllegalStateException("The opponents have not been initialized.");
        }
        Card played;
        if (turn == Turn.PLAYER) {
            if (isPlayable(p.getHand().getCards().get(index))) {
                played = p.getHand().getCards().get(index);
                System.out.println("Player!");
                setPlayerUnusedCombatMana((getPlayerUnusedCombatMana() - played.getCost()));
                if (played.getShield() > 0) {
                    p.setShield(p.getShield() + played.getShield());
                }
                inflictDamage(played);
                p.getHand().removeCard(index);
            } else {
               return false;
            }
        } else {
            System.out.println("Enemy!");
            Optional<Card> next = e.getDeck().popCard();
            if (next.isPresent()) {
                played = next.get();
            } else {
                throw new IllegalStateException("Tried to draw a card from empty deck");
            }
            if (e.getDeck().getCards().isEmpty()) {
                loadEnemysDeck();
            }
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
        if (p.getHealth() <= 0) {
            //TODO: Transfer loss status to the AccountController
            //account.lose();
            hasBattleFinished = true;
        } else if (e.getHealth() <= 0) {
            //TODO: Transfer win status to the MainController
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
                p.setShield(e.getShield() - damage);
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
        Card next;
        while (true) {
            if (!deckIterator.hasNext()) {
                deckIterator = p.getDeck().getCards().iterator(); 
            }
            next = deckIterator.next();
            if (!p.getHand().getCards().contains(next)) {
                break;
            }
        }
        if (turn == Turn.PLAYER && p.getHand().getCards().size() < MAX_CARDS_IN_HAND) {
            p.getHand().addCard(next);
        }
    }


    /**
     * Gets the player's current saved deck and add to the active deck every card that is not already in the player's hand.
     * */
    /*private void loadPlayersDeck() {
        account.getDeck().getCards().stream()
                                       .filter((card) -> !p.getHand().getCards().contains(card))
                                       .forEach((card) -> p.getDeck().addCard(card));
    }*/

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
    }

}