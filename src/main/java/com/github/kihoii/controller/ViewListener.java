package com.github.kihoii.controller;

import com.github.kihoii.Main;
import com.github.kihoii.model.Model;
import com.github.kihoii.utils.enums.States;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CR: i would've expected ViewListener to be an interface and its logic to be inside of controller
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
                // CR: shouldn't we stop it first? not sure, please check 
                Main.timer.start();
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
