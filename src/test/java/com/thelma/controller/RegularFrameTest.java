package com.thelma.controller;

import com.thelma.controller.impl.RegularFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegularFrameTest {

    Frame getFrameInstance(){
        return new RegularFrame();
    }

    @Test
    @DisplayName("Save chances with 5 and 4 pitfalls. Check sum and string repr")
    void testFrame1() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveBall('5');
        regularFrame.saveBall('4');
        Assertions.assertEquals(9, regularFrame.getFrameVal());
        Assertions.assertEquals("5\t4", regularFrame.getBallsString());
    }

    @Test
    @DisplayName("Save chances with 2 and 8 pitfalls. Check if spare was processed correctly")
    void testFrame2() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveBall('2');
        regularFrame.saveBall('8');
        Assertions.assertEquals(10, regularFrame.getFrameVal());
        Assertions.assertEquals("2\t/", regularFrame.getBallsString());
        Assertions.assertEquals(1, regularFrame.getBallsToScore());
    }

    @Test
    @DisplayName("Save chances with 0 and 10 pitfalls. Check if spare was processed correctly")
    void testFrame3() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveBall('0');
        regularFrame.saveBall('X');
        Assertions.assertEquals(10, regularFrame.getFrameVal());
        Assertions.assertEquals("0\t/", regularFrame.getBallsString());
        Assertions.assertEquals(1, regularFrame.getBallsToScore());
    }

    @Test
    @DisplayName("Save chances with F and 10 pitfalls. Check if spare was processed correctly")
    void testFrame4() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveBall('F');
        regularFrame.saveBall('X');
        Assertions.assertEquals(10, regularFrame.getFrameVal());
        Assertions.assertEquals("F\t/", regularFrame.getBallsString());
        Assertions.assertEquals(1, regularFrame.getBallsToScore());
    }

    @Test
    @DisplayName("Save chance with 10 pitfalls. Check if strike was processed correctly")
    void testFrame5() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveBall('X');
        Assertions.assertEquals(10, regularFrame.getFrameVal());
        Assertions.assertEquals("\0\tX", regularFrame.getBallsString());
        Assertions.assertEquals(2, regularFrame.getBallsToScore());
    }

    @Test
    @DisplayName("Save chance with 4 and 7 pitfalls. An exception should be thrown")
    void testFrame6() {
        Frame regularFrame = getFrameInstance();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            regularFrame.saveBall('4');
            regularFrame.saveBall('7');
        });
        Assertions.assertEquals("Invalid second throw", thrown.getMessage());
    }

    @Test
    @DisplayName("Save chance with 10 and 10 pitfalls. An exception should be thrown")
    void testFrame7() {
        Frame regularFrame = getFrameInstance();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
                regularFrame.saveBall('X');
                regularFrame.saveBall('X');
        });
        Assertions.assertEquals("Invalid second throw", thrown.getMessage());
    }

    @Test
    @DisplayName("Try to save 3 chances. An exception should be thrown")
    void testFrame8() {
        Frame regularFrame = getFrameInstance();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            regularFrame.saveBall('1');
            regularFrame.saveBall('2');
            regularFrame.saveBall('3');
        });
        Assertions.assertEquals("Invalid throw. Frame is complete", thrown.getMessage());
    }

    @Test
    @DisplayName("Try to save 3 chances, being the middle one a spare. An exception should be thrown")
    void testFrame9() throws Exception {
        Frame regularFrame = getFrameInstance();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            regularFrame.saveBall('4');
            regularFrame.saveBall('6');
            regularFrame.saveBall('3');
        });
        Assertions.assertEquals("Invalid throw. Frame is complete", thrown.getMessage());
    }
}
