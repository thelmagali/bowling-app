package com.thelma.controller.impl;

import com.thelma.controller.Frame;
import com.thelma.controller.impl.common.FrameCommon;

public class LastFrame extends FrameCommon implements Frame {

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
    protected char handleSpare(){
        if(getPreviousVal() != 10){
            ballsToScore = 1;
            return '/';
        }
        ballsToScore = 2;
        return 'X';
    }
}
