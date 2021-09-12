package com.github.kihoii.model;

public enum Direction {
    UP(1),
    DOWN(-1),
    RIGHT(1),
    LEFT(-1),
    NONE(0);

    // CR: you don't need this field at all, just use Direction instead of int for dx, dy fields
    //i need to know how coords are changing
    private final int k;

    Direction(int i) { k = i ;}

    public int get(){
        return k;
    }
}
