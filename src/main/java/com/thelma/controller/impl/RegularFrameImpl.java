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
        chancesToScore = 2;
        chances[0] = '\0';
        currentChanceIdx++;
    }

    @Override
    protected char handleSpareOrStrike(){
        chancesToScore = 1;
        return '/';
    }

    @Override
    public boolean isComplete() {
        return currentChanceIdx == 1;
    }

}