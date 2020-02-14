package com.thelma;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        List<Turn> turns = new ArrayList<>();
        turns.add(new Turn("thelma", '1'));
        turns.add(new Turn("thelma", '9'));
        turns.add(new Turn("thelma", '3'));
        turns.add(new Turn("thelma", '2'));
        turns.add(new Turn("thelma", 'X'));
        turns.add(new Turn("thelma", '4'));
        turns.add(new Turn("thelma", '3'));
        turns.add(new Turn("thelma", '2'));
        turns.add(new Turn("thelma", '2'));
        turns.add(new Turn("thelma", '2'));
        turns.add(new Turn("thelma", '2'));
        Bowling bowling = new Bowling();
        for (Turn turn: turns){
            bowling.playChance(turn);
        }
    }
}
