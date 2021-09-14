package com.github.kihoii.view;

import com.github.kihoii.model.Direction;

public abstract class GameObject {

    private int x, y;

    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    void accept(GameObjectVisitor visitor) {}

    public static class Pacman extends GameObject {
        private final Direction direction;

        public Pacman(Direction direction_) {
            direction = direction_;
        }

        public Direction getDirection() {
            return direction;
        }


        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }


    public static class Ghost extends GameObject {

        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Point extends GameObject {

        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }

}
