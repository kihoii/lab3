package com.github.kihoii.model;

import com.github.kihoii.utils.MapBlock;

public class Ghost {

    private int x, y;
    private Direction dx, dy;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SPEED = 3;

    public Ghost(int x, int y){
        this.x = x;
        this.y = y;
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

    public void move(short[] screenData){
        int count;
        int pos;

        Direction[] dxCount = new Direction[4];
        Direction[] dyCount = new Direction[4];

        if (x % BLOCK_SIZE == 0
                && y % BLOCK_SIZE == 0) {
            pos = x / BLOCK_SIZE + N_BLOCKS
                    * (y / BLOCK_SIZE);

            count = 0;

            if ((MapBlock.L_BORDER.is(screenData[pos]))
                    && dx != Direction.RIGHT) {
                dxCount[count] = Direction.LEFT;
                dyCount[count] = Direction.NONE;
                count++;
            }

            if ((MapBlock.UP_BORDER.is(screenData[pos]))
                    && dy != Direction.DOWN) {
                dxCount[count] = Direction.NONE;
                dyCount[count] = Direction.UP;
                count++;
            }

            if ((MapBlock.R_BORDER.is(screenData[pos]))
                    && dx != Direction.LEFT) {
                dxCount[count] = Direction.RIGHT;
                dyCount[count] = Direction.NONE;
                count++;
            }

            if ((MapBlock.D_BORDER.is(screenData[pos]))
                    && dy != Direction.UP) {
                dxCount[count] = Direction.NONE;
                dyCount[count] = Direction.DOWN;
                count++;
            }

            if (count == 0) {

                if (hasNoMoves(screenData[pos])) {
                    dx = Direction.NONE;
                    dy = Direction.NONE;
                } else {
                    if( dx == Direction.LEFT) dx = Direction.RIGHT;
                    else if (dx ==  Direction.RIGHT) dx = Direction.LEFT;

                    if(dy == Direction.UP) dy = Direction.DOWN;
                    else if (dy == Direction.DOWN) dy = Direction.UP;
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

        x += dx.adjustSpeed(SPEED);
        y += dy.adjustSpeed(SPEED);
    }

    private static boolean hasNoMoves(short block) {
        return MapBlock.UP_BORDER.is(block) &&
                MapBlock.R_BORDER.is(block) && MapBlock.L_BORDER.is(block) &&
                MapBlock.D_BORDER.is(block);
    }
}
