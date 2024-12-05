package AoC2024.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/AoC2024/Day5/input.txt"));
        int resultPt1 = 0, resultPt2 = 0;
        boolean inputSecondPart = false;
        ArrayList<Integer>[] valuesNeededBefore = new ArrayList[100];
        boolean[] valueOccurs =  new boolean[100];

        for (int i = 0; i < valuesNeededBefore.length; i++) {
            valuesNeededBefore[i] = new ArrayList<>();
        }

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isBlank()) {
                inputSecondPart = true;
                continue;
            }

            if (!inputSecondPart) {
                final String[] values = line.split("\\|");
                final int before = Integer.parseInt(values[0]);
                final int after = Integer.parseInt(values[1]);

                valuesNeededBefore[after].add(before);
            } else {
                for (int i = 0; i < valuesNeededBefore.length; i++) {
                    valueOccurs[i] = false;
                }
                final Integer[] values = Arrays.stream(line.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
                if (checkIsLineValid(valuesNeededBefore, valueOccurs, values, false)) {
                    resultPt1 += getMiddle(values);
                } else {
                    while (true) {
                        boolean[] valueOccursCopy = new boolean[valueOccurs.length];
                        System.arraycopy(valueOccurs, 0, valueOccursCopy, 0, valueOccursCopy.length);
                        if (checkIsLineValid(valuesNeededBefore, valueOccursCopy, values, true)) {
                            break;
                        }
                    }
                    resultPt2 += getMiddle(values);
                }
            }
        }

        System.out.println("Part One: " + resultPt1);
        System.out.println("Part Two: " + resultPt2);
    }

    private static int getMiddle(Integer[] values) {
        return values[(values.length - 1) / 2];
    }

    private static boolean checkIsLineValid(ArrayList<Integer>[] valuesNeededBefore, boolean[] valueOccurs, Integer[] values, boolean partTwo) {
        for (int i = 0; i < values.length; i++) {
            if (isValueValid(valuesNeededBefore, valueOccurs, values, i, partTwo)) {
                valueOccurs[values[i]] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isValueValid(ArrayList<Integer>[] valuesNeededBefore, boolean[] valueOccurs, Integer[] values, int index, boolean partTwo) {
        if (valuesNeededBefore[values[index]].isEmpty()) return true;
        for (int i = 0; i < valuesNeededBefore[values[index]].size(); i++) {
            int curr = valuesNeededBefore[values[index]].get(i);
            for (int j = index; j < values.length; ++j) {
                if (values[j] == curr) {
                    if (!valueOccurs[curr]) {
                        if (!partTwo) {
                            return false;
                        } else {
                            swap(values, index, j);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static void swap(Integer[] values, int a, int b) {
        var temp = values[a];
        values[a] = values[b];
        values[b] = temp;
    }
}
