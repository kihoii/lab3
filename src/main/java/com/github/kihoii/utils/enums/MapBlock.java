package com.github.kihoii.utils.enums;

public enum MapBlock {

    /* 0 - barrier
     * 1 - left border
     * 2 - top border
     * 4 - right border
     * 8 - bottom border
     * 16 - simple dot
     * 32 - ghost house entrance
     */
    L_BORDER(1),
    UP_BORDER(2),
    R_BORDER(4),
    D_BORDER(8),
    DOT(16),
    G_HOUSE(32);


    private final int k;

    MapBlock(int i) {
        k = i;
    }

    public int get(short block){
        return k & block;
    }
}
