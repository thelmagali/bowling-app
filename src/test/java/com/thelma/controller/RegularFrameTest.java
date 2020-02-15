package com.thelma.controller;

import com.thelma.controller.impl.RegularFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegularFrameTest {

    @Test
    @DisplayName("Save chances with 5 and 4 pitfalls. Check sum and string repr")
    void testRegFrame1() throws Exception {
        Frame regularFrame = new RegularFrame();
        regularFrame.saveBall('5');
        regularFrame.saveBall('4');
        Assertions.assertEquals(regularFrame.getFrameVal(), 9);
        Assertions.assertEquals("5\t4", regularFrame.getBallsString());
    }

    @Test
    @DisplayName("Save chances with 2 and 8 pitfalls. Check if spare was processed correctly")
    void testRegFrame2() throws Exception {
        Frame regularFrame = new RegularFrame();
        regularFrame.saveBall('2');
        regularFrame.saveBall('8');
        Assertions.assertEquals(regularFrame.getFrameVal(), 10);
        Assertions.assertEquals("2\t/", regularFrame.getBallsString());
        Assertions.assertEquals(regularFrame.getBallsToScore(), 1);
    }

    @Test
    @DisplayName("Save chances with 0 and 10 pitfalls. Check if spare was processed correctly")
    void testRegFrame3() throws Exception {
        Frame regularFrame = new RegularFrame();
        regularFrame.saveBall('0');
        regularFrame.saveBall('X');
        Assertions.assertEquals(regularFrame.getFrameVal(), 10);
        Assertions.assertEquals("0\t/", regularFrame.getBallsString());
        Assertions.assertEquals(regularFrame.getBallsToScore(), 1);
    }

    @Test
    @DisplayName("Save chances with F and 10 pitfalls. Check if spare was processed correctly")
    void testRegFrame4() throws Exception {
        Frame regularFrame = new RegularFrame();
        regularFrame.saveBall('F');
        regularFrame.saveBall('X');
        Assertions.assertEquals(regularFrame.getFrameVal(), 10);
        Assertions.assertEquals("F\t/", regularFrame.getBallsString());
        Assertions.assertEquals(regularFrame.getBallsToScore(), 1);
    }

    @Test
    @DisplayName("Save chance with 10 pitfalls. Check if strike was processed correctly")
    void testRegFrame5() throws Exception {
        Frame regularFrame = new RegularFrame();
        regularFrame.saveBall('X');
        Assertions.assertEquals(regularFrame.getFrameVal(), 10);
        Assertions.assertEquals("\0\tX", regularFrame.getBallsString());
        Assertions.assertEquals(regularFrame.getBallsToScore(), 2);
    }

    @Test
    @DisplayName("Save chance with 4 and 7 pitfalls. An exception should be thrown")
    void testRegFrame6() {
        Frame regularFrame = new RegularFrame();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            regularFrame.saveBall('4');
            regularFrame.saveBall('7');
        });
        Assertions.assertEquals("Invalid second throw", thrown.getMessage());
    }

    @Test
    @DisplayName("Save chance with 10 and 10 pitfalls. An exception should be thrown")
    void testRegFrame7() {
        Frame regularFrame = new RegularFrame();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
                regularFrame.saveBall('X');
                regularFrame.saveBall('X');
        });
        Assertions.assertEquals("Invalid second throw", thrown.getMessage());
    }
}
