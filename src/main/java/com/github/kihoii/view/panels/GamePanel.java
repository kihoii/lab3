package com.github.kihoii.view.panels;

import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.constants.Map;
import com.github.kihoii.view.draw.DrawGhosts;
import com.github.kihoii.view.draw.DrawMap;
import com.github.kihoii.view.draw.DrawPacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private short[] screenData;

    private final Font scoreFNT = new Font("arial", Font.BOLD, 15);
    private int score;

    int lives;

    public GamePanel(ActionListener actionListener){
        screenData = Map.mapData;
        this.setPreferredSize(new Dimension(376,500));
        score = 0;
        lives = 3;

        JButton pauseButton = new JButton("PAUSE");
        pauseButton.setFont(scoreFNT);
        pauseButton.setForeground(Color.YELLOW);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        pauseButton.setSize(new Dimension(100,50));
        pauseButton.setBounds(20, 420, 70, 30);
        pauseButton.addActionListener(actionListener);

        this.add(pauseButton);
        this.setLayout(null);
    }

    public void updateField(short[] screenData, int score, int lives){
        this.screenData = screenData;
        this.score = score;
        this.lives = lives;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 376, 500);
        new DrawMap(screenData, g2d);
        new DrawPacman(g2d);
        new DrawGhosts(g2d);

        for(int i = 0; i < lives; i++){
            g2d.drawImage(Context.heart, 330 - 30*i, 413, this);
        }

        g.setFont(scoreFNT);
        g.setColor(Color.YELLOW);
        String s = "Score: " + score;
        g.drawString(s, 376/2+90, 450);

    }


}
