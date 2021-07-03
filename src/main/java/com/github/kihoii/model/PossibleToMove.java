package com.github.kihoii.model;

public class PossibleToMove {

    public static boolean is(int x, int y, short curBlock){
        return !((x == -1 && y == 0 && (curBlock & 1) != 0)
                || (x == 1 && y == 0 && (curBlock & 4) != 0)
                || (x == 0 && y == -1 && (curBlock & 2) != 0)
                || (x == 0 && y == 1 && (curBlock & 8) != 0));
    }

}
