package models.Account;

public final class StatisticsImp implements Statistics {

    private int wins;
    private int loses;
    private int differentCardsUnlocked;

    /**
     * Constructor with default parameters.
     * <p>
     * <ul>
     * <li>{@code wins} = 0</li>
     * <li>{@code loses} = 0</li>
     * <li>{@code differemtCardsUnlocked} = 10</li>
     * </ul>
     * <p>
     */
    public StatisticsImp() {
        this.wins = 0;
        this.loses = 0;
        this.differentCardsUnlocked = 10;
    }

    /**
     * Constructor with custom parameters.
     * @param wins                      The {@code wins} of the {@code Account}.
     * @param loses                     The {@code loses} of the {@code Account}.
     * @param differentCardsUnlocked    The {@code differentCardsUnlocked} by the {@code Account}.
     */
    public StatisticsImp(final int wins, final int loses, final int differentCardsUnlocked) {
        this.wins = wins;
        this.loses = loses;
        this.differentCardsUnlocked = differentCardsUnlocked;
    }

    /**
     * Return the value of {@code wins}.
     */
    @Override
    public int getWins() {
        return this.wins;
    }

    /**
     * Return the value of {@code loses}.
     */
    @Override
    public int getLoses() {
        return this.loses;
    }

    /**
     * Return the value of {@code differentCardsUnlocked}.
     */
    @Override
    public int getUnlockedCards() {
        return this.differentCardsUnlocked;
    }

    /**
     * Increment the number of {@code wins}, and
     * if {@code duplicateCard} == {@code true} then Increment the number of {@code differentCardsUnlocked},
     * otherwise if {@code duplicateCard} == {@code false} do nothing.
     */
    @Override
    public void updateOnWin(final boolean duplicateCard) {
        this.wins += 1;
        if (!duplicateCard) {
            this.differentCardsUnlocked += 1;
        }
    }

    /**
     * Increment the number of {@code loses}.
     */
    @Override
    public void updateOnLose() {
        this.loses += 1;
    }

}
