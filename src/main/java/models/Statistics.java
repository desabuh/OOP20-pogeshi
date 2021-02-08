package models;

public interface Statistics {

    int getWins();

    int getLoses();

    int getUnlockedCards();

    void updateOnWin(boolean duplicateCard);

    void updateOnLose();

}
