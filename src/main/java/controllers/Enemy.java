package controllers;

import java.util.List;

public interface Enemy extends Character {

    public void damageEnemy(int amount);

    public List<Card> getHand();

}
