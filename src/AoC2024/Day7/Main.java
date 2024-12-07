package AoC2024.Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/AoC2024/Day7/input.txt"));
        long resultPt1 = 0, resultPt2 = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] tokens = line.split(":");
            final long finalValue = Long.parseLong(tokens[0]);
            final long[] values = Arrays.stream(tokens[1].substring(1).split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            if (check(values, values[0], 1, finalValue)) resultPt1 += finalValue;
            if (check2(values, values[0], 1, finalValue)) resultPt2 += finalValue;
        }


        System.out.println("Part One: " + resultPt1);
        System.out.println("Part Two: " + resultPt2);
    }

    private static boolean check(long[] values, long sum, int index, long finalValue) {
        if (index < values.length) {
            boolean a, b;
            long aSum = sum * values[index];
            if (aSum > finalValue || aSum < sum) {
                a = false;
            } else {
                a = check(values, aSum, index + 1, finalValue);
            }
            long bSum = sum + values[index];
            if (bSum > finalValue || bSum < sum) {
                b = false;
            } else {
                b = check(values, bSum, index + 1, finalValue);
            }
            return (a || b);
        } else {
            return sum == finalValue;
        }
    }

    private static boolean check2(long[] values, long sum, int index, long finalValue) {
        if (index < values.length) {
            boolean a, b, c;
            long aSum = sum * values[index];
            if (aSum > finalValue || aSum < sum) {
                a = false;
            } else {
                a = check2(values, aSum, index + 1, finalValue);
            }
            long bSum = sum + values[index];
            if (bSum > finalValue || bSum < sum) {
                b = false;
            } else {
                b = check2(values, bSum, index + 1, finalValue);
            }
            long cSum = sum * (long) Math.pow(10, String.valueOf(values[index]).length()) + values[index];
            if (cSum > finalValue || cSum < sum) {
                c = false;
            } else {
                c = check2(values, cSum, index + 1, finalValue);
            }
            return (a || b || c);
        } else {
            return sum == finalValue;
        }
    }
}