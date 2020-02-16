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
        super.handleStrike();
        balls[0] = '\0';
        currentIdx++;
    }

    @Override
    protected char handleSpare(){
        ballsToScore = 1;
        return '/';
    }

    @Override
    public boolean isComplete() {
        return currentIdx == 1;
    }

}