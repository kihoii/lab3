package com.github.kihoii.view.panels;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String name){

        Font buttonFNT = new Font("arial", Font.BOLD, 20);
        this.setFont(buttonFNT);
        this.setText(name);
        this.setForeground(Color.YELLOW);
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        this.setSize(new Dimension(100,50));

    }
}
