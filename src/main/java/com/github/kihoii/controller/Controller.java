package com.github.kihoii.controller;

import com.github.kihoii.Main;
import com.github.kihoii.model.*;
import com.github.kihoii.utils.enums.*;

import java.awt.event.*;
import java.io.IOException;

public class Controller extends KeyAdapter implements ViewListener {

    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN ->
                model.getPacman().setDirection(Direction.DOWN);

            case KeyEvent.VK_LEFT ->
                model.getPacman().setDirection(Direction.LEFT);

            case KeyEvent.VK_RIGHT ->
                model.getPacman().setDirection(Direction.RIGHT);

            case KeyEvent.VK_UP ->
                model.getPacman().setDirection(Direction.UP);

        }
        try {
            model.movePacman();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public void handleTimerRequest() throws IOException {
        model.movePacman();
    }

    @Override
    public void onAction(ActionType actionType) throws IOException {
        switch (actionType) {
            case EXIT -> System.exit(0);
            case START -> {
                model.initNewModel();
                // CR: shouldn't we stop it first? not sure, please check
                // it starts with the game start and stops with the end
                Main.timer.start();
            }
            case SCORE -> model.notifyObservers(States.SCORES);
            case MENU -> model.notifyObservers(States.MENU);
            case RESUME -> model.notifyObservers(States.CONTINUE);
            case PAUSE -> model.notifyObservers(States.PAUSE);
        }
    }

    // CR: please use KeyAdapter instead of KeyListener

}
