package com.thelma.controller;

import com.thelma.model.Chance;

public interface GameInputReader {
    Chance readChance() throws Exception;
}
