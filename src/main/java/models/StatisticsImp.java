package models;

public final class StatisticsImp implements Statistics {

    private int wins;
    private int loses;
    private int differentCardsUnlocked;

    public StatisticsImp() {
        //TODO: IF (file exist); THEN {load Statistics}; ELSE {create new file and generate default Statistics};
    }

    @Override
    public int getWins() {
        return this.wins;
    }

    @Override
    public int getLoses() {
        return this.loses;
    }

    @Override
    public int getUnlockedCards() {
        return this.differentCardsUnlocked;
    }

    @Override
    public void updateOnWin(final boolean duplicateCard) {
        this.wins += 1;
        if (!duplicateCard) {
            this.differentCardsUnlocked += 1;
        }
    }

    @Override
    public void updateOnLose() {
        this.loses += 1;
    }

}
