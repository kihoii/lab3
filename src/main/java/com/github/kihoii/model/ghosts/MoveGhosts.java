package com.github.kihoii.model.ghosts;

import com.github.kihoii.model.Model;
import com.github.kihoii.model.pacman.Pacman;
import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.enums.Direction;
import com.github.kihoii.utils.enums.MapBlocks;
import com.github.kihoii.utils.enums.States;

// CR: rename class to ModelUtils
// CR: rename move -> moveGhosts
// CR: every ghost should have method move inside of it, so it's encapsulated inside Ghost class
// CR: same thing is true for pacman
public class MoveGhosts {

    public static void move(Pacman pacman, Ghost[] ghosts, short[] screenData){
        int count;
        int pos;

        int[] dx = new int[4];
        int[] dy = new int[4];

        for (int i = 0; i < Context.N_GHOSTS; i++){
            if (ghosts[i].getX() % Context.BLOCK_SIZE == 0
                    && ghosts[i].getY() % Context.BLOCK_SIZE == 0) {
                pos = ghosts[i].getX() / Context.BLOCK_SIZE + Context.N_BLOCKS
                        * (ghosts[i].getY() / Context.BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & MapBlocks.L_BORDER.get()) == 0
                        && ghosts[i].getDx() != Direction.RIGHT.get()) {
                    dx[count] = Direction.LEFT.get();
                    dy[count] = Direction.NONE.get();
                    count++;
                }

                if ((screenData[pos] & MapBlocks.UP_BORDER.get()) == 0
                        && ghosts[i].getDy() != Direction.UP.get()) {
                    dx[count] = Direction.NONE.get();
                    dy[count] = Direction.DOWN.get();
                    count++;
                }

                if ((screenData[pos] & MapBlocks.R_BORDER.get()) == 0
                        && ghosts[i].getDx() != Direction.LEFT.get()) {
                    dx[count] = Direction.RIGHT.get();
                    dy[count] = Direction.NONE.get();
                    count++;
                }

                if ((screenData[pos] & MapBlocks.D_BORDER.get()) == 0
                        && ghosts[i].getDy() != Direction.DOWN.get()) {
                    dx[count] = Direction.NONE.get();
                    dy[count] = Direction.UP.get();
                    count++;
                }

                if (count == 0) {

                    // CR: what is 15?
                    if ((screenData[pos] & 15) == 15) {
                        ghosts[i].setDxy(Direction.NONE.get(),Direction.NONE.get());
                    } else {
                        ghosts[i].setDxy(-ghosts[i].getDx(), -ghosts[i].getDy());
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghosts[i].setDxy(dx[count], dy[count]);

                }
            }

            ghosts[i].setCoords(ghosts[i].getX() + (ghosts[i].getDx() * Context.GHOSTS_SPEED),
                    ghosts[i].getY() + (ghosts[i].getDy() * Context.GHOSTS_SPEED));

            if (pacman.getX() > (ghosts[i].getX() - 12)
                    && pacman.getX() < (ghosts[i].getX() + 12)
                    && pacman.getY() > (ghosts[i].getY() - 12)
                    && pacman.getY() < (ghosts[i].getY() + 12)
                    && Model.getCurState().equals(States.IN_PROC)) {

                pacman.setAlive(false);
                pacman.setLives();
                return;
            }
        }
    }

}

