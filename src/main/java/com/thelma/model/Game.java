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
        frame.saveBall(pitfall);
        currentScore += frame.getFrameVal();
        if(frames[nextFrameToScoreIdx].getFwdBallsToScore() > 0){
            if (ballsNotScored == frames[nextFrameToScoreIdx].getFwdBallsToScore()) {
                lastSavedScore += currentScore;
                currentScore -= frames[nextFrameToScoreIdx].getFrameVal();
                frames[nextFrameToScoreIdx].score(lastSavedScore);
                nextFrameToScoreIdx++;
            } else {
                ballsNotScored++;
            }
        }
        if (frame.isComplete()) {
            currentFrameIdx++;
            if(currentFrameIdx == 10){
                complete = true;
            }
            if(complete || frames[nextFrameToScoreIdx].getFwdBallsToScore() == 0){
                lastSavedScore += currentScore;
                currentScore -= frames[nextFrameToScoreIdx].getFrameVal();
                frames[nextFrameToScoreIdx].score(lastSavedScore);
                nextFrameToScoreIdx++;
            }
        }
    }
}