package com.thelma.controller;

import com.thelma.controller.impl.LastFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LastFrameTest extends RegularFrameTest{

    Frame getFrameInstance(){
        return new LastFrame();
    }

    @Override
    @Test
    @DisplayName("Save chance with 10 pitfalls. Check if strike was processed correctly")
    void testFrame5() throws Exception {
        Frame frame = getFrameInstance();
        frame.saveBall('X');
        Assertions.assertEquals(10, frame.getFrameVal());
        Assertions.assertEquals("X", frame.getBallsString());
        Assertions.assertEquals(2, frame.getBallsToScore());
    }

    @Override
    @Test
    @DisplayName("Save chance with 10 and 10 pitfalls. No exception should be thrown")
    void testFrame7(){
        Frame frame = getFrameInstance();
        Assertions.assertDoesNotThrow(() -> {
            frame.saveBall('X');
            frame.saveBall('X');
        });
        Assertions.assertEquals(20, frame.getFrameVal());
        Assertions.assertEquals("X\tX", frame.getBallsString());
        Assertions.assertEquals(2, frame.getBallsToScore());
    }

    @Test
    @DisplayName("Try to save 3 chances. No exception should be thrown because this semantic is validated in the game, not in the frame")
    void testFrame8() {
        Frame regularFrame = getFrameInstance();
        Assertions.assertDoesNotThrow(() -> {
            regularFrame.saveBall('1');
            regularFrame.saveBall('2');
            regularFrame.saveBall('3');
        });
    }

    @Override
    @Test
    @DisplayName("Try to save 3 chances, being the middle one a spare. No exception should be thrown")
    void testFrame9() throws Exception {
        Frame frame = getFrameInstance();
        frame.saveBall('4');
        frame.saveBall('6');
        frame.saveBall('3');
        Assertions.assertEquals(13, frame.getFrameVal());
        Assertions.assertEquals("4\t/\t3", frame.getBallsString());
        Assertions.assertEquals(1, frame.getBallsToScore());
    }

    @Test
    @DisplayName("Save chance with 10, 10 and 10 pitfalls. No exception should be thrown")
    void testLastFrame1(){
        Frame frame = getFrameInstance();
        Assertions.assertDoesNotThrow(() -> {
            frame.saveBall('X');
            frame.saveBall('X');
            frame.saveBall('X');
        });
        Assertions.assertEquals(30, frame.getFrameVal());
        Assertions.assertEquals("X\tX\tX", frame.getBallsString());
        Assertions.assertEquals(2, frame.getBallsToScore());
    }
}
