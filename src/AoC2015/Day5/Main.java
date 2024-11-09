package AoC2015.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("src/AoC2015/Day5/input.txt"))) {
            int niceStringsCount = 0, niceStringsCountPt2 = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (isNiceString(line)) niceStringsCount++;
                if (isNiceStringPt2(line)) niceStringsCountPt2++;
            }
            System.out.println("Nice strings count: " + niceStringsCount);
            System.out.println("Nice strings count pt2: " + niceStringsCountPt2);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }

    private static boolean isNiceString(String str) {
        return hasThreeVowels(str) && hasDoubleLetter(str) && !containsBadString(str);
    }

    private static boolean hasThreeVowels(String str) {
        int vowelCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if ("aeiou".indexOf(str.charAt(i)) != -1) ++vowelCount;
            if (vowelCount >= 3) return true;
        }
        return false;
    }

    private static boolean hasDoubleLetter(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) return true;
        }
        return false;
    }

    private static boolean containsBadString(String str) {
        final String[] badStrings = {"ab", "cd", "pq", "xy"};
        for (String badString : badStrings) {
            if (str.contains(badString)) return true;
        }
        return false;
    }

    private static boolean isNiceStringPt2(String str) {
        return hasTwoRepeatingLetters(str) && hasLetterRepeatWithSpace(str);
    }

    private static boolean hasTwoRepeatingLetters(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            String twoLetters = str.substring(i, i + 2);
            if (str.substring(i + 2).contains(twoLetters)) return true;
        }
        return false;
    }

    private static boolean hasLetterRepeatWithSpace(String str) {
        for (int i = 0; i < str.length() - 2; i++) {
            if (str.charAt(i) == str.charAt(i + 2)) return true;
        }
        return false;
    }

}