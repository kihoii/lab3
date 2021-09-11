package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PausePanel extends JPanel {


    private final JLabel label = new JLabel("PAUSE");

    private final ViewListener myListener;

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);

    public PausePanel(ViewListener actionListener){
        this.myListener = actionListener;
        creatPausePanel();
    }

    private void creatPausePanel(){

        JButton menuButton = new MenuButton("MENU");
        menuButton.setBounds(130, 150, 100, 50);
        menuButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.MENU);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        JButton exitButton = new MenuButton("EXIT");
        exitButton.setBounds(130, 290, 100, 50);
        exitButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.EXIT);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        JButton continueButton = new MenuButton("RESUME");
        continueButton.setBounds(130, 220, 100, 50);
        continueButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.RESUME);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        label.setFont(menuFNT);
        label.setForeground(Color.YELLOW);
        label.setBounds(90, 50, 300, 50);

        this.add(label);
        this.add(continueButton);
        this.add(menuButton);
        this.add(exitButton);
        this.setBackground(Color.BLACK);
        this.setLayout(null);


    }
}
