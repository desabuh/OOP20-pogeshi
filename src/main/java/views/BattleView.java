package views;

import javafx.scene.image.ImageView;

public interface BattleView {
    void removeCard(int index);

    void addCard(ImageView img);

    void showEnemyDamage(int amount);

    void updateManaLabel(int unspent, int max);

    void updateEnemyStats(int health, int shield);

    void updatePlayerStats(int health, int shield);
}
