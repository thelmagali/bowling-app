package com.thelma.controller.common;

import com.thelma.controller.Bowling;
import com.thelma.controller.GameInputReader;
import com.thelma.model.Chance;
import com.thelma.model.PlayerGame;

import java.util.List;

public abstract class BowlingCommonImpl implements Bowling {

    public void play(GameInputReader inputReader) throws Exception{
        while(true){
            Chance chance = inputReader.readChance();
            if(chance == null){
                break;
            }
            playChance(chance);
        }
    }

    public void printResult(){
        System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        for(PlayerGame playerGame: getRecords()){
            System.out.println(playerGame.getName());
            System.out.println(playerGame.getGame());
        }
    }

    protected abstract List<PlayerGame> getRecords();

    public abstract void playChance(Chance chance) throws Exception;
}
