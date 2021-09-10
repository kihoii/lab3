package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.Controller;
import com.github.kihoii.controller.ViewListener;
import com.github.kihoii.utils.constants.Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class ScorePanel extends JPanel {
    private final JLabel label = new JLabel("<html><center>HIGH<br>SCORES</center></html>");
    private final JLabel out = new JLabel();

    //private final ActionListener actionListener;

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);
    private final Font smallFNT = new Font("arial", Font.BOLD, 20);

    public ScorePanel(ViewListener actionListener) throws IOException {
        myListener = actionListener;
        getFirst();
        createScorePanel();
    }

    public void update() throws IOException {
        if (Context.file.delete()) {
            if(!Context.file.createNewFile()) System.out.println("File wasn't created");
        }
        FileWriter fileWriter = new FileWriter(Context.file);
        for (int k = 0; k < 10; k++) {
            fileWriter.write(Context.scores[k].toString());
            if(k != 9)fileWriter.append('\n');
        }
        fileWriter.flush();
        createScorePanel();
    }

    private final ViewListener myListener;

    private void createScorePanel() {
        label.setFont(menuFNT);
        label.setForeground(Color.YELLOW);
        label.setBounds(70, 20, 300, 110);

        JButton menuButton = new JButton("MENU");
        menuButton.setFont(smallFNT);
        menuButton.setForeground(Color.YELLOW);
        menuButton.setBackground(Color.BLACK);
        menuButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        menuButton.setSize(new Dimension(100,50));
        menuButton.setBounds(240, 400, 100, 50);
        menuButton.addActionListener(e -> myListener.onAction(ActionType.MENU));
        //menuButton.addActionListener(actionListener);

        Arrays.sort(Context.scores, Collections.reverseOrder());

        out.setForeground(Color.YELLOW);
        out.setFont(smallFNT);
        out.setBounds(80, 80, 100, 400);
        out.setText("<html><left>1. " + Context.scores[0]
                + "<br>2. " + Context.scores[1]
                + "<br>3. " + Context.scores[2]
                + "<br>4. " + Context.scores[3]
                + "<br>5. " + Context.scores[4]
                + "<br>6. " + Context.scores[5]
                + "<br>7. " + Context.scores[6]
                + "<br>8. " + Context.scores[7]
                + "<br>9. " + Context.scores[8]
                + "<br>10. " + Context.scores[9] + "</left></html>");

        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.add(label);
        this.add(out);
        this.add(menuButton);
    }

    private void getFirst() throws IOException {
        FileReader fileReader = new FileReader(Context.file);
        int c;
        StringBuilder number = new StringBuilder();
        int i = 0;
        while((c = fileReader.read()) != -1){
            if (c == '\n') {
                Context.scores[i] = Integer.parseInt(number.toString());
                i++;
                number = new StringBuilder();
            } else number.append((char)c);
        }
        Context.scores[i] = Integer.parseInt(number.toString());
    }
}
