package com.github.kihoii.model.ghosts;

import com.github.kihoii.model.Model;
import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.enums.States;

public class RandomGhosts {

    public static void move(){
        int count;
        int pos;

        for (int i = 0; i < Context.N_GHOSTS; i++){
            if (Context.ghosts_x[i] % Context.BLOCK_SIZE == 0
                    && Context.ghosts_y[i] % Context.BLOCK_SIZE == 0) {
                pos = Context.ghosts_x[i] / Context.BLOCK_SIZE + Context.N_BLOCKS
                        * (Context.ghosts_y[i] / Context.BLOCK_SIZE);

                count = 0;

                if ((Model.screenData[pos] & 1) == 0 && Context.ghosts_dx[i] != 1) {
                    Context.dx[count] = -1;
                    Context.dy[count] = 0;
                    count++;
                }

                if ((Model.screenData[pos] & 2) == 0 && Context.ghosts_dy[i] != 1) {
                    Context.dx[count] = 0;
                    Context.dy[count] = -1;
                    count++;
                }

                if ((Model.screenData[pos] & 4) == 0 && Context.ghosts_dx[i] != -1) {
                    Context.dx[count] = 1;
                    Context.dy[count] = 0;
                    count++;
                }

                if ((Model.screenData[pos] & 8) == 0 && Context.ghosts_dy[i] != -1) {
                    Context.dx[count] = 0;
                    Context.dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((Model.screenData[pos] & 15) == 15) {
                        Context.ghosts_dx[i] = 0;
                        Context.ghosts_dy[i] = 0;
                    } else {
                        Context.ghosts_dx[i] = -Context.ghosts_dx[i];
                        Context.ghosts_dy[i] = -Context.ghosts_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    Context.ghosts_dx[i] = Context.dx[count];
                    Context.ghosts_dy[i] = Context.dy[count];
                }
            }

            Context.ghosts_x[i] = Context.ghosts_x[i] + (Context.ghosts_dx[i] * Context.GHOSTS_SPEED);
            Context.ghosts_y[i] = Context.ghosts_y[i] + (Context.ghosts_dy[i] * Context.GHOSTS_SPEED);

            if (Context.pacman_x > (Context.ghosts_x[i] - 12)
                    && Context.pacman_x < (Context.ghosts_x[i] + 12)
                    && Context.pacman_y > (Context.ghosts_y[i] - 12)
                    && Context.pacman_y < (Context.ghosts_y[i] + 12)
                    && Model.getCurState().equals(States.IN_PROC)) {

                Model.alive = false;
            }
        }
    }

}

