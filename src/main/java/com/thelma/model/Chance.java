package com.thelma.model;

public class Chance {
    private String name;
    private char pitfalls;

    Chance(String name, char pitfalls){
        this.name = name;
        this.pitfalls = pitfalls;
    }

    String getName() {
        return name;
    }

    char getPitfalls() {
        return pitfalls;
    }

}
