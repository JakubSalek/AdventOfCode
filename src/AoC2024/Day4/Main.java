package AoC2024.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/AoC2024/Day4/input.txt"));
        int resultPt1 = 0, resultPt2 = 0;

        List<List<Character>> array = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            List<Character> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(c);
            }
            array.add(row);
        }

        array = getPaddedArray(array);

        for (int i = 0; i < array.size(); ++i) {
            for (int j = 0; j < array.get(i).size(); ++j) {
                if (array.get(i).get(j) == 'X') {
                    resultPt1 += countXmas(array, i, j);
                }
                if (array.get(i).get(j) == 'A') {
                    if (countXmas2(array, i, j)) resultPt2++;
                }
            }
        }

        System.out.println("Part One: " + resultPt1);
        System.out.println("Part Two: " + resultPt2);
    }

    private static List<List<Character>> getPaddedArray(List<List<Character>> array) {
        List<List<Character>> paddedArray = new ArrayList<>();

        int numCols = array.get(0).size();
        List<Character> topRow = new ArrayList<>();
        for (int i = 0; i < numCols + 2; i++) {
            topRow.add('0');
        }
        paddedArray.add(topRow);

        for (List<Character> row : array) {
            List<Character> paddedRow = new ArrayList<>();
            paddedRow.add('0');
            paddedRow.addAll(row);
            paddedRow.add('0');
            paddedArray.add(paddedRow);
        }

        List<Character> bottomRow = new ArrayList<>();
        for (int i = 0; i < numCols + 2; i++) {
            bottomRow.add('0');
        }
        paddedArray.add(bottomRow);

        return paddedArray;
    }

    private static int countXmas(List<List<Character>> array, int x, int y) {
        final int[][] directions = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {0, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        final char[] xmasLetters = {'M', 'A', 'S'};
        int result = 0;

        for (int[] direction : directions) {
            int posX = x;
            int posY = y;
            for (int j = 0; j < xmasLetters.length; ++j) {
                posX -= direction[0];
                posY -= direction[1];
                if (array.get(posX).get(posY) == xmasLetters[j]) {
                    if (j == xmasLetters.length - 1) {
                        result++;
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private static boolean countXmas2(List<List<Character>> array, int x, int y) {
        int[][] directions = {{-1, -1, 1, 1}, {1, -1, -1, 1}};

        for (int i = 0; i < directions.length; ++i) {
            int posX1 = x + directions[i][0];
            int posY1 = y + directions[i][1];
            int posX2 = x + directions[i][2];
            int posY2 = y + directions[i][3];
            String result = array.get(posX1).get(posY1) + "A" + array.get(posX2).get(posY2);
            if (result.equals("MAS") || result.equals("SAM")) {
                if (i == directions.length - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }
}