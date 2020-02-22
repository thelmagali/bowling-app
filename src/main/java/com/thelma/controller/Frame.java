package com.thelma.controller;

public interface Frame {
    int getFramePinfalls();
    int saveChance(char pinfalls) throws Exception;
    int getChancesToScore();
    boolean isComplete();
    void score(int value);
    int getScore();
    String getChancesString();
}
