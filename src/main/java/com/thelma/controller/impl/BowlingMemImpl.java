package com.thelma.controller.impl;

import com.thelma.controller.Bowling;
import com.thelma.controller.Game;
import com.thelma.controller.GameInputReader;
import com.thelma.model.Chance;
import com.thelma.model.PlayerGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BowlingMemImpl extends Bowling {
    private Map<String, Game> personGameMap;

    public BowlingMemImpl(GameInputReader reader){
        super(reader);
        personGameMap = new HashMap<>();
    }

    @Override
    public void playChance(Chance chance) throws Exception {
        Game game;
        game = personGameMap.get(chance.getName());
        if(game == null){
            game = new Game();
            personGameMap.put(chance.getName(), game);
        } else{
            if(game.isComplete())
                throw new Exception("Invalid chance. This game is already complete");
        }
        game.saveBall(chance.getPitfalls());
    }

    @Override
    public List<PlayerGame> getRecords() {
        return personGameMap.entrySet().stream()
                .map(x -> new PlayerGame(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }

}
