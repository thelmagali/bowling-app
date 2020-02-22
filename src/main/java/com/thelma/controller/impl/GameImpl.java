package com.thelma.controller.impl;

import com.thelma.controller.Frame;
import com.thelma.controller.Game;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class GameImpl implements Game {
    @Inject @Any
    private Instance<Frame> frameInstance;

    private Frame[] frames; //array of 10 frames
    private int currentFrameIdx; //index of the frame we are populating
    private int lastSavedScore;
    private int currentScore; //accumulated score in the game
    private int nextFrameToScoreIdx; //the first frame whose score was not saved yet
    private int ballsNotScored; //number of balls whose score was not saved yet
    private boolean complete; //if game is complete or not


    public GameImpl() {
        frames = new Frame[10];
        ballsNotScored = 0;
        nextFrameToScoreIdx = 0;
        currentScore = 0;
        complete = false;
        currentFrameIdx = 0;
        lastSavedScore = 0;
    }

    public boolean isComplete(){
        return complete;
    }

    private int getBallsToScoreNextFrame(){
        return frames[nextFrameToScoreIdx].getBallsToScore();
    }

    private RegularFrame getRegularFrame(){
        return frameInstance.select(RegularFrame.class).get();
    }

    private LastFrame getLastFrame(){
        return frameInstance.select(LastFrame.class).get();
    }

    public void saveBall(char pitfall) throws Exception {
        Frame frame = frames[currentFrameIdx];
        if (frame == null){
            if(currentFrameIdx == 9){
                frame = getLastFrame();
            } else {
                frame = getRegularFrame();
            }
            frames[currentFrameIdx] = frame;
        }
        currentScore += frame.saveBall(pitfall);
        if(getBallsToScoreNextFrame() > 0){
            ballsNotScored++;
            if (ballsNotScored > getBallsToScoreNextFrame()) {
                calculateScores();
                if(getBallsToScoreNextFrame() == 0){
                    ballsNotScored = 0;
                } else{
                    ballsNotScored--;
                }
            }
        }
        if(frame.isComplete()){
            if(currentFrameIdx == 9){
                complete = true;
                nextFrameToScoreIdx = 9;
            } else{
                currentFrameIdx++;
            }
            if(complete || getBallsToScoreNextFrame() == 0){
                calculateScores();
                ballsNotScored = 0;
            }
        }
    }

    private void calculateScores(){
        lastSavedScore += currentScore;
        frames[nextFrameToScoreIdx].score(lastSavedScore);
        currentScore -= frames[nextFrameToScoreIdx].getFrameVal();
        if(nextFrameToScoreIdx < currentFrameIdx){
            nextFrameToScoreIdx++;
        }

    }

    @Override
    public String toString() {
        StringBuilder ballsBuilder = new StringBuilder("Pinfalls");
        StringBuilder scoreBuilder = new StringBuilder("Score");
        for(int i = 0; i <= currentFrameIdx; i++){
            if(frames[i] != null){
                ballsBuilder.append(frames[i].getBallsString());
                scoreBuilder.append("\t\t").append(frames[i].getScore());
            }
        }
        return ballsBuilder.append('\n').append(scoreBuilder).toString();
    }
}