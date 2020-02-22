package com.thelma.controller;

import com.thelma.model.Chance;

public interface Bowling {
    void play(GameInputReader reader) throws Exception;
    void printResult();
    void playChance(Chance chance) throws Exception;
}
