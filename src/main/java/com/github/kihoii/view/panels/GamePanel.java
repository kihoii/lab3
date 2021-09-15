package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.ViewListener;
import com.github.kihoii.utils.MapBlock;
import com.github.kihoii.view.GameObject;
import com.github.kihoii.view.GameObjectPainter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {

    private static final int WIDTH = 15;
    private static final int HEIGHT = 17;
    private static final int BLOCK_SIZE = 24;
    private static final int SCREEN_X = WIDTH * BLOCK_SIZE;
    private static final int SCREEN_Y = HEIGHT * BLOCK_SIZE;

    private final Image heart = new ImageIcon("pics/heart.png").getImage();

    private final Font scoreFNT = new Font("arial", Font.BOLD, 15);
    private int score;

    int lives;

    private final short[] map;
    private List<GameObject> field;

    public GamePanel(ViewListener myListener, List<GameObject> field, short[] map, int score, int lives) {
        this.map = map;
        this.field = field;
        this.setPreferredSize(new Dimension(376,500));

        this.score = score;
        this.lives = lives;

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

    public void updateField(List<GameObject> field, int score, int lives){
        this.field = field;
        this.score = score;
        this.lives = lives;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 376, 500);

        paintMap(g2d);
        GameObjectPainter painter = new GameObjectPainter(g2d, this);
        field.forEach(GameObject -> GameObject.accept(painter));

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

                if ((MapBlock.L_BORDER.is(map[i]))) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((MapBlock.UP_BORDER.is(map[i]))) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((MapBlock.R_BORDER.is(map[i]))) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((MapBlock.D_BORDER.is(map[i]))) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((MapBlock.G_HOUSE.is(map[i]))) {
                    g2d.setColor(new Color(121, 121, 121));
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                i++;
            }
        }
    }

}
