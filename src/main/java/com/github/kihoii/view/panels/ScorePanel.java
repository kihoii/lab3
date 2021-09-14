package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.ViewListener;
import com.github.kihoii.utils.ScoreFile;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.*;

public class ScorePanel extends JPanel {

    private static Integer[] scores = new Integer[10];

    private final JLabel label = new JLabel("<html><center>HIGH<br>SCORES</center></html>");
    private final JLabel out = new JLabel();

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);
    private final Font smallFNT = new Font("arial", Font.BOLD, 20);

    public ScorePanel(ViewListener actionListener) throws IOException {
        myListener = actionListener;
        scores = ScoreFile.getScores();
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

        out.setForeground(Color.YELLOW);
        out.setFont(smallFNT);
        out.setBounds(80, 80, 100, 400);

        setScores();

        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.add(label);
        this.add(out);
        this.add(menuButton);
    }

    public void setScores(){
        String text = IntStream.range(0, 10)
                .mapToObj(i -> String.format("<br>%d. %d", i + 1, scores[i]))
                .collect(Collectors.joining("\n", "<html><left>", "</left></html>"));
        out.setText(text);
    }

    public void updateScores(int score){
        if(score >= scores[9]) {
            scores[9] = score;
            Arrays.sort(scores, Collections.reverseOrder());
            setScores();
        }
    }

    public Integer[] getScores(){
        return scores;
    }


}
