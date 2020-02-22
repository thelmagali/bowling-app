package com.thelma.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

class LastFrameTest extends RegularFrameTest{

    @Inject
    private LastFrame lastFrame;

    Frame getFrameInstance(){
        return lastFrame;
    }

    @Override
    @Test
    @DisplayName("Save chance with 10 pinfall. Check if strike was processed correctly")
    void testFrame5() throws Exception {
        Frame frame = getFrameInstance();
        frame.saveChance('X');
        Assertions.assertEquals(10, frame.getFramePinfalls());
        Assertions.assertEquals("\tX", frame.getChancesString());
        Assertions.assertEquals(2, frame.getChancesToScore());
    }

    @Override
    @Test
    @DisplayName("Save chance with 10 and 10 pinfall. No exception should be thrown")
    void testFrame7(){
        Frame frame = getFrameInstance();
        Assertions.assertDoesNotThrow(() -> {
            frame.saveChance('X');
            frame.saveChance('X');
        });
        Assertions.assertEquals(20, frame.getFramePinfalls());
        Assertions.assertEquals("\tX\tX", frame.getChancesString());
        Assertions.assertEquals(2, frame.getChancesToScore());
    }

    @Test
    @DisplayName("Try to save 3 chances. No exception should be thrown because this semantic is validated in the game, not in the frame")
    void testFrame8() {
        Frame regularFrame = getFrameInstance();
        Assertions.assertDoesNotThrow(() -> {
            regularFrame.saveChance('1');
            regularFrame.saveChance('2');
            regularFrame.saveChance('3');
        });
    }

    @Override
    @Test
    @DisplayName("Save 3 chances, being the middle one a spare. No exception should be thrown")
    void testFrame9() throws Exception {
        Frame frame = getFrameInstance();
        frame.saveChance('4');
        frame.saveChance('6');
        frame.saveChance('3');
        Assertions.assertEquals(13, frame.getFramePinfalls());
        Assertions.assertEquals("\t4\t/\t3", frame.getChancesString());
        Assertions.assertEquals(1, frame.getChancesToScore());
    }

    @Test
    @DisplayName("Save chance with 10, 10 and 10 pinfall. No exception should be thrown")
    void testLastFrame1(){
        Frame frame = getFrameInstance();
        Assertions.assertDoesNotThrow(() -> {
            frame.saveChance('X');
            frame.saveChance('X');
            frame.saveChance('X');
        });
        Assertions.assertEquals(30, frame.getFramePinfalls());
        Assertions.assertEquals("\tX\tX\tX", frame.getChancesString());
        Assertions.assertEquals(2, frame.getChancesToScore());
    }
}
