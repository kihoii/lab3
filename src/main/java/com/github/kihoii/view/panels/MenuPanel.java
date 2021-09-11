package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private final JLabel label = new JLabel("PACMAN");

    private final ViewListener myListener;

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);

    public MenuPanel(ViewListener actionListener){
        myListener = actionListener;
        creatMenuPanel();
    }

    private void creatMenuPanel(){

        JButton playButton = new MenuButton("START");
        playButton.setBounds(130, 150, 100, 50);
        playButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.START);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        JButton scoreTableButton = new MenuButton("<html><center>SCORE<br>TABLE</center></html>");
        scoreTableButton.setBounds(130, 220, 100, 50);
        // CR: i think text can be aligned without writing raw html
        // i didn't find the way
        scoreTableButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.SCORE);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        JButton exitButton = new MenuButton("EXIT");
        exitButton.setBounds(130, 290, 100, 50);
        exitButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.EXIT);
            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        label.setFont(menuFNT);
        label.setForeground(Color.YELLOW);
        label.setBounds(70, 50, 300, 50);

        this.add(label);
        this.add(scoreTableButton);
        this.add(playButton);
        this.add(exitButton);
        this.setBackground(Color.BLACK);
        this.setLayout(null);

    }

}
