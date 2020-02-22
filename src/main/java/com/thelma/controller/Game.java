package com.thelma.controller;

public interface Game {

    /**
     * Return whether this game is complete or not
     * @return true if it is complete, false if it is not
     */
    boolean isComplete();

    /**
     * Saves a chance and calculates the score
     * @param pinfalls the number of pin falls of the chance. Valid values: from 0 to 9, X, F
     * @throws Exception if the chance could not be saved
     */
    void saveChance(char pinfalls) throws Exception;

    /**
     * Gets the string representation of this game
     * @return returns the string representation of the entire game
     */
    String toString();
}