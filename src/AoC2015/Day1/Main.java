package AoC2015.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("src/AoC2015/Day1/scanner.txt"))) {
            String data = scanner.nextLine();

            int floor = 0;
            int position = 0;
            boolean found = false;
            for (char c : data.toCharArray()) {
                position++;
                if (c == '(') {
                    floor++;
                } else if (c == ')') {
                    floor--;
                }
                if (floor == -1 && !found) {
                    found = true;
                    System.out.println("Basement: " + position);
                }
            }

            System.out.println("Final floor: " + floor);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}
