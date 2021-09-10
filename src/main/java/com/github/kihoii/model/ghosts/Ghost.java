package com.github.kihoii.model.ghosts;

import com.github.kihoii.utils.constants.Context;

public class Ghost {

    private int x, y;
    private int dx, dy;

    public Ghost(){
        x = 7 * Context.BLOCK_SIZE;
        y = 7 * Context.BLOCK_SIZE;
    }

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
