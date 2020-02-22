package com.thelma.controller.impl;

import com.thelma.controller.RegularFrame;
import com.thelma.controller.common.FrameCommon;

public class RegularFrameImpl extends FrameCommon implements RegularFrame {

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
        chances[0] = '\0';
        currentIdx++;
    }

    @Override
    protected char handleSpare(){
        chancesToScore = 1;
        return '/';
    }

    @Override
    public boolean isComplete() {
        return currentIdx == 1;
    }

}