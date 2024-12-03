package AoC2024.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try  (Scanner input = new Scanner(new File("src/AoC2024/Day3/input.txt"))) {
            int sumPt1 = 0, sumPt2 = 0;
            boolean mulActive = true;

            StringBuilder myInput = new StringBuilder();
            while (input.hasNextLine()) {
                myInput.append(input.nextLine());
            }

            Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
            Matcher matcher = pattern.matcher(myInput.toString());

            while (matcher.find()) {
                String curr = matcher.group();
                switch (curr) {
                    case "do()":
                        mulActive = true;
                        break;
                    case "don't()":
                        mulActive = false;
                        break;
                    default:
                        String[] values = curr.substring(curr.indexOf('(') + 1, curr.indexOf(')')).split(",");
                        sumPt1 += (Integer.parseInt(values[0]) * Integer.parseInt(values[1]));
                        if (mulActive) {
                            sumPt2 += (Integer.parseInt(values[0]) * Integer.parseInt(values[1]));
                        }
                }
            }

            System.out.println("Part One: " + sumPt1);
            System.out.println("Part Two: " + sumPt2);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}
