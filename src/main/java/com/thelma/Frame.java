package com.thelma;

class Frame {
    protected Character[] balls;
    protected int score;
    protected int fwdBallsToScore;
    protected int currentIdx;
    protected int frameVal;

    Frame(){
        balls = new Character[2];
        score = 0;
        fwdBallsToScore = 0;
        currentIdx = -1;
    }

    protected int getMaxPins(){
        return 10;
    }

    public int getFrameVal(){
        return frameVal;
    }

    void saveBall(char pitfall) throws Exception {
        currentIdx++;
        frameVal += getVal(pitfall);
        if(currentIdx == 0){
            if(pitfall == 'X'){
                balls[0] = ' ';
                fwdBallsToScore = 2;
                currentIdx++;
            }
        } else{
            int maxPins = getMaxPins();
            if(frameVal == maxPins){
                pitfall = '/';
                fwdBallsToScore = 1;
            } else if (frameVal > maxPins){
                throw new Exception("Invalid second throw");
            }
        }
        balls[currentIdx] = pitfall;
    }

    int getFwdBallsToScore(){
        return fwdBallsToScore;
    }

    boolean isComplete() {
        return currentIdx == 1;
    }

    int getPreviousVal() {
        return getVal(balls[currentIdx - 1]);
    }

    void score(int value) {
        score = value;
    }

    int getScore(){
        return score;
    }

    private int getVal(char pitfalls){
        if(pitfalls == 'F') return 0;
        if(pitfalls == ' ') return 0;
        if(pitfalls == 'X') return 10;
        return Character.getNumericValue(pitfalls);
    }
}