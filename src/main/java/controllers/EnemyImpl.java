package controllers;

import java.util.ArrayList;
import java.util.List;

public class EnemyImpl implements Enemy {

    private int health;
    private int shield;
    public List<Card> cards;

    public EnemyImpl(final int health) {
        this.health = health;
        this.shield = 0;
        this.cards = new ArrayList<>();
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(final int health) {
        this.health = health;

    }

    @Override
    public void damageEnemy(int amount) {
        if (this.shield > amount) {
            this.shield -= amount;
        } else if (this.shield > 0) {
            amount -= this.shield;
            this.shield = 0;
        }
        this.health -= amount;
    }

    @Override
    public void addShield(final int amount) {
        this.shield += amount;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public List<Card> getHand() {
        return cards;
    }

    @Override
    public void addCard(final Card c) {
        cards.add(c);

    }

    @Override
    public void removeCard(final int index) {
        cards.remove(index);
    }

}
