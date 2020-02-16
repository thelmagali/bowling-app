package com.thelma.integration;

import com.thelma.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;


class AppIT {

    void testHelper(String inputFile, String inputDir) throws IOException {
        final String EXP_OUT_DIR = "src/test/resources/expected-output/";
        File outputFile = File.createTempFile(inputFile, "tmp");
        System.setOut(new PrintStream(new FileOutputStream(outputFile)));
        App.main(new String[]{inputDir + inputFile});
        try (Scanner expectedScanner = new Scanner(new File(EXP_OUT_DIR + inputFile));
             Scanner actualScanner = new Scanner(outputFile)) {
            while (expectedScanner.hasNext()) {
                Assertions.assertEquals(expectedScanner.nextLine(), actualScanner.nextLine());
            }
        }
    }

    void testHelper(String inputFile) throws IOException {
        final String INPUT_DIR = "src/test/resources/";
        testHelper(inputFile, INPUT_DIR);
    }

    @Test
    void testSampleInput() throws IOException {
        testHelper("sample-input", "src/main/resources/");
    }


    @Test
    void testPerfectGame() throws IOException {
       testHelper("perfect-game-single");
    }

    @Test
    void testIncompleteGame() throws IOException {
        testHelper("incomplete-game");
    }

    @Test
    void testRandomGame1() throws IOException {
        testHelper("2-players-random");
    }

    @Test
    void testZeroGame() throws IOException {
        testHelper("zero-game");
    }

    @Test
    void testSparesGame() throws IOException {
        testHelper("spares-game");
    }


}
