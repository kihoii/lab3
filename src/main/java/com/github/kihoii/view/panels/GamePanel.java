package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.ViewListener;
import com.github.kihoii.model.ghosts.Ghost;
import com.github.kihoii.model.pacman.Pacman;
import com.github.kihoii.model.Direction;
import com.github.kihoii.utils.enums.MapBlock;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {



    public final int WEIGHT = 15;
    public final int HIGH = 17;
    public final int BLOCK_SIZE = 24;
    public final int SCREEN_X = WEIGHT * BLOCK_SIZE;
    public final int SCREEN_Y = HIGH * BLOCK_SIZE;


    public final Image down = new ImageIcon("pics/down.gif").getImage();
    public final Image up = new ImageIcon("pics/up.gif").getImage();
    public final Image left = new ImageIcon("pics/left.gif").getImage();
    public final Image right = new ImageIcon("pics/right.gif").getImage();
    public final Image pacmanIm = new ImageIcon("pics/pacman.png").getImage();

    public final Image ghost = new ImageIcon("pics/ghost.gif").getImage();
    public final Image heart = new ImageIcon("pics/heart.png").getImage();


    private short[] screenData;


    private final Font scoreFNT = new Font("arial", Font.BOLD, 15);
    private int score;

    int lives;

    // CR: view shouldn't know anything about model objects
    //tbh idk how to reorg it
    private Pacman pacman;
    private Ghost[] ghosts;

    private final short[] map;

    public GamePanel(ViewListener myListener, Pacman pacman, Ghost[] ghosts, short[] map) {
        this.map = map;
        screenData = map;

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
        pauseButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.PAUSE);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

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
            g2d.drawImage(heart, 330 - 30*i, 413, this);
        }

        g.setFont(scoreFNT);
        g.setColor(Color.YELLOW);
        String s = "Score: " + score;
        g.drawString(s, 376/2+90, 450);

    }

    private void paintMap(Graphics2D g2d){

        short i = 0;
        for(int y = 0; y < SCREEN_Y; y += BLOCK_SIZE){
            for(int x = 0; x < SCREEN_X; x += BLOCK_SIZE){
                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(1));

                if ((map[i] == 0)) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }

                if ((MapBlock.L_BORDER.get(screenData[i])) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((MapBlock.UP_BORDER.get(screenData[i])) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((MapBlock.R_BORDER.get(screenData[i])) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((MapBlock.D_BORDER.get(screenData[i])) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((MapBlock.DOT.get(screenData[i])) != 0) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                if ((MapBlock.G_HOUSE.get(screenData[i])) != 0) {
                    g2d.setColor(new Color(121, 121, 121));
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                i++;
            }
        }
    }

    private void paintPacman(Graphics2D g2d){

        if (pacman.getDirection().equals(Direction.LEFT)) {
            g2d.drawImage(left, pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDirection().equals(Direction.RIGHT)) {
            g2d.drawImage(right, pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDirection().equals(Direction.UP)) {
            g2d.drawImage(up, pacman.getX() + 1, pacman.getY() + 1, this);
        } else if (pacman.getDirection().equals(Direction.DOWN)){
            g2d.drawImage(down, pacman.getX() + 1, pacman.getY() + 1, this);
        } else {
            g2d.drawImage(pacmanIm, pacman.getX() + 1, pacman.getY() + 1, this);
        }
    }

    private void paintGhosts(Graphics2D g2d){

        for(int i = 0; i < 4; i++){
            g2d.drawImage(ghost, ghosts[i].getX() + 1, ghosts[i].getY() + 1, this);
        }

    }


}
