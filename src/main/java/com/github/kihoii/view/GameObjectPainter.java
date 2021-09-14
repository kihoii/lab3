package com.github.kihoii.view;

import com.github.kihoii.model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public class GameObjectPainter implements GameObjectVisitor {

    public final Image down = new ImageIcon("pics/down.gif").getImage();
    public final Image up = new ImageIcon("pics/up.gif").getImage();
    public final Image left = new ImageIcon("pics/left.gif").getImage();
    public final Image right = new ImageIcon("pics/right.gif").getImage();
    public final Image pacmanIm = new ImageIcon("pics/pacman.png").getImage();

    public final Image ghost = new ImageIcon("pics/ghost.gif").getImage();

    private final Graphics2D g2d;
    private final ImageObserver im;

    public GameObjectPainter(Graphics2D g2d, List<GameObject> field, ImageObserver im) {
        this.g2d = g2d;
        this.im = im;

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
