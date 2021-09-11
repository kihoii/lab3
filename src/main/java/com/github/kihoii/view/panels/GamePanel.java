package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.Controller;
import com.github.kihoii.controller.ViewListener;
import com.github.kihoii.model.ghosts.Ghost;
import com.github.kihoii.model.pacman.Pacman;
import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.utils.constants.Map;
import com.github.kihoii.utils.enums.Direction;
import com.github.kihoii.utils.enums.MapBlocks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private short[] screenData;

    private final Font scoreFNT = new Font("arial", Font.BOLD, 15);
    private int score;

    int lives;

    // CR: view shouldn't know anything about model objects
    private Pacman pacman;
    private Ghost[] ghosts;



    public GamePanel(ViewListener myListener, Pacman pacman, Ghost[] ghosts){
        screenData = Map.mapData;
        this.setPreferredSize(new Dimension(376,500));
        score = 0;
        lives = 3;

        this.pacman = pacman;
        this.ghosts = ghosts;

        JButton pauseButton = new JButton("PAUSE");
        pauseButton.setFont(scoreFNT);
        pauseButton.setForeground(Color.YELLOW);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        pauseButton.setSize(new Dimension(100,50));
        pauseButton.setBounds(20, 420, 70, 30);
        pauseButton.addActionListener(e -> myListener.onAction(ActionType.PAUSE));

        this.add(pauseButton);
        this.setLayout(null);
    }

    // CR: I'd expect that this method would have the following signature:
    // CR: updateField(GameObject[] gameObjects, int score, int lives)
    // CR: where GameObject is the enum with constants like WALL, PACMAN, GHOST and so on
    // CR: this method would be called from the Controller and Controller would convert model specific objects like
    // CR: Pacman, Ghost, short[] screenData into this GameObject array
    public void updateField(short[] screenData, int score, Pacman pacman, Ghost[] ghosts){
        this.screenData = screenData;
        this.score = score;
        this.pacman = pacman;
        this.ghosts = ghosts;
        lives = pacman.getLives();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 376, 500);
        paintMap(g2d);
        paintPacman(g2d);
        paintGhosts(g2d);

        for(int i = 0; i < lives; i++){
            g2d.drawImage(Context.heart, 330 - 30*i, 413, this);
        }

        g.setFont(scoreFNT);
        g.setColor(Color.YELLOW);
        String s = "Score: " + score;
        g.drawString(s, 376/2+90, 450);

    }

    private void paintMap(Graphics2D g2d){

        short i = 0;
        for(int y = 0; y < Context.SCREEN_Y; y += Context.BLOCK_SIZE){
            for(int x = 0; x < Context.SCREEN_X; x += Context.BLOCK_SIZE){
                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(1));

                if ((Map.mapData[i] == 0)) {
                    g2d.fillRect(x, y, Context.BLOCK_SIZE, Context.BLOCK_SIZE);
                }

                if ((screenData[i] & MapBlocks.L_BORDER.get()) != 0) {
                    g2d.drawLine(x, y, x, y + Context.BLOCK_SIZE - 1);
                }

                if ((screenData[i] & MapBlocks.UP_BORDER.get()) != 0) {
                    g2d.drawLine(x, y, x + Context.BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & MapBlocks.R_BORDER.get()) != 0) {
                    g2d.drawLine(x + Context.BLOCK_SIZE - 1, y, x + Context.BLOCK_SIZE - 1,
                            y + Context.BLOCK_SIZE - 1);
                }

                if ((screenData[i] & MapBlocks.D_BORDER.get()) != 0) {
                    g2d.drawLine(x, y + Context.BLOCK_SIZE - 1, x + Context.BLOCK_SIZE - 1,
                            y + Context.BLOCK_SIZE - 1);
                }

                if ((screenData[i] & MapBlocks.DOT.get()) != 0) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                if ((screenData[i] & MapBlocks.G_HOUSE.get()) != 0) {
                    g2d.setColor(new Color(121, 121, 121));
                    g2d.drawLine(x, y, x + Context.BLOCK_SIZE - 1, y);
                }

                i++;
            }
        }
    }

    private void paintPacman(Graphics2D g2d){

        if (pacman.getDirection().equals(Direction.LEFT)) {
            g2d.drawImage(Context.left, pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDirection().equals(Direction.RIGHT)) {
            g2d.drawImage(Context.right, pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDirection().equals(Direction.UP)) {
            g2d.drawImage(Context.up, pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDirection().equals(Direction.DOWN)){
            g2d.drawImage(Context.down, pacman.getX() + 1, pacman.getY() + 1, this);
        } else {
            g2d.drawImage(Context.pacman, pacman.getX() + 1, pacman.getY() + 1, this);
        }
    }

    private void paintGhosts(Graphics2D g2d){

        for(int i = 0; i < Context.N_GHOSTS; i++){
            g2d.drawImage(Context.ghost, ghosts[i].getX() + 1, ghosts[i].getY() + 1, this);
        }

    }


}
