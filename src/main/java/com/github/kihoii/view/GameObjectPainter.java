package com.github.kihoii.view;

import com.github.kihoii.model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public class GameObjectPainter implements GameObjectVisitor {

    // CR: naming
    private static final Image down = new ImageIcon("pics/down.gif").getImage();
    private static final Image up = new ImageIcon("pics/up.gif").getImage();
    private static final Image left = new ImageIcon("pics/left.gif").getImage();
    private static final Image right = new ImageIcon("pics/right.gif").getImage();
    private static final Image pacmanIm = new ImageIcon("pics/pacman.png").getImage();

    private static final Image ghost = new ImageIcon("pics/ghost.gif").getImage();

    private final Graphics2D g2d;
    private final ImageObserver im;

    public GameObjectPainter(Graphics2D g2d, List<GameObject> field, ImageObserver im) {
        this.g2d = g2d;
        this.im = im;

        // CR: do it in separate method
        /*
        CR: 
        static void paint(Graphics2D g2d, List<GameObject> field, ImageObserver im) {
          GameObjectPainter painter = ...;
          field.forEach(go -> go.accept(this));
        }
         */
        for (GameObject gameObject : field) {
            gameObject.accept(this);
        }
    }

    @Override
    public void visit(GameObject.Pacman pacman) {
        paintPacman(pacman.getX(), pacman.getY(), pacman.getDirection());
    }

    private void paintPacman(int x, int y, Direction d){
        if (d.equals(Direction.LEFT)) {
            g2d.drawImage(left, x + 1, y + 1, im);
        } else if (d.equals(Direction.RIGHT)) {
            g2d.drawImage(right,x + 1, y + 1, im);
        } else if (d.equals(Direction.UP)) {
            g2d.drawImage(up, x + 1, y + 1, im);
        } else if (d.equals(Direction.DOWN)){
            g2d.drawImage(down, x + 1, y + 1, im);
        } else {
            g2d.drawImage(pacmanIm, x + 1, y + 1, im);
        }
    }

    @Override
    public void visit(GameObject.Ghost ghost) {
        paintGhosts(ghost.getX(), ghost.getY());
    }

    private void paintGhosts(int x, int y){
        g2d.drawImage(ghost, x + 1, y + 1, im);
    }

    @Override
    public void visit(GameObject.Point point) {
        paintPoint(point.getX(), point.getY());
    }

    private void paintPoint(int x, int y){
        g2d.setColor(new Color(255,255,255));
        g2d.fillOval(x + 10, y + 10, 6, 6);
    }

}
