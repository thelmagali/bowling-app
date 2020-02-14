package com.thelma;

public class Turn {
    private String name;
    private char pitfalls;

    public Turn(String name, char pitfalls){
        this.name = name;
        this.pitfalls = pitfalls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getPitfalls() {
        return pitfalls;
    }

    public void setPitfalls(char pitfalls) {
        this.pitfalls = pitfalls;
    }
}
