package com.github.kihoii.utils;

import javax.swing.*;
import java.awt.*;

public class CreateButton {

    private final Font buttonFNT = new Font("arial", Font.BOLD, 20);

    public JButton create(String name){
        JButton button = new JButton(name);

        button.setFont(buttonFNT);
        button.setForeground(Color.YELLOW);
        button.setBackground(Color.BLACK);
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        button.setSize(new Dimension(100,50));

        return button;
    }
    

}
