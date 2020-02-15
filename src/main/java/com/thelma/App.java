package com.thelma;

import com.thelma.model.Bowling;
import com.thelma.model.Chance;
import com.thelma.model.GameFileReader;

public class App 
{
    public static void main(String[] args) {
        try {
            GameFileReader reader = new GameFileReader(args[0]);
            Bowling bowling = new Bowling();
            while(true){
                Chance chance = reader.readLine();
                if(chance == null){
                    break;
                }
                bowling.playChance(chance);
            }
            bowling.printResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
