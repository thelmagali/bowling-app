package com.thelma.model;


public class Game {
    private Frame[] frames; //array of 10 frames
    private int currentFrameIdx; //index of the frame we are populating
    private int lastSavedScore;
    private int currentScore; //accumulated score in the game
    private int nextFrameToScoreIdx; //the first frame whose score was not saved yet
    private int ballsNotScored; //number of balls whose score was not saved yet
    private boolean complete; //if game is complete or not

    Game() {
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

    void saveBall(char pitfall) throws Exception {
        Frame frame = frames[currentFrameIdx];
        if (frame == null){
            if(currentFrameIdx == 9){
                frame = new LastFrame();
            } else {
                frame = new Frame();
            }
            frames[currentFrameIdx] = frame;
        }
        currentScore += frame.saveBall(pitfall);
        if(getBallsToScoreNextFrame() > 0){
            if (ballsNotScored == getBallsToScoreNextFrame()) {
                calculateScores();
            } else {
                ballsNotScored++;
            }
        }
        if (frame.isComplete()) {
            currentFrameIdx++;
            complete = (currentFrameIdx == 10);
            if(getBallsToScoreNextFrame() == 0 || complete){
                calculateScores();
                ballsNotScored = 0;
            }
        }
    }

    private void calculateScores(){
        lastSavedScore += currentScore;
        frames[nextFrameToScoreIdx].score(lastSavedScore);
        currentScore -= frames[nextFrameToScoreIdx].getFrameVal();
        nextFrameToScoreIdx++;
    }
}