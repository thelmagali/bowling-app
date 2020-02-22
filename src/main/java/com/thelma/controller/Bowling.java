package com.thelma.controller;

public interface Bowling {

    /**
     * Plays a whole multi player Bowling game reading the data from the provided reader
     * @param reader implementation of GameInputReader
     * @throws Exception when there is an error in the reading or in the actual bowling game
     */
    void play(GameInputReader reader) throws Exception;

    /**
     * Prints the result of this Bowling game
     */
    void printResult();
}
