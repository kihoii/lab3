package com.github.kihoii;

import com.github.kihoii.controller.Controller;
import com.github.kihoii.model.Model;
import com.github.kihoii.view.View;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final File file = new File("src/main/resources/Map");

    public static final int WEIGHT = 15;
    public static final int HIGH = 17;

    private static Controller controller;
    public static Timer timer;

    public static void main(String[] args) throws FileNotFoundException {

        short[] map = new short[WEIGHT*HIGH];
        Scanner scan = new Scanner(file);

        for(int y = 0; y < WEIGHT*HIGH; y++) {
            scan.useDelimiter(", ");
            map[y] = scan.nextShort();
        }

        Model model = new Model(map);
        controller = new Controller(model);
        View view = new View(model, controller, map);
        model.addObserver(view);

        int TIMER_DELAY = 40;
        timer = new Timer(TIMER_DELAY, e -> {
            try {
                controller.handleTimerRequest();
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
    }

}
