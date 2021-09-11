package com.github.kihoii.controller;

import com.github.kihoii.model.*;
import com.github.kihoii.utils.enums.*;

import java.awt.event.*;

public class Controller extends ViewListener implements KeyListener {

    private final Model model;

    // CR: can be a local variable, not a field
    private Direction d;

    public Controller(Model model) {
        super(model);
        this.model = model;
        d = Direction.NONE;
    }


    @Override
    public synchronized void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> {
                d = Direction.DOWN;
                // CR: can extract from switch
                model.movePacman(d);
            }
            case KeyEvent.VK_LEFT -> {
                d = Direction.LEFT;
                model.movePacman(d);
            }
            case KeyEvent.VK_RIGHT -> {
                d = Direction.RIGHT;
                model.movePacman(d);
            }
            case KeyEvent.VK_UP -> {
                d = Direction.UP;
                model.movePacman(d);
            }
        }
    }

    // CR: why synchronized?
    public synchronized void handleTimerRequest() {
        // CR: can just call movePacman with no args, model already has the knowledge about pacman's direction 
        model.movePacman(model.getPacman().getDirection());
    }

    // CR: please use KeyAdapter instead of KeyListener
    @Override
    public synchronized void keyReleased(KeyEvent e) {
    }


//    @Override
//    public synchronized void actionPerformed(ActionEvent e) {
//        switch (e.getActionCommand()) {
//            case "EXIT" -> System.exit(0);
//            case "START", "AGAIN" -> {
//                model.initNewModel();
//                model.notifyObservers();
//                Game.timer.start();
//            }
//            case "<html><center>SCORE<br>TABLE</center></html>" -> {
//                //view.showScores();
//                model.setCurState(States.SCORES);
//                model.notifyObservers();
//            }
//            case "PAUSE" -> {
//                model.setCurState(States.PAUSE);
//                model.notifyObservers();
//            }
//            case "RESUME" -> {
//                model.setCurState(States.CONTINUE);
//                model.notifyObservers();
//            } case "MENU" ->{
//                model.setCurState(States.MENU);
//                model.notifyObservers();
//            }
//        }
//    }
}
