package com.thelma.controller;

public interface Game {
    boolean isComplete();
    void saveChance(char pinfalls) throws Exception;
    String toString();
}