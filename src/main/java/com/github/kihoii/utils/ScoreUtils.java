package com.github.kihoii.utils;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ScoreUtils {

    private static final File file = new File("src/main/resources/HighScores");
    public static Integer[] scores = new Integer[10];

    public static void getFirst() throws IOException {

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\n");
        int i = 0;
        while (scanner.hasNext()){
            scores[i] = scanner.nextInt();
            i++;
        }
        scanner.close();
    }

    public static void scoreUpdate(int score) throws IOException {

        StringBuilder total = new StringBuilder();

        if(score >= scores[9]) {
            scores[9] = score;
            Arrays.sort(ScoreUtils.scores, Collections.reverseOrder());
        }

        for(int i = 0; i < 10; i++){
            total.append(scores[i]).append("\n");
        }

        FileWriter fw = new FileWriter(file);
        fw.write(total.toString());
        fw.close();

    }
}
