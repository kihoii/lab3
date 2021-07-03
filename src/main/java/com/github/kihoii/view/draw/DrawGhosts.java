package com.github.kihoii.view.draw;

import com.github.kihoii.utils.constants.Context;

import javax.swing.*;
import java.awt.*;

public class DrawGhosts extends JFrame {

    private Graphics2D g2d;

    public DrawGhosts(Graphics2D g2d){
        this.g2d = g2d;
        paintGhosts();
    }

    private void paintGhosts(){

        for(int i = 0; i < Context.N_GHOSTS; i++){
            g2d.drawImage(Context.ghost, Context.ghosts_x[i] + 1, Context.ghosts_y[i] + 1, this);
        }

    }
}
