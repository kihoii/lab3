package com.github.kihoii.model.pacman;

import com.github.kihoii.model.Model;
import com.github.kihoii.model.Direction;
import com.github.kihoii.utils.enums.MapBlock;

// CR: i think packages ghosts and pacman are redundant, they only contain one class each
// CR: please move classes from this packages to model package
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
        // CR: that's very bug-prone and hard to read code when there are 
        // CR: two things that do the same thing. i'd expect pacman just to have lives field
        // CR: and method getAlive just check if lives != 0
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

    // CR: naming
    // CR: req_dx -> reqDx, req_dx -> reqDy
    // CR: movePacman -> move
    public boolean movePacman(int req_dx, int req_dy) {
        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {
            int pos = x / BLOCK_SIZE + N_BLOCKS * (y / BLOCK_SIZE);
            // CR: just pass screenData as method parameter, global fields lead to subtle bugs
            short curBlock = Model.screenData[pos];

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
