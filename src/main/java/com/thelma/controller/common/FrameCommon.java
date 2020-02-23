package com.thelma.controller.common;

import javafx.util.Pair;

import java.util.*;

public abstract class FrameCommon {
    protected Character[] chances; //array of chances inside this frame (up to 2 for regular frames and up to 3 for last frame)
    private int score;
    protected int chancesToScore; //number of forward chances needed to score this game
    protected int currentChanceIdx; //index of the current chance to be saved in this frame
    private int totalPinfalls; //total pin falls in this frame

    public FrameCommon(){
        chances = new Character[getNumberOfChances()];
        score = 0;
        chancesToScore = 0;
        currentChanceIdx = -1;
    }

    /**
     * Gets the maximum number of possible chances in this frame
     * @return 2 for regular frames and 3 for last frame
     */
    protected abstract int getNumberOfChances();

    /**
     * Gets the maximum possible number of pinfalls in the current chance
     * @return the remaining number pins prior to the chance
     */
    protected abstract int getMaxPins();

    /**
     * Calculates whether this chance was a spare of a strike
     * @return '/' for spare and 'X' for strike
     */
    protected abstract char handleSpareOrStrike();

    /**
     * Processes a strike. It depends on the implementation
     */
    protected abstract void handleStrike();

    public int getFramePinfalls(){
        return totalPinfalls;
    }

    public int saveChance(char pinfalls) throws Exception {
        currentChanceIdx++;
        char formattedPinfall = pinfalls;
        int val = getVal(pinfalls);
        totalPinfalls += val;
        if(currentChanceIdx == 0){
            if(pinfalls == 'X'){
                handleStrike();
            }
        } else{
            int maxPins = getMaxPins();
            if(val == maxPins){
                formattedPinfall = handleSpareOrStrike();
            } else if (val > maxPins){
                throw new Exception("Invalid second throw");
            }
        }
        chances[currentChanceIdx] = formattedPinfall;
        return val;
    }

    public int getChancesToScore(){
        return chancesToScore;
    }

    protected int getPreviousVal() {
        return getVal(chances[currentChanceIdx - 1]);
    }

    public void score(int value) {
        score = value;
    }

    public int getScore(){
        return score;
    }

    private int getVal(char pinfalls){
        if(pinfalls == 'F') return 0;
        if(pinfalls == '\0') return 0;
        if(pinfalls == 'X') return 10;
        return Character.getNumericValue(pinfalls);
    }

    public String getChancesString(){
        return Arrays.stream(chances)
                .filter(Objects::nonNull)
                .map(x -> new Pair<>("\t", (x != '\0' ? x.toString() : "")))
                .map(x -> new StringBuilder(x.getKey() + x.getValue()))
                .reduce(new StringBuilder(), StringBuilder::append)
                .toString();
    }
}
