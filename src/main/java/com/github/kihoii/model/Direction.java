package com.github.kihoii.model;

public enum Direction {
    UP(-1){
        public boolean backwards(Direction d){
            return d == DOWN;
        }
    },
    DOWN(1){
        public boolean backwards(Direction d){
            return d == UP;
        }
    },
    RIGHT(1){
        public boolean backwards(Direction d){
            return d == LEFT;
        }
    },
    LEFT(-1){
        public boolean backwards(Direction d){
            return d == RIGHT;
        }
    },
    NONE(0){
        public boolean backwards(Direction d){
            return false;
        }
    };

    private final int coefficient;

    Direction(int coefficient) { this.coefficient = coefficient ;}

    public int adjustSpeed(int speed){
        return coefficient * speed;
    }

    abstract boolean backwards(Direction d);


}
