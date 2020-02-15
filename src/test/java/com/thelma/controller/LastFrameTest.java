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
        Assertions.assertEquals(frame.getFrameVal(), 10);
        Assertions.assertEquals("X", frame.getBallsString());
        Assertions.assertEquals(frame.getBallsToScore(), 2);
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
