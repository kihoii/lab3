package com.github.kihoii.view.panels;

import com.github.kihoii.utils.CreateButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndPanel extends JPanel {

    private final JLabel label = new JLabel("YOU LOSE");
    private final JLabel scores = new JLabel();

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);
    private final Font smallFNT = new Font("arial", Font.BOLD, 20);

    private final int score;

    private final ActionListener actionListener;

    public EndPanel(ActionListener actionListener, int score){
        this.actionListener = actionListener;
        this.score = score;
        createEndPanel();
    }

    private void createEndPanel(){
        JButton menuButton = new CreateButton().create("MENU");
        menuButton.setBounds(80, 220, 100, 50);
        menuButton.addActionListener(actionListener);

        JButton continueButton = new CreateButton().create("AGAIN");
        continueButton.setBounds(190, 220, 100, 50);
        continueButton.addActionListener(actionListener);

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
