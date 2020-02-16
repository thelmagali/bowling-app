package com.thelma.controller.impl;

import com.thelma.controller.Frame;

import java.util.ArrayList;
import java.util.List;

public class LastFrame extends Frame {

    private List<Character> chances = new ArrayList<>();

    @Override
    protected Iterable<Character> getChances() {
        return chances;
    }

    @Override
    protected Character getChance(int index) {
        return chances.get(index);
    }

    @Override
    protected void setChance(int index, Character chance) throws Exception {
        if(index == 2){
            if(isComplete()){
                throw new Exception("Invalid throw. Frame is complete");
            }
            chances.add(chance);
        } else{
            chances.set(index, chance);
        }
    }

    @Override
    public boolean isComplete() {
        return (ballsToScore == 0) ? (chances.size() == 2) : (chances.size() == 3);
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
