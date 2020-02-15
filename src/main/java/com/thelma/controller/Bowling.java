package com.thelma.controller;

import com.thelma.model.Chance;
import com.thelma.model.PlayerGame;

import java.util.List;

public abstract class Bowling {

    private GameInputReader inputReader;

    public Bowling(GameInputReader gameInputReader){
        inputReader = gameInputReader;
    }

    public void play() throws Exception{
        while(true){
            Chance chance = inputReader.readChance();
            if(chance == null){
                break;
            }
            playChance(chance);
        }
    }

    public void printResult(){
        System.out.println("RegularFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        for(PlayerGame playerGame: getRecords()){
            System.out.println(playerGame.getName());
            playerGame.getGame().print();
        }
    }

    public abstract List<PlayerGame> getRecords();

    public abstract void playChance(Chance chance) throws Exception;
}
