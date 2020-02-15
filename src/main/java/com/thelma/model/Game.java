package com.thelma.model;


class Game {
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

    boolean isComplete(){
        return complete;
    }

    private int getBallsToScoreNextFrame(){
        if (nextFrameToScoreIdx == 10){
            complete = true;
            nextFrameToScoreIdx = 9;
            return 0;
        }
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
            if (ballsNotScored >= getBallsToScoreNextFrame()) {
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

    void print() {
        StringBuilder ballsBuilder = new StringBuilder("Pinfalls\t");
        StringBuilder scoreBuilder = new StringBuilder("Score\t\t");
        for(int i = 0; i < currentFrameIdx; i++){
            ballsBuilder.append(frames[i].getBallsString()).append("\t");
            scoreBuilder.append(frames[i].getScore()).append("\t\t");
        }
        if(currentFrameIdx > 0){
            ballsBuilder.deleteCharAt(ballsBuilder.length() - 1);
            scoreBuilder.substring(0, scoreBuilder.length() - 2);
        }
        System.out.println(ballsBuilder.toString());
        System.out.println(scoreBuilder.toString());
    }
}