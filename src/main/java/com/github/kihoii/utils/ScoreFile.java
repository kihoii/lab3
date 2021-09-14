package com.github.kihoii.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreFile {

    private static final File file = new File("src/main/resources/HighScores");

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
