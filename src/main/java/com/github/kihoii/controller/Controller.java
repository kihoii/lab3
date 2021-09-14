package com.github.kihoii.controller;

import com.github.kihoii.Main;
import com.github.kihoii.model.*;
import com.github.kihoii.utils.Observable;
import com.github.kihoii.view.View;

import java.awt.event.*;

public class Controller extends KeyAdapter implements ViewListener, Observable {

    private final Model model;

    private View fieldUpdate;

    public Controller(Model model) {
        this.model = model;
    }

    public void keyPressed(KeyEvent e) {
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

    private void move(){
        if(model.move()){
            fieldUpdate.updateField(model.getField(), model.getScore(), model.getLives());
        } else {
            fieldUpdate.endGame(model.getScore());
        }
    }

    public void handleTimerRequest(){
        move();
    }

    @Override
    public void onAction(ActionType actionType){
        switch (actionType) {
            case EXIT -> fieldUpdate.exitGame();
            case START -> {
                model.initNewModel();
                fieldUpdate.startGame(model.getField());
                Main.timer.start();
            }
            case SCORE -> fieldUpdate.showScores();
            case MENU -> fieldUpdate.openMenu();
            case RESUME -> fieldUpdate.continueGame();
            case PAUSE -> fieldUpdate.setPause();
        }
    }

    @Override
    public void addObserver(View fieldUpdate) {
        this.fieldUpdate = fieldUpdate;
    }

}
