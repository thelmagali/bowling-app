package com.thelma.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    
    private final String PINFALLS = "Pinfalls\t";
    private final String SCORE = "\nScore\t\t";

    @Test
    @DisplayName("Should score instantly a frame without strike or spare")
    void scoreNormally() throws Exception {
        Game game = new Game();
        game.saveBall('3');
        game.saveBall('6');
        String expectedString = PINFALLS+"3\t6"+SCORE+"9";
        Assertions.assertEquals(expectedString, game.toString());
    }

    @Test
    @DisplayName("Should not score instantly a frame with strike")
    void scoreX1() throws Exception {
        Game game = new Game();
        game.saveBall('X');
        String expectedString = PINFALLS+"\0\tX"+SCORE+"0";
        Assertions.assertEquals(expectedString, game.toString());
    }

    @Test
    @DisplayName("A strike should not yet be scored after 1 extra chance")
    void scoreX2() throws Exception {
        Game game = new Game();
        game.saveBall('X');
        game.saveBall('9');
        String expectedString = PINFALLS+"\0\tX\t9"+SCORE+"0\t\t0";
        Assertions.assertEquals(expectedString, game.toString());
    }

    @Test
    @DisplayName("A strike should be scored after 2 extra chances")
    void scoreX3() throws Exception {
        Game game = new Game();
        game.saveBall('X');
        game.saveBall('8');
        game.saveBall('1');
        String expectedString = PINFALLS+"\0\tX\t8\t1"+SCORE+"19\t\t28";
        Assertions.assertEquals(expectedString, game.toString());
    }

    @Test
    @DisplayName("Should not score instantly a frame with spare")
    void scoreSpare1() throws Exception {
        Game game = new Game();
        game.saveBall('5');
        game.saveBall('5');
        String expectedString = PINFALLS+"5\t/"+SCORE+"0";
        Assertions.assertEquals(expectedString, game.toString());
    }

    @Test
    @DisplayName("A spare should be scored after 1 more chance")
    void scoreSpare2() throws Exception {
        Game game = new Game();
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('2');
        String expectedString = PINFALLS+"5\t/\t2"+SCORE+"12\t\t0";
        Assertions.assertEquals(expectedString, game.toString());
    }

    @Test
    @DisplayName("A spare should be scored after 1 more chance. Next is strike")
    void scoreSpare3() throws Exception {
        Game game = new Game();
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('X');
        String expectedString = PINFALLS+"5\t/\t\0\tX"+SCORE+"20\t\t0";
        Assertions.assertEquals(expectedString, game.toString());
    }

    @Test
    @DisplayName("Complete a game. Check if isComplete = true")
    void completeGame() throws Exception {
        Game game = new Game();
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        game.saveBall('5');
        Assertions.assertFalse(game.isComplete());
        game.saveBall('5');
        Assertions.assertTrue(game.isComplete());
    }
}
