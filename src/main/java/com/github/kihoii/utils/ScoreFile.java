package com.github.kihoii.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreFile {

    private static final File file = new File("src/main/resources/HighScores");

    // CR: this method should be non static and either return cached scores or extract them from file
    // CR: e.g.
    /*
    CR:
    int[] getScores() {
        if (scores != null) return scores;
        scores = read();
        if (scores == null) scores = createScores();
        return scores;
    }
     */
    public static Integer[] getScores(){
        Integer[] scores = new Integer[10];
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");
            int i = 0;
            while (scanner.hasNext()){
                scores[i] = scanner.nextInt();
                i++;
            }
            scanner.close();
        } catch (IOException e){
            for(int i = 0; i < 10; i++){
                scores[i] = 0;
            }
        }
        return scores;
    }

    // CR: this method shouldn't be available to the clients,
    // CR: save score should happen during ScoreFile#addScore(int)
    public static void saveScores(Integer[] scores){
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
