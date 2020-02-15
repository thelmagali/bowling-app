package com.thelma.controller;

import com.thelma.controller.impl.GameInputReaderFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class GameInputReaderFileTest {

    @Test
    @DisplayName("Show throw FileNotFoundException when trying to create a reader with a non existing file")
    void shouldThrowError() {
        Assertions.assertThrows(FileNotFoundException.class, () ->
            new GameInputReaderFile("NON_EXISTING_FILENAME")
        );
    }
}
