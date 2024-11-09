package AoC2015.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final int GRID_SIZE = 1000;

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src/AoC2015/Day6/input.txt"))) {
            boolean[][] lights = new boolean[GRID_SIZE][GRID_SIZE];
            int[][] lightsPt2 = new int[GRID_SIZE][GRID_SIZE];
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.startsWith("turn")) line = line.replace("turn ", "");
                final String[] tokens = line.split(" ");
                final String[] startCoords = tokens[1].split(",");
                final String[] endCoords = tokens[3].split(",");

                final int startCol = Integer.parseInt(startCoords[0]);
                final int endCol = Integer.parseInt(endCoords[0]);
                final int startRow = Integer.parseInt(startCoords[1]);
                final int endRow = Integer.parseInt(endCoords[1]);

                processLine(lights, startRow, endRow, startCol, endCol, tokens[0]);
                processLine(lightsPt2, startRow, endRow, startCol, endCol, tokens[0]);
            }

            System.out.println("Part 1: " + countLightsOn(lights));
            System.out.println("Part 2: " + countLightsOn(lightsPt2));

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }

    private static void processLine(boolean[][] lights, int startRow, int endRow, int startCol, int endCol, String operation) {
        for (int currRow = startRow; currRow <= endRow; currRow++) {
            for (int currCol = startCol; currCol <= endCol; currCol++) {
                switch (operation) {
                    case "on" -> lights[currRow][currCol] = true;
                    case "off" -> lights[currRow][currCol] = false;
                    case "toggle" -> lights[currRow][currCol] = !lights[currRow][currCol];
                }
            }
        }
    }

    private static int countLightsOn(boolean[][] lights) {
        int lightsOn = 0;
        for (boolean[] row : lights) {
            for (boolean light : row) {
                if (light) lightsOn++;
            }
        }
        return lightsOn;
    }

    private static void processLine(int[][] lights, int startRow, int endRow, int startCol, int endCol, String operation) {
        for (int currRow = startRow; currRow <= endRow; currRow++) {
            for (int currCol = startCol; currCol <= endCol; currCol++) {
                switch (operation) {
                    case "on" -> ++lights[currRow][currCol];
                    case "off" -> lights[currRow][currCol] = Math.max(0, lights[currRow][currCol] - 1);
                    case "toggle" -> lights[currRow][currCol] += 2;
                }
            }
        }
    }

    private static int countLightsOn(int[][] lights) {
        int lightsOn = 0;
        for (int[] row : lights) {
            for (int light : row) {
                lightsOn += light;
            }
        }
        return lightsOn;
    }
}