package com.github.kihoii.model;

public enum Direction {
    UP(1),
    DOWN(-1),
    RIGHT(1),
    LEFT(-1),
    NONE(0);

    private final int k;

    Direction(int i) { k = i ;}

    // CR: remove this method
    // CR: add method 
    // public int adjustSpeed(int speed) { return k * speed; }
    // CR: rename k to coefficient
    public int get(){
        return k;
    }
}
