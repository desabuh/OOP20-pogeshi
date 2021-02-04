package controllers;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements Player {

    private int health;
    private int mana;
    private int unusedCombatMana;
    private int shield;
    public List<Card> cards;

    public PlayerImpl(final int health) {
        this.health = health;
        this.mana = 3;
        this.unusedCombatMana = this.mana;
        this.shield = 0;
        this.cards = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(final int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public void damagePlayer(int amount) {
        if (this.shield > amount) {
            this.shield -= amount;
        } else if (this.shield > 0) {
            amount -= this.shield;
            this.shield = 0;
        }
        this.health -= amount;
    }

    public void addShield(final int amount) {
        this.shield += amount;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void addCard(Card c) {
        cards.add(c);
    }

    public List<Card> getHand() {
        return cards;
    }

    public void removeCard(final int index) {
        cards.remove(index);
    }

    public int getUnusedCombatMana() {
        return unusedCombatMana;
    }

    public void setUnusedCombatMana(final int unusedCombatMana) {
        this.unusedCombatMana = unusedCombatMana;
    }

}
