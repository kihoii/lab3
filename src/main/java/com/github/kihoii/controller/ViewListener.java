package com.github.kihoii.controller;

import com.github.kihoii.Game;
import com.github.kihoii.model.Model;
import com.github.kihoii.utils.enums.States;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewListener {

    private Model model;

    public ViewListener(Model model){
        this.model = model;
    }

    public void onAction(ActionType actionType) {
        switch (actionType) {
            case EXIT -> System.exit(0);
            case START -> {
                model.initNewModel();
                Game.timer.start();
            }
            case SCORE ->
                    model.setCurState(States.SCORES);
            case MENU ->
                    model.setCurState(States.MENU);
            case RESUME ->
                    model.setCurState(States.CONTINUE);
            case PAUSE ->
                    model.setCurState(States.PAUSE);
        }
    }


}
