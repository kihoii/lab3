package com.github.kihoii.view.draw;

import com.github.kihoii.utils.constants.Context;

import javax.swing.*;
import java.awt.*;

public class DrawPacman extends JFrame {

    private final Graphics2D g2d;

    public DrawPacman(Graphics2D g2d){
        this.g2d = g2d;
        paintPacman();
    }

    private void paintPacman(){
        if (Context.req_dx == -1) {
            g2d.drawImage(Context.left, Context.pacman_x + 1, Context.pacman_y + 1, this);
        } else if (Context.req_dx == 1) {
            g2d.drawImage(Context.right, Context.pacman_x + 1, Context.pacman_y + 1, this);
        } else if (Context.req_dy == -1) {
            g2d.drawImage(Context.up, Context.pacman_x + 1, Context.pacman_y + 1, this);
        } else if (Context.req_dy == 1){
            g2d.drawImage(Context.down, Context.pacman_x + 1, Context.pacman_y + 1, this);
        } else {
            g2d.drawImage(Context.pacman, Context.pacman_x + 1, Context.pacman_y + 1, this);
        }
    }

}
