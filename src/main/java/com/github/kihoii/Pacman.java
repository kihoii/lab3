package com.github.kihoii;

import com.github.kihoii.controller.Controller;
import com.github.kihoii.model.Model;
import com.github.kihoii.utils.constants.Context;
import com.github.kihoii.view.View;

import javax.swing.*;

public class Pacman implements Runnable {

    private static Pacman instance;

    private static Controller controller;
    public static Timer timer;

    private Pacman(){}

    public static Pacman getInstance(){
        if (instance == null) {
            instance = new Pacman();
        }
        return instance;
    }

    @Override
    public void run() {

        Model model = new Model();
        controller = new Controller(model);
        View view = new View(model, controller);
        model.addObserver(view);

        timer = new Timer(Context.TIMER_DELAY, e -> controller.handleTimerRequest());
        //timer.start();


    }
}
