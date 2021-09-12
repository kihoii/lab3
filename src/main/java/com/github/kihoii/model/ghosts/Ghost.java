package com.github.kihoii.model.ghosts;

import com.github.kihoii.model.pacman.Pacman;
import com.github.kihoii.model.Direction;
import com.github.kihoii.utils.enums.MapBlock;

public class Ghost {

    private int x, y;
    // CR: this fields must have type Direction 
    private int dx, dy;

    public final int N_GHOSTS = 4;
    public final int BLOCK_SIZE = 24;
    public final int N_BLOCKS = 15;

    public Ghost(){
        x = 7 * BLOCK_SIZE;
        y = 7 * BLOCK_SIZE;
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

    // CR: moveGhost -> move
    public void moveGhost(Pacman pacman, short[] screenData){
        int count;
        int pos;

        int[] dxCount = new int[4];
        int[] dyCount = new int[4];

        // CR: every ghost should move separately, it shouldn't know anything about other ghosts
        for (int i = 0; i < N_GHOSTS; i++){
            if (x % BLOCK_SIZE == 0
                    && y % BLOCK_SIZE == 0) {
                pos = x / BLOCK_SIZE + N_BLOCKS
                        * (y / BLOCK_SIZE);

                count = 0;

                if ((MapBlock.L_BORDER.get(screenData[pos])) == 0
                        && dx != Direction.RIGHT.get()) {
                    dxCount[count] = Direction.LEFT.get();
                    dyCount[count] = Direction.NONE.get();
                    count++;
                }

                if ((MapBlock.UP_BORDER.get(screenData[pos])) == 0
                        && dy != Direction.UP.get()) {
                    dxCount[count] = Direction.NONE.get();
                    dyCount[count] = Direction.DOWN.get();
                    count++;
                }

                if ((MapBlock.R_BORDER.get(screenData[pos])) == 0
                        && dx != Direction.LEFT.get()) {
                    dxCount[count] = Direction.RIGHT.get();
                    dyCount[count] = Direction.NONE.get();
                    count++;
                }

                if ((MapBlock.D_BORDER.get(screenData[pos])) == 0
                        && dy != Direction.DOWN.get()) {
                    dxCount[count] = Direction.NONE.get();
                    dyCount[count] = Direction.UP.get();
                    count++;
                }

                if (count == 0) {

                    // CR: what is 15?
                    // like none direction is clear
                    if ((screenData[pos] & 15) == 15) {
                        dx = Direction.NONE.get();
                        dy = Direction.NONE.get();
                    } else {
                        dx = -dx;
                        dy = -dy;
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    dx = dxCount[count];
                    dy = dyCount[count];

                }
            }
        }

        int speed = 3;
        x += dx * speed;
        y += dy * speed;


        if (pacman.getX() > (x - 12)
                && pacman.getX() < (x + 12)
                && pacman.getY() > (y - 12)
                && pacman.getY() < (y + 12)) {

            pacman.setAlive(false);
            pacman.setLives();
        }
    }

}
