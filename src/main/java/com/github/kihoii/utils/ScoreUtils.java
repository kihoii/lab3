package com.github.kihoii.utils;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ScoreUtils {

    private static final File file = new File("src/main/resources/HighScores");
    public static Integer[] scores = new Integer[10];

    /*
    CR: i'd expect something like this
    static class ScoreFile {
        public int[] getScores() {...}
        public int addScore(int score) {...}
    }
    CR: this class would encapsulate all logic related to working with score file
     */

    //it actually does all logic with file


    public static void getFirst(){
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
            //System.err.println(e.getMessage());
            for(int i = 0; i < 10; i++){
                scores[i] = 0;
            }
        }

    }

    public static void scoreUpdate(int score) {

        StringBuilder total = new StringBuilder();

        if(score >= scores[9]) {
            scores[9] = score;
            Arrays.sort(ScoreUtils.scores, Collections.reverseOrder());
        }

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
