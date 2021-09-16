package com.github.kihoii.model;

import com.github.kihoii.utils.*;

public class Pacman {

    private static final int N_BLOCKS = 15;
    private static final int PACMAN_SPEED = 3;

    private int x;
    private int y;
    private Direction dx = Direction.NONE;
    private Direction dy = Direction.NONE;
    private Direction direction = Direction.NONE;
    private int lives = 3;
    private int score;

    public Pacman(int x, int y) {
        this.x = x;
        this.y = y;
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
        Direction reqDx = Direction.NONE;
        Direction reqDy = Direction.NONE;
        switch (direction) {
            case UP -> reqDy = Direction.UP;
            case DOWN -> reqDy = Direction.DOWN;
            case RIGHT -> reqDx = Direction.RIGHT;
            case LEFT -> reqDx = Direction.LEFT;
        }

        if (x % Model.BLOCK_SIZE == 0 && y % Model.BLOCK_SIZE == 0) {
            int pos = x / Model.BLOCK_SIZE + N_BLOCKS * (y / Model.BLOCK_SIZE);
            short curBlock = screenData[pos];

            if (MapBlock.DOT.is(curBlock)) {
                screenData[pos] = (short) (curBlock & 15);
                score += 10;
            }

            if (reqDx != Direction.NONE || reqDy != Direction.NONE) {
                if (isPossibleToMove(reqDx, reqDy, curBlock)) {
                    dx = reqDx;
                    dy = reqDy;

                } else {
                    dx = Direction.NONE;
                    dy = Direction.NONE;
                    hit(ghosts);
                    return screenData;
                }

            }

            if (!isPossibleToMove(dx, dy, curBlock)) {
                dx = Direction.NONE;
                dy = Direction.NONE;
                hit(ghosts);
                return screenData;
            }

        }
        x += dx.adjustSpeed(PACMAN_SPEED);
        y += dy.adjustSpeed(PACMAN_SPEED);

        hit(ghosts);

        return screenData;
    }

    private void hit(Ghost[] ghosts){
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
            dx = Direction.NONE;
            dy = Direction.NONE;
            x = 7 * Model.BLOCK_SIZE;
            y = 12 * Model.BLOCK_SIZE;
        }
    }

    private static boolean isPossibleToMove(Direction x, Direction y, short curBlock){
        return (y == Direction.UP && x == Direction.NONE && !(MapBlock.UP_BORDER.is(curBlock)))
                || (y == Direction.DOWN && x == Direction.NONE && !(MapBlock.D_BORDER.is(curBlock)))
                || (y == Direction.NONE && x == Direction.RIGHT && !(MapBlock.R_BORDER.is(curBlock)))
                || (y == Direction.NONE && x == Direction.LEFT && !(MapBlock.L_BORDER.is(curBlock)));
    }

}
