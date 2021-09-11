package com.github.kihoii.model.pacman;

import com.github.kihoii.model.Model;
import com.github.kihoii.model.Direction;
import com.github.kihoii.utils.enums.MapBlock;

public class Pacman {

    private int x, y;
    private int dx, dy;
    private Direction direction;
    private boolean alive;
    private int lives;
    private int score;

    public final int BLOCK_SIZE = 24;
    public final int N_BLOCKS = 15;
    public final int START_X = 7 * BLOCK_SIZE;
    public final int START_Y = 12 * BLOCK_SIZE;

    public Pacman(){
        score = 0;
        lives = 3;
        alive = true;
        x = START_X;
        y = START_Y;
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

    public int getScore(){
        return score;
    }

    public boolean movePacman(int req_dx, int req_dy){
        int pos;
        short curBlock;

        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {
            pos = x / BLOCK_SIZE + N_BLOCKS * (y / BLOCK_SIZE);
            curBlock = Model.screenData[pos];

            if ((MapBlock.DOT.get(curBlock)) != 0) {
                Model.screenData[pos] = (short) (curBlock & 15);
                score += 10;
            }

            if (req_dx != Direction.NONE.get() || req_dy != Direction.NONE.get()) {
                if (isPossibleToMove(req_dx, req_dy, curBlock)) {
                    dx = req_dx;
                    dy = req_dy;

                } else {
                    dx = Direction.NONE.get();
                    dy = Direction.NONE.get();
                    return false;
                }

            }

            if (!isPossibleToMove(dx, dy, curBlock)) {
                dx = Direction.NONE.get();
                dy = Direction.NONE.get();
                return false;
            }

        }
        return true;
    }

    public static boolean isPossibleToMove(int x, int y, short curBlock){
        return !((x == -1 && y == 0 && (MapBlock.L_BORDER.get(curBlock)) != 0)
                || (x == 1 && y == 0 && (MapBlock.R_BORDER.get(curBlock)) != 0)
                || (x == 0 && y == -1 && (MapBlock.UP_BORDER.get(curBlock)) != 0)
                || (x == 0 && y == 1 && (MapBlock.D_BORDER.get(curBlock)) != 0));
    }

}
