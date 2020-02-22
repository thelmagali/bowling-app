package com.thelma.controller.common;

public abstract class FrameCommon {
    protected Character[] chances;
    private int score;
    protected int chancesToScore;
    protected int currentIdx;
    private int frameVal;

    public FrameCommon(){
        chances = new Character[getNumberOfChances()];
        score = 0;
        chancesToScore = 0;
        currentIdx = -1;
    }

    protected abstract int getNumberOfChances();

    protected abstract int getMaxPins();

    public int getFramePinfalls(){
        return frameVal;
    }

    protected void handleStrike(){
        chancesToScore = 2;
    }

    protected abstract char handleSpare();

    public int saveChance(char pinfalls) throws Exception {
        currentIdx++;
        char formattedPitfall = pinfalls;
        int val = getVal(pinfalls);
        frameVal += val;
        if(currentIdx == 0){
            if(pinfalls == 'X'){
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
        chances[currentIdx] = formattedPitfall;
        return val;
    }

    public int getChancesToScore(){
        return chancesToScore;
    }

    protected int getPreviousVal() {
        return getVal(chances[currentIdx - 1]);
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
        StringBuilder sb = new StringBuilder();
        for(Character ball: chances){
            if(ball != null){
                sb.append("\t");
                if(ball != '\0'){
                    sb.append(ball);
                }
            }
        }
        return sb.toString();
    }
}
