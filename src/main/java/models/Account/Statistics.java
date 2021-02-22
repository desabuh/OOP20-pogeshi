package models.Account;

public interface Statistics {

    /**
     * Return the number of games won by the {@code Account}.
     * @return  Value of {@code wins}.
     */
    int getWins();

    /**
     * Return the number of games lost by the {@code Account}.
     * @return  Value of {@code loses}.
     */
    int getLoses();

    /**
     * Return the number of different cards unlocked by the {@code Account}.
     * @return  Value of {@code different cards unlocked}.
     */
    int getUnlockedCards();

    /**
     * Update the number of {@code wins} and depending on a {@code boolean} update the number of {@code different cards unlocked}.
     * <p>
     * if {@code duplicate card} == {@code true},
     * then {@code different cards unlocked} remain the same.<br>
     * if {@code duplicate card} == {@code false},
     * then {@code different cards unlocked} get updated.
     * </p>
     * @param duplicateCard {@code boolean} representing if the {@code Card} is a duplicate.
     */
    void updateOnWin(boolean duplicateCard);

    /**
     * Update the number of {@code loses}.
     */
    void updateOnLose();

}
