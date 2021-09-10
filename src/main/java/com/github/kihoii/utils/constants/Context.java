package com.github.kihoii.utils.constants;

//import com.github.kihoii.utils.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Context {

    public static final int N_BLOCKS = 15;
    public static final int BLOCK_SIZE = 24;
    public static final int WEIGHT = 15;
    public static final int HIGH = 17;
    public static final int SCREEN_X = WEIGHT * BLOCK_SIZE;
    public static final int SCREEN_Y = HIGH * BLOCK_SIZE;

    public static final short[] map = Map.mapData;

    public static int PACMAN_SPEED = 3;
    public static final int START_X = 7 * BLOCK_SIZE;
    public static final int START_Y = 12 * BLOCK_SIZE;

    public static int TIMER_DELAY = 40;

    public static int N_GHOSTS = 4;
    public static int GHOSTS_SPEED = 3;

    public static int N_DOTS = 159;
    public static int TOTAL_SCORE = N_DOTS*10;

    public static File file = new File("src/main/resources/HighScores");
    public static Integer[] scores = new Integer[10];

    public static final Image down = new ImageIcon("pics/down.gif").getImage();
    public static final Image up = new ImageIcon("pics/up.gif").getImage();
    public static final Image left = new ImageIcon("pics/left.gif").getImage();
    public static final Image right = new ImageIcon("pics/right.gif").getImage();
    public static final Image pacman = new ImageIcon("pics/pacman.png").getImage();

    public static final Image ghost = new ImageIcon("pics/ghost.gif").getImage();
    public static final Image heart = new ImageIcon("pics/heart.png").getImage();
}
