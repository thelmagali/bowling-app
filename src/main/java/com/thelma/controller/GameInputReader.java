package com.thelma.controller;

import com.thelma.model.Chance;

public interface GameInputReader {

    /**
     * Reads a chance from any input
     * @return a Chance object containing the name of the person and the number of pin falls
     * @throws Exception if the input reading failed for some reason
     */
    Chance readChance() throws Exception;
}
