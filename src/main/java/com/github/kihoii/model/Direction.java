package com.github.kihoii.model;

public enum Direction {
    UP(-1),
    DOWN(1),
    RIGHT(1),
    LEFT(-1),
    NONE(0);

    private final int coefficient;

    Direction(int i) { coefficient = i ;}

    public int adjustSpeed(int speed){
        return coefficient * speed;
    }
}
