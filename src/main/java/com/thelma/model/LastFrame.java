package com.thelma.model;

class LastFrame extends Frame {

    LastFrame(){
        balls = new Character[3];
        score = 0;
        ballsToScore = 0;
        currentIdx = -1;
    }

    @Override
    boolean isComplete() {
        if(ballsToScore == 0){
            return currentIdx == 1;
        }
        return currentIdx == 2;
    }

    @Override
    protected int getMaxPins(){
        return (getPreviousVal() < 10) ? 10 : 20;
    }
}
