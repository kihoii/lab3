package com.github.kihoii.utils;

public enum MapBlock {

    /* 0 - barrier
     * 1 - left border
     * 2 - top border
     * 4 - right border
     * 8 - bottom border
     * 16 - simple dot
     * 32 - ghost house entrance
     */
    L_BORDER((short)1),
    UP_BORDER((short)2),
    R_BORDER((short)4),
    D_BORDER((short)8),
    DOT((short)16),
    G_HOUSE((short)32);

    private final short blockType;

    MapBlock(short i) {
        blockType = i;
    }

    public boolean is(short block){
        return ((blockType & block) == 0);
    }
}
