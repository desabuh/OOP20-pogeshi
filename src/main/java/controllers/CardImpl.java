package controllers;

public class CardImpl implements Card {

    private String name;
    private int cost;
    private int damage;
    private int shield;
    
    public CardImpl(String name, int cost, int damage, int shield) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.shield = shield;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public int getShield() {
        return shield;
    }
    
}
