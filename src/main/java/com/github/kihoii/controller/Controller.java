package com.github.kihoii.controller;

import com.github.kihoii.model.Model;
import com.github.kihoii.utils.enums.Direction;
import com.github.kihoii.utils.enums.States;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener, ActionListener {

    private final Model model;

    private Direction d;

    public Controller(Model model) {
        this.model = model;
        d = Direction.DOWN;
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {}

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN -> {
                d = Direction.DOWN;
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

    public synchronized void handleTimerRequest() {
        model.movePacman(d);
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "EXIT" -> System.exit(0);
            case "START", "AGAIN" -> model.initNewModel();
            case "<html><center>SCORE<br>TABLE</center></html>" -> {
                model.setCurState(States.SCORES);
                model.notifyObservers();
            }
            case "PAUSE" -> {
                model.setCurState(States.PAUSE);
                model.notifyObservers();
            }
            case "RESUME" -> {
                model.setCurState(States.CONTINUE);
                model.notifyObservers();
            } case "MENU" ->{
                model.setCurState(States.MENU);
                model.notifyObservers();
            }
        }
    }
}
