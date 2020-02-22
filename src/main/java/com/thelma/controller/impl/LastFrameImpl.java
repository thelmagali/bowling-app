package com.thelma.controller.impl;

import com.thelma.controller.LastFrame;
import com.thelma.controller.common.FrameCommon;

public class LastFrameImpl extends FrameCommon implements LastFrame {

    @Override
    protected int getNumberOfChances() {
        return 3;
    }

    @Override
    public boolean isComplete() {
        return (chancesToScore == 0) ? (currentIdx == 1) : (currentIdx == 2);
    }

    @Override
    protected int getMaxPins(){
        return (getPreviousVal() == 10) ? 10 : 10 - getPreviousVal();
    }

    @Override
    protected char handleSpare(){
        if(getPreviousVal() != 10){
            chancesToScore = 1;
            return '/';
        }
        chancesToScore = 2;
        return 'X';
    }
}
