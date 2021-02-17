package models;

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
    private Player p;
    private EnemyImp e;
    private Account account = new AccountImp();

    public BattleImpl(final Player p, final EnemyImp e) {
        this.p = p;
        this.e = e;
    }

    public BattleImpl() {
        p = new PlayerImp(new DeckImpl());
        e = new EnemyImp(new DeckImpl(), new Point2DImp(0, 0));
    }

    @Override
    public void endTurn() {
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
        if (hasBeenInitialized) {
            throw new IllegalStateException("Both opponents of the battle have already been initialized.");
        }
        loadPlayersDeck();
        //e.getDeck().addCard(new CardImpl.Builder().attack(10).shield(0).cost(1).description("").resourcePath("res" + File.separator + "images" + File.separator + "AceOfHearts.png").name("Sample enemy card").build());
        loadEnemysDeck();
        for (int i = 0; i < BASE_STARTING_CARDS; i++) {
            drawCard();
        }
        playerUnusedCombatMana = p.getMana();
        hasBeenInitialized = true;
    }

    public boolean playCard(final int index) {
        if (!hasBeenInitialized) {
            throw new IllegalStateException("The opponents have not been initialized.");
        }
        Card played;
        if (turn == Turn.PLAYER && isPlayable(p.getHand().getCards().get(index))) {
            if (turn == Turn.PLAYER) {
                played = p.getHand().getCards().get(index);
                System.out.println("Player!");
                setPlayerUnusedCombatMana((getPlayerUnusedCombatMana() - played.getCost()));
                if (played.getShield() > 0) {
                    p.setShield(p.getShield() + played.getShield());
                }
                inflictDamage(e, played);
                p.getHand().removeCard(index);
            } else {
                Optional<Card> next = e.getDeck().popCard();
                if (next.isPresent()) {
                    played = next.get();
                    if (played.getShield() > 0) {
                        e.setShield(e.getShield() + played.getShield());
                    }
                    inflictDamage(p, played);
                } else {
                    // TODO: regenerate deck
                    loadEnemysDeck();
                }
                System.out.println("Enemy!");
            }
            return true;
        } else {
            return false;
        }
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
            account.lose();
            return true;
        } else if (e.getHealth() <= 0) {
            account.win();
            return true;
        }
        return false;
        //return p.getHealth() <= 0 || e.getHealth() <= 0;
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

    private void inflictDamage(final Character target, final Card c) {
        int damage = c.getAttack();
        if (target.getShield() > damage) {
            target.setShield(target.getShield() - damage);
            damage = 0;
        } else if (target.getShield() > 0) {
            damage -= target.getShield();
            target.setShield(0);
        }
        target.setHealth(target.getHealth() - damage);
    }

    private void drawCard() {
        if (turn == Turn.PLAYER && p.getHand().getCards().size() <= MAX_CARDS_IN_HAND) {
            Optional<Card> next = p.getDeck().popCard();
            if (next.isPresent()) {
                p.getHand().addCard(next.get());
            } else {
                loadPlayersDeck();
                /**
                 * Since the maximum amount of cards in the player's hand is less than the amount of total cards in the deck,
                 * after a deck refill there will always be at least 1 card in the deck.
                 * */
                p.getHand().addCard(p.getDeck().popCard().get());
            }
        }
    }

    /**
     * Gets the player's current saved deck and add to the active deck every card that is not already in the player's hand.
     * */
    private void loadPlayersDeck() {
        account.getDeck().getCards().stream()
                                       .filter((card) -> !p.getHand().getCards().contains(card))
                                       .forEach((card) -> p.getDeck().addCard(card));
    }

    /**
     * Fills the enemy's deck with the default deck's cards.
     * */
    private void loadEnemysDeck() {
        new AccountImp().getDeck().getCards().stream()
                                                .forEach((card) -> e.getDeck().addCard(card));
    }


}