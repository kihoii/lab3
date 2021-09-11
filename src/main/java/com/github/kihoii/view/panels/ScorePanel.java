package com.github.kihoii.view.panels;

import com.github.kihoii.controller.ActionType;
import com.github.kihoii.controller.ViewListener;
import com.github.kihoii.utils.ScoreUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class ScorePanel extends JPanel {

    private final JLabel label = new JLabel("<html><center>HIGH<br>SCORES</center></html>");
    private final JLabel out = new JLabel();

    private final Font menuFNT = new Font("arial", Font.BOLD, 50);
    private final Font smallFNT = new Font("arial", Font.BOLD, 20);

    public ScorePanel(ViewListener actionListener) throws IOException {
        myListener = actionListener;
        ScoreUtils.getFirst();
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
        menuButton.addActionListener(e -> {
            try {
                myListener.onAction(ActionType.MENU);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Arrays.sort(ScoreUtils.scores, Collections.reverseOrder());

        out.setForeground(Color.YELLOW);
        out.setFont(smallFNT);
        out.setBounds(80, 80, 100, 400);
        out.setText("<html><left>1. " + ScoreUtils.scores[0]
                + "<br>2. " + ScoreUtils.scores[1]
                + "<br>3. " + ScoreUtils.scores[2]
                + "<br>4. " + ScoreUtils.scores[3]
                + "<br>5. " + ScoreUtils.scores[4]
                + "<br>6. " + ScoreUtils.scores[5]
                + "<br>7. " + ScoreUtils.scores[6]
                + "<br>8. " + ScoreUtils.scores[7]
                + "<br>9. " + ScoreUtils.scores[8]
                + "<br>10. " + ScoreUtils.scores[9] + "</left></html>");

        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.add(label);
        this.add(out);
        this.add(menuButton);
    }


}
