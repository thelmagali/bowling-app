package com.thelma.model;

public class Chance {
    private String name;
    private char pinfalls;

    public Chance(String name, char pinfalls){
        this.name = name;
        this.pinfalls = pinfalls;
    }

    public String getName() {
        return name;
    }

    public char getPinfalls() {
        return pinfalls;
    }

}
