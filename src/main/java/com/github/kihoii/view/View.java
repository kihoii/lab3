package com.github.kihoii.view;

import com.github.kihoii.Main;
import com.github.kihoii.controller.Controller;
import com.github.kihoii.utils.ScoreFile;
import com.github.kihoii.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class View {

    private final Controller myListener;

    private final JFrame mainWindow;
    private final MenuPanel menuPanel;
    private GamePanel gamePanel;
    private final PausePanel pausePanel;
    private ScorePanel scorePanel;

    private ScoreFile scoreFile;

    private JPanel curPanel;

    private final short[] map;

    public View(Controller viewListener, short[] map)  {
        scoreFile = new ScoreFile();

        this.map = map;

        myListener = viewListener;

        menuPanel = new MenuPanel(myListener);
        pausePanel = new PausePanel(myListener);
        try{
            scorePanel = new ScorePanel(viewListener, scoreFile.getScores());
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

    public void updateField(List<GameObject> field, int score, int lives){
        gamePanel.updateField(field, score, lives);
    }

    public void startGame(List<GameObject> field, int score, int lives){
        mainWindow.remove(curPanel);
        gamePanel = new GamePanel(myListener, field, map, score, lives);
        curPanel = gamePanel;
        mainWindow.add(curPanel);

        SwingUtilities.updateComponentTreeUI(mainWindow);
    }

    public void openMenu(){
        mainWindow.remove(curPanel);
        curPanel = menuPanel;
        mainWindow.add(curPanel);
        SwingUtilities.updateComponentTreeUI(mainWindow);
    }

    public void setPause(){
        Main.timer.stop();
        mainWindow.remove(curPanel);
        curPanel = pausePanel;
        mainWindow.add(curPanel);
        SwingUtilities.updateComponentTreeUI(mainWindow);
    }

    public void endGame(int score){
        scoreFile.addScore(score);
        scorePanel.update(scoreFile.getScores());
        Main.timer.stop();
        mainWindow.remove(curPanel);
        curPanel = new EndPanel(myListener, score);
        mainWindow.add(curPanel);
        SwingUtilities.updateComponentTreeUI(mainWindow);
    }

    public void continueGame(){
        mainWindow.remove(curPanel);
        curPanel = gamePanel;
        mainWindow.add(curPanel);
        Main.timer.start();
        SwingUtilities.updateComponentTreeUI(mainWindow);
    }

    public void showScores(){
        mainWindow.remove(curPanel);
        //scorePanel.updateScores(ScoreUtils.getScores());
        curPanel = scorePanel;
        mainWindow.add(curPanel);
        SwingUtilities.updateComponentTreeUI(mainWindow);
    }

    public void exitGame(){
        //ScoreFile.saveScores(scorePanel.getScores());
        System.exit(0);
    }

}
