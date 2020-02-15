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
        return (ballsToScore == 0) ? (currentIdx == 1) : (currentIdx == 2);
    }

    @Override
    int getMaxPins(){
        return (getPreviousVal() == 10) ? 10 : 10 - getPreviousVal();
    }

    @Override
    String getBallsString(){
        StringBuilder sb = new StringBuilder();
        sb.append(balls[0]).append("\t").append(balls[1]).append("\t");
        if(balls[2] != null) sb.append(balls[2]);
        return sb.toString();
    }

    @Override
    void handleStrike(){ }

    @Override
    char formatIfSpare(){
        if(getPreviousVal() != 10) return '/';
        return 'X';
    }
}
