package com.github.kihoii.view;

import com.github.kihoii.Game;
import com.github.kihoii.controller.Controller;
import com.github.kihoii.model.Model;
import com.github.kihoii.utils.enums.States;
import com.github.kihoii.utils.observer.FieldUpdate;
import com.github.kihoii.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

public class View implements FieldUpdate {

    private final Model model;

    //private final ActionListener actionListener;
    private final Controller myListener;

    private final JFrame mainWindow;
    private final MenuPanel menuPanel;
    private GamePanel gamePanel;
    private final PausePanel pausePanel;
    private ScorePanel scorePanel;

    private JPanel curPanel;

    public View(Model model, Controller viewListener)  {
        this.model = model;
        //this.actionListener = actionListener;
        myListener = viewListener;

        menuPanel = new MenuPanel(myListener);
        pausePanel = new PausePanel(myListener);
        try{
            scorePanel = new ScorePanel(viewListener);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        curPanel = menuPanel;

        mainWindow = new JFrame("Pacman");
        mainWindow.getContentPane().setBackground(Color.BLACK);
        mainWindow.setSize(376, 500);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
       // mainWindow.addKeyListener((KeyListener) actionListener);
        mainWindow.addKeyListener((KeyListener) viewListener);
        mainWindow.add(curPanel);
        mainWindow.setResizable(false);
        mainWindow.setFocusable(true);
        mainWindow.setVisible(true);
    }



    @Override
    public void handleEvent(States state) {
        switch (state) {
            case MENU -> {
                mainWindow.remove(curPanel);
                curPanel = menuPanel;
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
            case START -> {
                System.out.println("Start");
                mainWindow.remove(curPanel);
                gamePanel = new GamePanel(myListener, model.getPacman(), model.getGhosts());
                curPanel = gamePanel;
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
            case IN_PROC ->
                    gamePanel.updateField(model.getScreenData(), model.getScore(),
                            model.getPacman(), model.getGhosts());
            case END -> {
                try{
                    scorePanel.update();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                }
                Game.timer.stop();
                mainWindow.remove(curPanel);
                curPanel = new EndPanel(myListener, model.getScore());
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
            case PAUSE -> {
                Game.timer.stop();
                mainWindow.remove(curPanel);
                curPanel = pausePanel;
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            } case CONTINUE -> {
                mainWindow.remove(curPanel);
                curPanel = gamePanel;
                mainWindow.add(curPanel);
                model.setCurState(States.IN_PROC);
                Game.timer.start();
                SwingUtilities.updateComponentTreeUI(mainWindow);
            } case SCORES -> {
                mainWindow.remove(curPanel);
                curPanel = scorePanel;
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
        }
    }

}
