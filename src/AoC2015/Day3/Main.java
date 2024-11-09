package AoC2015.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/AoC2015/Day3/input.txt");
            Scanner input = new Scanner(inputFile);

            String data = input.nextLine();
            Set<String> visitedHouses = new HashSet<>();

            int[] santaPos = {0, 0};
            int[] roboSantaPos = {0, 0};
            visitedHouses.add("0,0");

            boolean santaTurn = true;
            for (char c : data.toCharArray()) {
                int[] currentPos = santaTurn ? santaPos : roboSantaPos;

                switch (c) {
                    case '^' -> currentPos[1]--;
                    case 'v' -> currentPos[1]++;
                    case '<' -> currentPos[0]--;
                    case '>' -> currentPos[0]++;
                }

                visitedHouses.add(currentPos[0] + "," + currentPos[1]);
                santaTurn = !santaTurn;
            }

            System.out.println(visitedHouses.size());

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}
