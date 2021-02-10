package views;

public interface BattleView {
    public void removeCard(int index);

    public void showEnemyDamage(int amount);

    public void updateManaLabel(int unspent, int max);
}
