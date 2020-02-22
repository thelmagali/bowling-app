package com.thelma.controller.impl;

import com.thelma.controller.Game;
import com.thelma.controller.common.BowlingCommonImpl;
import com.thelma.model.Chance;
import com.thelma.model.PlayerGame;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BowlingMemImpl extends BowlingCommonImpl {
    private Map<String, Game> personGameMap;

    @Inject
    private Instance<Game> gameInstance;

    @PostConstruct
    void init(){
        personGameMap = new HashMap<>();
    }

    @Override
    public void playChance(Chance chance) throws Exception {
        Game game;
        game = personGameMap.get(chance.getName());
        if(game == null){
            game = gameInstance.get();
            personGameMap.put(chance.getName(), game);
        } else{
            if(game.isComplete())
                throw new Exception("Invalid chance. This game is already complete");
        }
        game.saveChance(chance.getPinfalls());
    }

    @Override
    public List<PlayerGame> getRecords() {
        return personGameMap.entrySet().stream()
                .map(x -> new PlayerGame(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }

}
