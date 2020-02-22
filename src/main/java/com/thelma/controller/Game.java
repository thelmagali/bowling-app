package com.thelma.controller;
public interface Game {
    boolean isComplete();
    void saveBall(char pitfall) throws Exception;
    String toString();
}