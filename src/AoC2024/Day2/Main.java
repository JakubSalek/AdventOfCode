package AoC2024.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/AoC2024/Day2/input.txt"));
        int safeSumPt1 = 0, safeSumPt2 = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            ArrayList<Integer> values = new ArrayList<>((Arrays.stream(line.split(" ")).map(Integer::parseInt).toList()));
            if (isSafeBase(values)) safeSumPt1++;
            if (isSafeWithMistake(values)) safeSumPt2++;

        }

        System.out.println("Part One: " + safeSumPt1);
        System.out.println("Part Two: " + safeSumPt2);
    }

    public static boolean isSafeBase(ArrayList<Integer> values) {
        boolean isRising = isRising(values);

        for (int i = 0; i < values.size() - 1; i++) {
            final int diff = values.get(i) - values.get(i + 1);
            if ((diff == 0) || (Math.abs(diff) > 3) || !((diff < 0) == isRising )) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSafeWithMistake(ArrayList<Integer> values) {
        if (isSafeBase(values)) return true;

        for (int i = 0; i < values.size(); i++) {
            ArrayList<Integer> newValues = new ArrayList<>(values);
            newValues.remove((Integer) i);
            if (isSafeBase(newValues)) return true;
        }

        return false;
    }

    public static boolean isRising(ArrayList<Integer> values) {
        return values.get(0) < values.get(1);
    }
}