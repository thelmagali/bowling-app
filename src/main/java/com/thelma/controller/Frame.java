package com.thelma.controller;

public interface Frame {

    /**
     * Gets the total of pin falls in all the chances within this frame
     * @return the number of pin falls in the whole frame
     */
    int getFramePinfalls();

    /**
     * Saves a chance and updates the amount of chances needed to score this frame.
     * Contains logic for saving: Strikes, spares and the other regular pin falls.
     *
     * @param pinfalls number of thrown pins in this chance. Possible values are (0 to 9, X, F)
     * @return the numeric value of the received pinfalls (10 for 'X', 0 for 'F', 1 for '1', etc)
     * @throws Exception when the chance cannot be saved for some reason
     */
    int saveChance(char pinfalls) throws Exception;

    /**
     * Obtains the necessary number of chances in order to calculate the score of this frame.
     * @return 2 if this frame contains a strike, 1 if this frame contains a spare and 0 for all the other cases
     */
    int getChancesToScore();

    /**
     * Returns whether this frame was already completed or not
     * @return true if it is complete, false if not
     */
    boolean isComplete();

    /**
     * Sets the score of this frame
     * @param value Score previously calculated in the game
     */
    void score(int value);

    /**
     * Gets the score of this frame
     * @return This frame's score
     */
    int getScore();

    /**
     * Gets string representation of the chances of this frame
     * @return A string containing every chance separated by a tabulation
     */
    String getChancesString();
}
