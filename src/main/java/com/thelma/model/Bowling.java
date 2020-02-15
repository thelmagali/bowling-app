package com.thelma.model;

import java.util.HashMap;
import java.util.Map;

public class Bowling {
    private Map<String, Game> personGameMap;

    public Bowling(){
        personGameMap = new HashMap<>();
    }

    public void playChance(Chance turn) throws Exception {
        Game game;
        game = personGameMap.get(turn.getName());
        if(game == null){
            game = new Game();
            personGameMap.put(turn.getName(), game);
        } else{
            if(game.isComplete()) throw new Exception("Invalid chance. This game is already complete");
        }
        game.saveBall(turn.getPitfalls());
    }

    public void printResult(){
        System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        for(Map.Entry<String, Game> personGame: personGameMap.entrySet()){
            System.out.println(personGame.getKey());
            personGame.getValue().print();
        }
    }
}
