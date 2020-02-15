package com.thelma;

import com.thelma.controller.Bowling;
import com.thelma.controller.GameInputReader;
import com.thelma.controller.impl.BowlingMemImpl;
import com.thelma.controller.impl.GameInputReaderFile;

public class App 
{
    public static void main(String[] args) {
        try {
            if(args.length != 1){
                throw new Exception("1 argument is necessary. The name of the input file.");
            }
            GameInputReader reader = new GameInputReaderFile(args[0]);
            Bowling bowling = new BowlingMemImpl(reader);
            bowling.play();
            bowling.printResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
