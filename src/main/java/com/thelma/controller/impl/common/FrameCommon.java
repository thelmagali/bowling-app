package com.thelma.controller.impl.common;

public abstract class FrameCommon {
    protected Character[] balls;
    private int score;
    protected int ballsToScore;
    protected int currentIdx;
    private int frameVal;

    public FrameCommon(){
        balls = new Character[getNumberOfChances()];
        score = 0;
        ballsToScore = 0;
        currentIdx = -1;
    }

    protected abstract int getNumberOfChances();

    protected abstract int getMaxPins();

    public int getFrameVal(){
        return frameVal;
    }

    protected void handleStrike(){
        ballsToScore = 2;
    }

    protected abstract char handleSpare();

    public int saveBall(char pitfall) throws Exception {
        currentIdx++;
        char formattedPitfall = pitfall;
        int val = getVal(pitfall);
        frameVal += val;
        if(currentIdx == 0){
            if(pitfall == 'X'){
                handleStrike();
            }
        } else{
            int maxPins = getMaxPins();
            if(val == maxPins){
                formattedPitfall = handleSpare();
            } else if (val > maxPins){
                throw new Exception("Invalid second throw");
            }
        }
        balls[currentIdx] = formattedPitfall;
        return val;
    }

    public int getBallsToScore(){
        return ballsToScore;
    }

    protected int getPreviousVal() {
        return getVal(balls[currentIdx - 1]);
    }

    public void score(int value) {
        score = value;
    }

    public int getScore(){
        return score;
    }

    private int getVal(char pitfalls){
        if(pitfalls == 'F') return 0;
        if(pitfalls == '\0') return 0;
        if(pitfalls == 'X') return 10;
        return Character.getNumericValue(pitfalls);
    }

    public String getBallsString(){
        StringBuilder sb = new StringBuilder();
        for(Character ball: balls){
            if(ball != null){
                sb.append("\t");
                if(ball != '\0'){
                    sb.append(ball);
                }
            }
        }
        return sb.toString();
    }
}
