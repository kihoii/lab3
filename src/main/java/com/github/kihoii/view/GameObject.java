package com.github.kihoii.view;

import com.github.kihoii.model.Direction;

public interface GameObject {


    void accept(GameObjectVisitor visitor);

    class Pacman implements GameObject {
        private final Direction direction;
        private final int x, y;

        public Pacman(Direction direction, int x, int y) {
            this.direction = direction;
            this.x = x;
            this.y = y;
        }

        public Direction getDirection() {
            return direction;
        }

        // CR: make GameObject abstract class and move x, y related logic there
        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }


    class Ghost implements GameObject {

        private final int x, y;

        public Ghost(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }

    class Point implements GameObject {

        private final int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        @Override
        public void accept(GameObjectVisitor visitor) {
            visitor.visit(this);
        }
    }

}
