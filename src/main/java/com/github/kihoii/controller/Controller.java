package com.github.kihoii.controller;

import com.github.kihoii.Main;
import com.github.kihoii.model.*;
import com.github.kihoii.view.View;

import java.awt.event.*;

public class Controller extends KeyAdapter implements ViewListener {

    private final Model model;

    private View view;

    private enum State {STARTED, STOPPED}
    
    private State state = State.STOPPED;

    public Controller(Model model) {
        this.model = model;
    }

    public void keyPressed(KeyEvent e) {
        if (state == State.STOPPED) return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN ->
                model.setDirection(Direction.DOWN);

            case KeyEvent.VK_LEFT ->
                model.setDirection(Direction.LEFT);

            case KeyEvent.VK_RIGHT ->
                model.setDirection(Direction.RIGHT);

            case KeyEvent.VK_UP ->
                model.setDirection(Direction.UP);

        }
        move();
    }

    public void move(){
        if(model.move()){
            view.updateField(model.getField(), model.getScore(), model.getLives());
        } else {
            view.endGame(model.getScore());
        }
    }

    @Override
    public void onAction(ActionType actionType){
        state = actionType == ActionType.START || actionType == ActionType.RESUME ? State.STARTED : State.STOPPED;
        switch (actionType) {
            case EXIT -> System.exit(0);
            case START -> {
                model.initNewModel();
                view.startGame(model.getField(), model.getScore(), model.getLives());
                Main.timer.start();
            }
            case SCORE -> view.showScores();
            case MENU -> view.openMenu();
            case RESUME -> view.continueGame();
            case PAUSE -> view.setPause();
        }
    }

    public void setView(View view) {
        this.view = view;
    }

}
