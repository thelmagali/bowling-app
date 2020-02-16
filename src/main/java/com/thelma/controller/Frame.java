package com.thelma.controller;

public abstract class Frame {

    private int score;
    protected int ballsToScore;
    protected int currentIdx;
    private int frameVal;

    public Frame(){
        score = 0;
        ballsToScore = 0;
        currentIdx = -1;
    }

    protected abstract Iterable<Character> getChances();

    protected abstract Character getChance(int index);

    protected abstract void setChance(int index, Character chance) throws Exception;

    protected abstract int getMaxPins();

    int getFrameVal(){
        return frameVal;
    }

    protected void handleStrike() throws Exception {
        ballsToScore = 2;
    }

    protected abstract char handleSpare();

    int saveBall(char pitfall) throws Exception {
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
        setChance(currentIdx, formattedPitfall);
        return val;
    }

    int getBallsToScore(){
        return ballsToScore;
    }

    public abstract boolean isComplete();

    protected int getPreviousVal() {
        return getVal(getChance(currentIdx - 1));
    }

    void score(int value) {
        score = value;
    }

    int getScore(){
        return score;
    }

    private int getVal(char pitfalls){
        if(pitfalls == 'F') return 0;
        if(pitfalls == '\0') return 0;
        if(pitfalls == 'X') return 10;
        return Character.getNumericValue(pitfalls);
    }

    String getBallsString(){
        StringBuilder sb = new StringBuilder();
        for(Character ball: getChances()){
            if(ball != null) sb.append(ball).append("\t");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
