package com.github.kihoii.view;

import com.github.kihoii.Main;
import com.github.kihoii.controller.Controller;
import com.github.kihoii.model.Model;
import com.github.kihoii.utils.ScoreUtils;
import com.github.kihoii.utils.enums.States;
import com.github.kihoii.utils.observer.FieldUpdate;
import com.github.kihoii.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class View implements FieldUpdate {

    private final Model model;

    private final Controller myListener;

    private final JFrame mainWindow;
    private final MenuPanel menuPanel;
    private GamePanel gamePanel;
    private final PausePanel pausePanel;
    private ScorePanel scorePanel;

    private JPanel curPanel;

    private final short[] map;

    public View(Model model, Controller viewListener, short[] map)  {
        this.model = model;
        this.map = map;

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
        mainWindow.addKeyListener(viewListener);
        mainWindow.add(curPanel);
        mainWindow.setResizable(false);
        mainWindow.setFocusable(true);
        mainWindow.setVisible(true);
    }



    @Override
    public void handleEvent(States state) throws IOException {
        switch (state) {
            case MENU -> {
                mainWindow.remove(curPanel);
                curPanel = menuPanel;
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
            case START -> {
                mainWindow.remove(curPanel);
                gamePanel = new GamePanel(myListener, model.getPacman(), model.getGhosts(), map);
                curPanel = gamePanel;
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
            case IN_PROC ->
                    gamePanel.updateField(model.getScreenData(), model.getPacman().getScore(),
                            model.getPacman(), model.getGhosts());
            case END -> {

                ScoreUtils.scoreUpdate(model.getPacman().getScore());
                Main.timer.stop();
                mainWindow.remove(curPanel);
                curPanel = new EndPanel(myListener, model.getPacman().getScore());
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            }
            case PAUSE -> {
                Main.timer.stop();
                mainWindow.remove(curPanel);
                curPanel = pausePanel;
                mainWindow.add(curPanel);
                SwingUtilities.updateComponentTreeUI(mainWindow);
            } case CONTINUE -> {
                mainWindow.remove(curPanel);
                curPanel = gamePanel;
                mainWindow.add(curPanel);
                //model.setCurState(States.IN_PROC);
                Main.timer.start();
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