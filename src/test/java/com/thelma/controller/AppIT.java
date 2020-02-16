package com.thelma.controller;

import com.thelma.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;


class AppIT {

    void testBowlingGame(String inputFile) throws IOException {
        final String EXP_OUT_DIR = "src/test/resources/expected-output/";
        final String INPUT_DIR = "src/test/resources/";
        File outputFile = File.createTempFile(inputFile, "tmp");
        System.setOut(new PrintStream(new FileOutputStream(outputFile)));
        App.main(new String[]{INPUT_DIR + inputFile});
        Scanner expectedScanner = new Scanner(new File(EXP_OUT_DIR + inputFile));
        Scanner actualScanner = new Scanner(outputFile);
        while(expectedScanner.hasNext()){
            Assertions.assertEquals(expectedScanner.nextLine(), actualScanner.nextLine());
        }
        expectedScanner.close();
        actualScanner.close();
    }


    @Test
    void testPerfectGame() throws IOException {
       testBowlingGame("perfect-game-single");
    }

    @Test
    void testIncompleteGame() throws IOException {
        testBowlingGame("incomplete-game");
    }

    @Test
    void testRandomGame1() throws IOException {
        testBowlingGame("2-players-random");
    }


}
