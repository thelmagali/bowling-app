package com.thelma.controller.impl;

import com.thelma.controller.Frame;

public class RegularFrame extends Frame {

    @Override
    protected int getNumberOfChances() {
        return 2;
    }

    @Override
    protected int getMaxPins(){
        return 10 - getPreviousVal();
    }

    @Override
    protected void handleStrike(){
        balls[0] = '\0';
        currentIdx++;
    }

    @Override
    protected char formatIfSpare(){
        return '/';
    }

    @Override
    public boolean isComplete() {
        return currentIdx == 1;
    }

    @Override
    public String getBallsString(){
        return balls[0] + "\t" + balls[1];
    }
}