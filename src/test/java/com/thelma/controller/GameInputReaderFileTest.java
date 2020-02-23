package com.thelma.controller;

import com.thelma.controller.impl.GameInputReaderFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class GameInputReaderFileTest {

    @Test
    @DisplayName("Show throw FileNotFoundException when trying to create a reader with a non existing file")
    void shouldThrowError1() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
            new GameInputReaderFile("NON_EXISTING_FILENAME")
        );
    }

    @Test
    @DisplayName("Throw error when line does not have a tab")
    void shouldThrowError2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () ->{
            GameInputReader reader = new GameInputReaderFile("invalid-input-1");
            reader.readChance();
        });
        Assertions.assertEquals("Line must have 2 elements", thrown.getMessage());
    }

    @Test
    @DisplayName("Throw error when pinfalls has an invalid value")
    void shouldThrowError3(){
        Exception thrown = Assertions.assertThrows(Exception.class, () ->{
            GameInputReader reader = new GameInputReaderFile("invalid-input-2");
            reader.readChance();
        });
        Assertions.assertEquals("Invalid value of pinfalls", thrown.getMessage());
    }
}
