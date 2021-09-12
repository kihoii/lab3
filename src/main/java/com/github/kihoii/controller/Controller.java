package com.github.kihoii.controller;

import com.github.kihoii.Main;
import com.github.kihoii.model.*;
import com.github.kihoii.utils.observer.FieldUpdate;
import com.github.kihoii.utils.observer.Observable;

import java.awt.event.*;

public class Controller extends KeyAdapter implements ViewListener, Observable {

    private final Model model;

    private FieldUpdate fieldUpdate;

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
        move();
    }

    private void move(){
        if(model.movePacman()){
            notifyObservers(States.IN_PROC);
        } else {
            System.out.println("not alive");
            notifyObservers(States.END);
        }
    }

    public void handleTimerRequest(){
        move();
    }

    @Override
    public void onAction(ActionType actionType){
        switch (actionType) {
            case EXIT -> System.exit(0);
            case START -> {
                model.initNewModel();
                notifyObservers(States.START);
                Main.timer.start();
            }
            case SCORE -> notifyObservers(States.SCORES);
            case MENU -> notifyObservers(States.MENU);
            case RESUME -> notifyObservers(States.CONTINUE);
            case PAUSE -> notifyObservers(States.PAUSE);
        }
    }

    @Override
    public void addObserver(FieldUpdate fieldUpdate) {
        this.fieldUpdate = fieldUpdate;
    }

    @Override
    public void notifyObservers(States state) {
        fieldUpdate.handleEvent(state);
    }
}
