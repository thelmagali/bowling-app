package com.thelma.model;

class Frame {
    Character[] balls;
    int score;
    int ballsToScore;
    int currentIdx;
    private int frameVal;

    Frame(){
        balls = new Character[2];
        score = 0;
        ballsToScore = 0;
        currentIdx = -1;
    }

    int getMaxPins(){
        return 10 - getPreviousVal();
    }

    int getFrameVal(){
        return frameVal;
    }

    void handleStrike(){
        balls[0] = ' ';
        currentIdx++;
    }

    char formatIfSpare(){
        return '/';
    }

    int saveBall(char pitfall) throws Exception {
        currentIdx++;
        char formattedPitfall = pitfall;
        int val = getVal(pitfall);
        frameVal += val;
        if(currentIdx == 0){
            if(pitfall == 'X'){
                handleStrike();
                ballsToScore = 2;
            }
        } else{
            int maxPins = getMaxPins();
            if(val == maxPins){
                formattedPitfall = formatIfSpare();
                ballsToScore = 1;
            } else if (val > maxPins){
                throw new Exception("Invalid second throw");
            }
        }
        balls[currentIdx] = formattedPitfall;
        return val;
    }

    int getBallsToScore(){
        return ballsToScore;
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

    String getBallsString(){
        StringBuilder sb = new StringBuilder();
        if(balls[0] != ' ') sb.append(balls[0]);
        sb.append("\t").append(balls[1]);
        return sb.toString();
    }
}