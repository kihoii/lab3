package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EndPanel extends JPanel {

    private final JLabel label = new JLabel("YOU LOSE");
    private final JLabel scores = new JLabel();

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);
    private final Font smallFNT = new Font("arial", Font.BOLD, 20);

    private final int score;

    private final ViewListener myListener;

    public EndPanel(ViewListener actionListener, int score){
        this.myListener = actionListener;
        this.score = score;
        createEndPanel();
    }

    private void createEndPanel(){
        JButton menuButton = new MenuButton("MENU");
        menuButton.setBounds(80, 220, 100, 50);
        menuButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.MENU);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        JButton continueButton = new MenuButton("AGAIN");
        continueButton.setBounds(190, 220, 100, 50);
        continueButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.START);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        label.setFont(menuFNT);
        label.setForeground(Color.YELLOW);
        label.setBounds(50, 70, 300, 50);

        scores.setText("Your score: " + score);
        scores.setFont(smallFNT);
        scores.setForeground(Color.YELLOW);
        scores.setBounds(110, 130, 300,50);

        this.add(label);
        this.add(scores);
        this.add(menuButton);
        this.add(continueButton);

        this.setBackground(Color.BLACK);
        this.setLayout(null);
    }

}
