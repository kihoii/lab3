package com.github.kihoii.model.pacman;

import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.enums.Direction;

public class Pacman {

    private int x, y;
    private int dx, dy;
    private Direction direction;
    private boolean alive;
    private int lives;

    public Pacman(){
        lives = 3;
        alive = true;
        x = Context.START_X;
        y = Context.START_Y;
        direction = Direction.NONE;
    }

    public void setCoords(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public boolean getAlive(){
        return alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public void setLives(){
        lives--;
    }

    public int getLives(){
        return lives;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDxy(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

}
