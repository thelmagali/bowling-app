package com.thelma.controller;

public interface Frame {
    int getFrameVal();
    int saveBall(char pitfall) throws Exception;
    int getBallsToScore();
    boolean isComplete();
    void score(int value);
    int getScore();
    String getBallsString();
}
