package com.thelma.model;

import com.thelma.controller.Game;

public class PlayerGame {
    private String name;
    private Game game;

    public PlayerGame(String name, Game game){
        this.name = name;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }
}
