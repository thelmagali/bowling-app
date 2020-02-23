package com.thelma.controller.impl;

import com.thelma.controller.Bowling;
import com.thelma.controller.Game;
import com.thelma.controller.GameInputReader;
import com.thelma.model.Chance;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class BowlingMemImpl implements Bowling {
    private Map<String, Game> personGameMap;

    @Inject
    private Instance<Game> gameProvider;

    public BowlingMemImpl(){
        personGameMap = new HashMap<>();
    }

    public void play(GameInputReader inputReader) throws Exception{
        while(true){
            Chance chance = inputReader.readChance();
            if(chance == null){
                return;
            }
            Game game = personGameMap.get(chance.getName());
            if(game == null){
                game = gameProvider.get();
                personGameMap.put(chance.getName(), game);
            } else{
                if(game.isComplete())
                    throw new Exception("Invalid chance. This game is already complete");
            }
            game.saveChance(chance.getPinfalls());
        }
    }

    public void printResult(){
        System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        personGameMap.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }

}
