package com.github.kihoii.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ScoreFile {

    private static final File file = new File("src/main/resources/HighScores");

    private Integer[] scores = new Integer[10];


    public Integer[] getScores(){
        if (scores == null) {
            try {
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("\n");
                int i = 0;
                while (scanner.hasNext()) {
                    scores[i] = scanner.nextInt();
                    i++;
                }
                scanner.close();
            } catch (IOException e) {
                for (int i = 0; i < 10; i++) {
                    scores[i] = 0;
                }
            }
        }
        return scores;
    }

    public void addScore(int score){
        if(score >= scores[9]) {
            scores[9] = score;
            Arrays.sort(scores, Collections.reverseOrder());
            saveScores();
        }
    }

    private void saveScores(){
        StringBuilder total = new StringBuilder();

        for(int i = 0; i < 10; i++){
            total.append(scores[i]).append("\n");
        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(total.toString());
            fw.close();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}
