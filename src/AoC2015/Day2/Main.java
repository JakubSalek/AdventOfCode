package AoC2015.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("src/AoC2015/Day2/scanner.txt"))) {
            int paperSum = 0;
            int ribbonSum = 0;
            while (scanner.hasNextLine()) {
                String[] sizes = scanner.nextLine().split("x");

                int length = Integer.parseInt(sizes[0]);
                int width = Integer.parseInt(sizes[1]);
                int height = Integer.parseInt(sizes[2]);

                int lw = length * width;
                int wh = width * height;
                int hl = height * length;

                int paperMin = Math.min(Math.min(lw, wh), hl);
                int[] sortedLengths = Arrays.stream(new int[]{length, width, height}).sorted().toArray();

                paperSum += 2 * (lw + wh + hl) + paperMin;
                ribbonSum += 2 * (sortedLengths[0] + sortedLengths[1]) + (length * width * height);
            }

            System.out.println("Paper sum: " + paperSum);
            System.out.println("Ribbon sum: " + ribbonSum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}
