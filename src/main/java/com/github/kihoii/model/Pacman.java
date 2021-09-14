package com.github.kihoii.model;

import com.github.kihoii.utils.*;

public class Pacman {

    private static final int BLOCK_SIZE = 24;
    private static final int N_BLOCKS = 15;
    private static final int PACMAN_SPEED = 3;

    private int x = 7 * BLOCK_SIZE;
    private int y = 12 * BLOCK_SIZE;
    private int dx;
    private int dy;
    private Direction direction = Direction.NONE;
    private int lives = 3;
    private int score;

    public void setCoords(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public boolean getAlive(){
        return lives > 0;
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

    public int getLives(){
        return lives;
    }

    public int getScore(){
        return score;
    }

    public short[] move(short[] screenData, Ghost[] ghosts) {
        int reqDx = 0;
        int reqDy = 0;
        switch (direction) {
            case UP -> reqDy = -1;
            case DOWN -> reqDy = 1;
            case RIGHT -> reqDx = 1;
            case LEFT -> reqDx = -1;
        }

        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {
            int pos = x / BLOCK_SIZE + N_BLOCKS * (y / BLOCK_SIZE);
            short curBlock = screenData[pos];

            if (!MapBlock.DOT.is(curBlock)) {
                screenData[pos] = (short) (curBlock & 15);
                score += 10;
            }

            if (reqDx != Direction.NONE.get() || reqDy != Direction.NONE.get()) {
                if (isPossibleToMove(reqDx, reqDy, curBlock)) {
                    dx = reqDx;
                    dy = reqDy;

                } else {
                    dx = Direction.NONE.get();
                    dy = Direction.NONE.get();
                    return screenData;
                }

            }

            if (!isPossibleToMove(dx, dy, curBlock)) {
                dx = Direction.NONE.get();
                dy = Direction.NONE.get();
                return screenData;
            }

        }
        x += dx * PACMAN_SPEED;
        y += dy * PACMAN_SPEED;

        boolean hit = false;
        for (int i = 0 ; i < 4; i++){
            if (x > (ghosts[i].getX() - 12)
                    && x < (ghosts[i].getX() + 12)
                    && y > (ghosts[i].getY() - 12)
                    && y < (ghosts[i].getY() + 12)) {

                lives--;
                hit = true;
            }
        }

        if(hit && lives > 0){
            direction = Direction.NONE;
            dx = 0;
            dy = 0;
            x = 7 * BLOCK_SIZE;
            y = 12 * BLOCK_SIZE;
        }

        return screenData;
    }

    private static boolean isPossibleToMove(int x, int y, short curBlock){
        return !((x == -1 && y == 0 && (!MapBlock.L_BORDER.is(curBlock)))
                || (x == 1 && y == 0 && (!MapBlock.R_BORDER.is(curBlock)))
                || (x == 0 && y == -1 && (!MapBlock.UP_BORDER.is(curBlock)))
                || (x == 0 && y == 1 && (!MapBlock.D_BORDER.is(curBlock))));
    }

}
