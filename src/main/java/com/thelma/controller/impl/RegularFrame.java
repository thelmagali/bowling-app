package com.thelma.controller.impl;

import com.thelma.controller.Frame;

import java.util.Arrays;

public class RegularFrame extends Frame {
    private Character[] chances = new Character[2];

    @Override
    protected Iterable<Character> getChances() {
        return Arrays.asList(chances);
    }

    @Override
    protected Character getChance(int index) {
        return chances[index];
    }

    @Override
    protected void setChance(int index, Character chance) throws Exception {
        if(index > 1){
            throw new Exception("Invalid throw. Frame is complete");
        }
        chances[index] = chance;
    }

    @Override
    protected int getMaxPins(){
        return 10 - getPreviousVal();
    }

    @Override
    protected void handleStrike() throws Exception {
        super.handleStrike();
        setChance(0, '\0');
        currentIdx++;
    }

    @Override
    protected char handleSpare(){
        ballsToScore = 1;
        return '/';
    }

    @Override
    public boolean isComplete() {
        return currentIdx == 1;
    }

}