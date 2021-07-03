package com.github.kihoii.view.panels;

import com.github.kihoii.utils.CreateButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private final JLabel label = new JLabel("PACMAN");

    private final ActionListener actionListener;

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);

    public MenuPanel(ActionListener actionListener){
        this.actionListener = actionListener;
        creatMenuPanel();
    }

    private void creatMenuPanel(){

        JButton playButton = new CreateButton().create("START");
        playButton.setBounds(130, 150, 100, 50);
        playButton.addActionListener(actionListener);

        JButton scoreTableButton = new CreateButton().create("SCORES");
        scoreTableButton.setBounds(130, 220, 100, 50);
        scoreTableButton.setText("<html><center>SCORE<br>TABLE</center></html>");
        scoreTableButton.addActionListener(actionListener);

        JButton exitButton = new CreateButton().create("EXIT");
        exitButton.setBounds(130, 290, 100, 50);
        exitButton.addActionListener(actionListener);

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
