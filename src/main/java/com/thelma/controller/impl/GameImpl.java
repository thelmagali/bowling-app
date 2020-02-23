package com.thelma.controller.impl;

import com.thelma.controller.Frame;
import com.thelma.controller.Game;
import com.thelma.controller.LastFrame;
import com.thelma.controller.RegularFrame;
import javafx.util.Pair;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.*;

public class GameImpl implements Game {
    @Inject
    private Instance<RegularFrame> regFrameProvider;

    @Inject
    private Instance<LastFrame> lastFrameProvider;

    private Frame[] frames = new Frame[10]; //array of 10 frames
    private int currentFrameIdx = 0; //index of the frame we are populating
    private int lastSavedScore = 0;
    private int currentScore = 0; //accumulated score in the game
    private int nextFrameToScoreIdx = 0; //the first frame whose score was not saved yet
    private int chancesNotScored = 0; //number of chances whose score was not saved yet
    private boolean complete = false; //if game is complete or not

    public boolean isComplete() {
        return complete;
    }

    private int getChancesToScoreNext() {
        return frames[nextFrameToScoreIdx].getChancesToScore();
    }

    public void saveChance(char pinfalls) throws Exception {
        Frame frame = frames[currentFrameIdx];
        if (frame == null) {
            if (currentFrameIdx != 9) {
                frame = regFrameProvider.get();
            } else {
                frame = lastFrameProvider.get();
            }
            frames[currentFrameIdx] = frame;
        }
        currentScore += frame.saveChance(pinfalls);
        if (getChancesToScoreNext() > 0) {
            chancesNotScored++;
            if (chancesNotScored > getChancesToScoreNext()) {
                calculateScores();
                if (getChancesToScoreNext() == 0) {
                    chancesNotScored = 0;
                } else {
                    chancesNotScored--;
                }
            }
        }
        if (frame.isComplete()) {
            if (currentFrameIdx == 9) {
                complete = true;
                nextFrameToScoreIdx = 9;
            } else {
                currentFrameIdx++;
            }
            if (complete || getChancesToScoreNext() == 0) {
                calculateScores();
                chancesNotScored = 0;
            }
        }
    }

    /*
        1. Adds the current score to lastSavedScore
        2. Scores the nextFrameToScore with the value of lastSavedScore
        3. Substracts the pinfalls of the just-scored frame from the current score
        4. Increments nextFrameToScore
     */
    private void calculateScores() {
        lastSavedScore += currentScore;
        frames[nextFrameToScoreIdx].score(lastSavedScore);
        currentScore -= frames[nextFrameToScoreIdx].getFramePinfalls();
        if (nextFrameToScoreIdx < currentFrameIdx) {
            nextFrameToScoreIdx++;
        }
    }

    @Override
    public String toString() {
        Pair<StringBuilder, StringBuilder> pair = Arrays.stream(frames)
                .filter(Objects::nonNull)
                .map(x -> new Pair<>(new StringBuilder(x.getChancesString()), new StringBuilder("\t\t" + x.getScore())))
                .reduce(new Pair<>(new StringBuilder("Pinfalls"), new StringBuilder("Score")), (acc, x) -> {
                    acc.getKey().append(x.getKey());
                    acc.getValue().append(x.getValue());
                    return acc;
                });
        return pair.getKey().append("\n").append(pair.getValue()).toString();
    }
}