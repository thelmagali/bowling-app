package com.thelma.model;

public class Turn {
    private String name;
    private char pitfalls;

    public Turn(String name, char pitfalls){
        this.name = name;
        this.pitfalls = pitfalls;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    char getPitfalls() {
        return pitfalls;
    }

    public void setPitfalls(char pitfalls) {
        this.pitfalls = pitfalls;
    }
}
