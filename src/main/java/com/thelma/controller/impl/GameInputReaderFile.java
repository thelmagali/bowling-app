package com.thelma.controller.impl;

import com.thelma.model.Chance;
import com.thelma.controller.GameInputReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class GameInputReaderFile implements GameInputReader {
    private Scanner scanner;

    public GameInputReaderFile(String filename) throws FileNotFoundException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
        if(inputStream != null){
            scanner = new Scanner(inputStream);
        } else {
            scanner = new Scanner(new File(filename));
        }
    }

    public Chance readChance() throws Exception {
        try{
            if(scanner.hasNextLine()){
                String[] inputArray = scanner.nextLine().split("\t");
                if(inputArray.length != 2) throw new Exception("Line must have 2 elements");
                String name = inputArray[0];
                String pitfalls = inputArray[1];
                if(!pitfalls.matches("10|[0-9]|[fF]")) throw new Exception("Invalid value of pitfalls");
                char chancePitfalls;
                switch (pitfalls) {
                    case "10":
                        chancePitfalls = 'X';
                        break;
                    case "f":
                    case "F":
                        chancePitfalls = 'F';
                        break;
                    default:
                        chancePitfalls = pitfalls.charAt(0);
                }
                return new Chance(name, chancePitfalls);
            }
            scanner.close();
            return null;
        } catch (Exception e){
            scanner.close();
            throw e;
        }
    }
}