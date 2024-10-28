package AoC2015.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/AoC2015/Day3/input.txt");
            Scanner input = new Scanner(inputFile);

            String data = input.nextLine();
            int dataLength = data.length() * 2;

            boolean[][] houses = new boolean[data.length()*2][data.length()*2];
            for (int i = 0; i < dataLength; i++) {
                for (int j = 0; j < dataLength; j++) {
                    houses[i][j] = false;
                }
            }
            int[] santaPos = {data.length(), data.length()};
            int[] roboSantaPos = {data.length(), data.length()};
            houses[santaPos[0]][santaPos[1]] = true;

            boolean santaTurn = true;
            for (char c : data.toCharArray()) {
                if (c == '^') {
                    if (santaTurn) {
                        santaTurn = false;
                        santaPos[1] = santaPos[1] - 1;
                    } else {
                        santaTurn = true;
                        roboSantaPos[1] = roboSantaPos[1] - 1;
                    }
                } else if (c == 'v') {
                    if (santaTurn) {
                        santaTurn = false;
                        santaPos[1] = santaPos[1] + 1;
                    } else {
                        santaTurn = true;
                        roboSantaPos[1] = roboSantaPos[1] + 1;
                    }
                } else if (c == '<') {
                    if (santaTurn) {
                        santaTurn = false;
                        santaPos[0] = santaPos[0] - 1;
                    } else {
                        santaTurn = true;
                        roboSantaPos[0] = roboSantaPos[0] - 1;
                    }
                } else if (c == '>') {
                    if (santaTurn) {
                        santaTurn = false;
                        santaPos[0] = santaPos[0] + 1;
                    } else {
                        santaTurn = true;
                        roboSantaPos[0] = roboSantaPos[0] + 1;
                    }
                }
                houses[santaPos[0]][santaPos[1]] = true;
                houses[roboSantaPos[0]][roboSantaPos[1]] = true;
            }

            int visitedHouses = 0;
            for (boolean[] house : houses) {
                for (int j = 0; j < houses.length; j++) {
                    if (house[j]) {
                        visitedHouses++;
                    }
                }
            }

            System.out.println(visitedHouses);

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}
