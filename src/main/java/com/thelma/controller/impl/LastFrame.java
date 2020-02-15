package com.thelma.controller.impl;

import com.thelma.controller.Frame;

public class LastFrame extends Frame {

    @Override
    protected int getNumberOfChances() {
        return 3;
    }

    @Override
    public boolean isComplete() {
        return (ballsToScore == 0) ? (currentIdx == 1) : (currentIdx == 2);
    }

    @Override
    protected int getMaxPins(){
        return (getPreviousVal() == 10) ? 10 : 10 - getPreviousVal();
    }

    @Override
    public String getBallsString(){
        StringBuilder sb = new StringBuilder();
        sb.append(balls[0]).append("\t").append(balls[1]).append("\t");
        if(balls[2] != null) sb.append(balls[2]);
        return sb.toString();
    }

    @Override
    protected char formatIfSpare(){
        if(getPreviousVal() != 10) return '/';
        return 'X';
    }
}
