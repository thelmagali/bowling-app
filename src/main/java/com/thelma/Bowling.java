package com.thelma;

import java.util.HashMap;
import java.util.Map;

public class Bowling {
    private Map<String, Game> personGameMap;

    public Bowling(){
        personGameMap = new HashMap<>();
    }

    public void playChance(Turn turn) throws Exception {
        Game game;
        game = personGameMap.get(turn.getName());
        if(game == null){
            game = new Game();
            personGameMap.put(turn.getName(), game);
        }
        game.saveBall(turn.getPitfalls());
    }
}
