package com.github.kihoii.model.pacman;


import com.github.kihoii.model.Model;
import com.github.kihoii.model.PossibleToMove;
import com.github.kihoii.utils.constants.Context;

public class MovePacman {

    public static boolean move(){
        int pos;
        short curBlock;

        if (Context.pacman_x % Context.BLOCK_SIZE == 0 && Context.pacman_y % Context.BLOCK_SIZE == 0) {
            pos = Context.pacman_x / Context.BLOCK_SIZE + Context.N_BLOCKS * (Context.pacman_y / Context.BLOCK_SIZE);
            curBlock = Model.screenData[pos];

            if ((curBlock & 16) != 0) {
                Model.screenData[pos] = (short) (curBlock & 15);
                Model.score += 10;
            }

            if (Context.req_dx != 0 || Context.req_dy != 0) {
                if (PossibleToMove.is(Context.req_dx, Context.req_dy, curBlock)) {
                    Context.pacman_dx = Context.req_dx;
                    Context.pacman_dy = Context.req_dy;

                } else {
                    Context.pacman_dx = 0;
                    Context.pacman_dy = 0;
                    return false;
                }

            }

            if (!PossibleToMove.is(Context.pacman_dx, Context.pacman_dy, curBlock)) {
                Context.pacman_dx = 0;
                Context.pacman_dy = 0;
                return false;
            }

        }
        return true;
    }

}
