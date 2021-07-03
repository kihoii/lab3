package com.github.kihoii.view.draw;

import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.constants.Map;

import javax.swing.*;
import java.awt.*;

public class DrawMap extends JFrame  {

    private final Graphics2D g2d;

    private final short[] curMap;

    public DrawMap(short[] screenData, Graphics2D g2d){
        curMap = screenData;
        this.g2d = g2d;
        paintMap();
    }

    private void paintMap(){

        short i = 0;
        for(int y = 0; y < Context.SCREEN_Y; y += Context.BLOCK_SIZE){
            for(int x = 0; x < Context.SCREEN_X; x += Context.BLOCK_SIZE){
                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(1));

                if ((Map.mapData[i] == 0)) {
                    g2d.fillRect(x, y, Context.BLOCK_SIZE, Context.BLOCK_SIZE);
                }

                if ((curMap[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + Context.BLOCK_SIZE - 1);
                }

                if ((curMap[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + Context.BLOCK_SIZE - 1, y);
                }

                if ((curMap[i] & 4) != 0) {
                    g2d.drawLine(x + Context.BLOCK_SIZE - 1, y, x + Context.BLOCK_SIZE - 1,
                            y + Context.BLOCK_SIZE - 1);
                }

                if ((curMap[i] & 8) != 0) {
                    g2d.drawLine(x, y + Context.BLOCK_SIZE - 1, x + Context.BLOCK_SIZE - 1,
                            y + Context.BLOCK_SIZE - 1);
                }

                if ((curMap[i] & 16) != 0) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                if ((curMap[i] & 32) != 0) {
                    g2d.setColor(new Color(121, 121, 121));
                    g2d.drawLine(x, y, x + Context.BLOCK_SIZE - 1, y);
                }

                i++;
            }
        }
    }

}
