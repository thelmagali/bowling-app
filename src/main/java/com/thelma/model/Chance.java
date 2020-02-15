package com.thelma.model;

public class Chance {
    private String name;
    private char pitfalls;

    public Chance(String name, char pitfalls){
        this.name = name;
        this.pitfalls = pitfalls;
    }

    public String getName() {
        return name;
    }

    public char getPitfalls() {
        return pitfalls;
    }

}
