package com.github.kihoii;

import com.github.kihoii.controller.Controller;
import com.github.kihoii.model.Model;
import com.github.kihoii.view.View;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final File file = new File("src/main/resources/Map");

    public static final int WIDTH = 15;
    public static final int HEIGHT = 17;
    private static final int TIMER_DELAY = 40;

    private static Controller controller;
    public static Timer timer;

    public static void main(String[] args) {
        short[] map = getMap();

        Model model = new Model(map);
        controller = new Controller(model);
        View view = new View(controller, map);
        controller.setView(view);

        timer = new Timer(TIMER_DELAY, e -> controller.move());
    }

    private static short[] getMap(){
        short[] map = new short[WIDTH * HEIGHT];
        try {
            Scanner scan = new Scanner(file);
            for(int y = 0; y < WIDTH * HEIGHT; y++) {
                scan.useDelimiter(", ");
                map[y] = scan.nextShort();
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return map;
    }

}
