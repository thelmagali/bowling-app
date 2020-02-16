package com.thelma.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    //test saveBall, print and isComplete

    @Test
    @DisplayName("Should score instantly a frame without strike or spare")
    void scoreNormally() throws Exception {
        Game game = new Game();
        game.saveBall('3');
        game.saveBall('6');
        Assertions.assertEquals(9, game.getLastSavedScore());
    }

    @Test
    @DisplayName("Should not score instantly a frame with strike")
    void scoreX() throws Exception {
        Game game = new Game();
        game.saveBall('X');
        Assertions.assertEquals(0, game.getLastSavedScore());
    }

    @Test
    @DisplayName("Should not score instantly a frame with spare")
    void scoreSpare() throws Exception {
        Game game = new Game();
        game.saveBall('5');
        game.saveBall('5');
        Assertions.assertEquals(0, game.getLastSavedScore());
    }
}
