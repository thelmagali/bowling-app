package com.thelma.controller;

import com.thelma.controller.impl.RegularFrameImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegularFrameTest {

    Frame getFrameInstance(){
        return new RegularFrameImpl();
    }

    @Test
    @DisplayName("Save chances with 5 and 4 pinfalls. Check sum and string repr")
    void testFrame1() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveChance('5');
        regularFrame.saveChance('4');
        Assertions.assertEquals(9, regularFrame.getFramePinfalls());
        Assertions.assertEquals("\t5\t4", regularFrame.getChancesString());
    }

    @Test
    @DisplayName("Save chances with 2 and 8 pinfalls. Check if spare was processed correctly")
    void testFrame2() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveChance('2');
        regularFrame.saveChance('8');
        Assertions.assertEquals(10, regularFrame.getFramePinfalls());
        Assertions.assertEquals("\t2\t/", regularFrame.getChancesString());
        Assertions.assertEquals(1, regularFrame.getChancesToScore());
    }

    @Test
    @DisplayName("Save chances with 0 and 10 pinfalls. Check if spare was processed correctly")
    void testFrame3() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveChance('0');
        regularFrame.saveChance('X');
        Assertions.assertEquals(10, regularFrame.getFramePinfalls());
        Assertions.assertEquals("\t0\t/", regularFrame.getChancesString());
        Assertions.assertEquals(1, regularFrame.getChancesToScore());
    }

    @Test
    @DisplayName("Save chances with F and 10 pinfalls. Check if spare was processed correctly")
    void testFrame4() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveChance('F');
        regularFrame.saveChance('X');
        Assertions.assertEquals(10, regularFrame.getFramePinfalls());
        Assertions.assertEquals("\tF\t/", regularFrame.getChancesString());
        Assertions.assertEquals(1, regularFrame.getChancesToScore());
    }

    @Test
    @DisplayName("Save chance with 10 pinfalls. Check if strike was processed correctly")
    void testFrame5() throws Exception {
        Frame regularFrame = getFrameInstance();
        regularFrame.saveChance('X');
        Assertions.assertEquals(10, regularFrame.getFramePinfalls());
        Assertions.assertEquals("\t\tX", regularFrame.getChancesString());
        Assertions.assertEquals(2, regularFrame.getChancesToScore());
    }

    @Test
    @DisplayName("Save chance with 4 and 7 pinfalls. An exception should be thrown")
    void testFrame6() {
        Frame regularFrame = getFrameInstance();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            regularFrame.saveChance('4');
            regularFrame.saveChance('7');
        });
        Assertions.assertEquals("Invalid second throw", thrown.getMessage());
    }

    @Test
    @DisplayName("Save chance with 10 and 10 pinfalls. An exception should be thrown")
    void testFrame7() {
        Frame regularFrame = getFrameInstance();
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
                regularFrame.saveChance('X');
                regularFrame.saveChance('X');
        });
        Assertions.assertEquals("Invalid second throw", thrown.getMessage());
    }

    @Test
    @DisplayName("Try to save 3 chances. An exception should be thrown")
    void testFrame8() {
        Frame regularFrame = getFrameInstance();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            regularFrame.saveChance('1');
            regularFrame.saveChance('2');
            regularFrame.saveChance('3');
        });
    }

    @Test
    @DisplayName("Try to save 3 chances, being the middle one a spare. An exception should be thrown")
    void testFrame9() throws Exception {
        Frame regularFrame = getFrameInstance();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            regularFrame.saveChance('4');
            regularFrame.saveChance('6');
            regularFrame.saveChance('3');
        });
    }
}
