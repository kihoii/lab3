package com.github.kihoii.view.panels;

import com.github.kihoii.utils.CreateButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PausePanel extends JPanel {


    private final JLabel label = new JLabel("PAUSE");

    private final ActionListener actionListener;

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);

    public PausePanel(ActionListener actionListener){
        this.actionListener = actionListener;
        creatPausePanel();
    }

    private void creatPausePanel(){

        JButton menuButton = new CreateButton().create("MENU");
        menuButton.setBounds(130, 150, 100, 50);
        menuButton.addActionListener(actionListener);

        JButton exitButton = new CreateButton().create("EXIT");
        exitButton.setBounds(130, 290, 100, 50);
        exitButton.addActionListener(actionListener);

        JButton continueButton = new CreateButton().create("RESUME");
        continueButton.setBounds(130, 220, 100, 50);
        continueButton.addActionListener(actionListener);

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
