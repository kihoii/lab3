package com.github.kihoii.utils.enums;

public enum Direction {
    UP(1),
    DOWN(-1),
    RIGHT(1),
    LEFT(-1),
    NONE(0);

    private final int k;

    Direction(int i) { k = i ;}

    public int get(){
        return k;
    }
}
