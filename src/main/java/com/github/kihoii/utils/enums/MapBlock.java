package com.github.kihoii.utils.enums;

// CR: please move to package where it is used
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


    // CR: please rename it to something meaningful
    // CR: i.e. k -> blockType
    // CR: also it would be safer to have short instead of int
    private final int k;

    MapBlock(int i) {
        k = i;
    }

    // CR: why not make it a boolean method, since you compare only with 0
    // CR: boolean is(short block) { (k & block) != 0 }
    public int get(short block){
        return k & block;
    }
}
