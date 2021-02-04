package controllers;

public interface Character {
    
    public int getHealth();

    public void setHealth(int health);
    
    public void addShield(int amount);

    public boolean isAlive();
    
    public void addCard(Card c);

    public void removeCard(int index);

}
