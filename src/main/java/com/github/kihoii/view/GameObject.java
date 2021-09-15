package com.github.kihoii.view;

import com.github.kihoii.model.Direction;

public abstract class GameObject {

    private final int x;
    private final int y;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public abstract void accept(GameObjectVisitor visitor);

    public static class Pacman extends GameObject {
        private final Direction direction;

        public Pacman(Direction direction, int x, int y) {
            super(x, y);
            this.direction = direction;
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

        public Ghost(int x, int y) {
            super(x, y);
        }

        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Point extends GameObject {

        public Point(int x, int y) {
            super(x, y);
        }

        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }

}
