package com.github.kihoii.utils.enums;

public enum MapBlocks {

    /* 0 - barrier
     * 1 - left border
     * 2 - top border
     * 4 - right border
     * 8 - bottom border
     * 16 - simple dot
     * 32 - ghost house entrance
     */
    BARRIER(0),
    L_BORDER(1),
    UP_BORDER(2),
    R_BORDER(4),
    D_BORDER(8),
    DOT(16),
    G_HOUSE(32);


    private final int k;

    MapBlocks(int i) {
        k = i;
    }

    public int get(){
        return k;
    }
}
