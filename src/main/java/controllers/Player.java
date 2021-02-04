package controllers;

import java.util.List;

public interface Player implements Character {

    public int getMana();

    public void setMana(int mana);

    public int getUnusedCombatMana();

    public void setUnusedCombatMana(int amount);

    public void damagePlayer(int amount);

    public List<Card> getHand();

    public void addCard(Card c);

}
