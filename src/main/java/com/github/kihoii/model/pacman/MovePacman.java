package com.github.kihoii.model.pacman;


import com.github.kihoii.model.Model;
import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.enums.Direction;
import com.github.kihoii.utils.enums.MapBlocks;

public class MovePacman {

    public static boolean move(Pacman pacman, int req_dx, int req_dy){
        int pos;
        short curBlock;

        if (pacman.getX() % Context.BLOCK_SIZE == 0 && pacman.getY() % Context.BLOCK_SIZE == 0) {
            pos = pacman.getX() / Context.BLOCK_SIZE + Context.N_BLOCKS * (pacman.getY() / Context.BLOCK_SIZE);
            curBlock = Model.screenData[pos];

            if ((curBlock & MapBlocks.DOT.get()) != 0) {
                Model.screenData[pos] = (short) (curBlock & 15);
                Model.score += 10;
            }

            if (req_dx != Direction.NONE.get() || req_dy != Direction.NONE.get()) {
                if (isPossibleToMove(req_dx, req_dy, curBlock)) {
                    pacman.setDxy(req_dx, req_dy);

                } else {
                    pacman.setDxy(Direction.NONE.get(), Direction.NONE.get());
                    return false;
                }

            }

            if (!isPossibleToMove(pacman.getDx(), pacman.getDy(), curBlock)) {
                pacman.setDxy(Direction.NONE.get(), Direction.NONE.get());
                return false;
            }

        }
        return true;
    }

    public static boolean isPossibleToMove(int x, int y, short curBlock){
        return !((x == -1 && y == 0 && (curBlock & MapBlocks.L_BORDER.get()) != 0)
                || (x == 1 && y == 0 && (curBlock & MapBlocks.R_BORDER.get()) != 0)
                || (x == 0 && y == -1 && (curBlock & MapBlocks.UP_BORDER.get()) != 0)
                || (x == 0 && y == 1 && (curBlock & MapBlocks.D_BORDER.get()) != 0));
    }

}
