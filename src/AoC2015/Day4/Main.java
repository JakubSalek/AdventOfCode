package AoC2015.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try (Scanner scanner = new Scanner(new File("src/AoC2015/Day4/input.txt"))) {
            final String myInput = scanner.nextLine();

            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int i = 1, fiveZerosIndex = -1, sixZerosIndex = -1;
            while (fiveZerosIndex == -1 || sixZerosIndex == -1) {
                md5.reset();
                byte[] byteArray = md5.digest((myInput + i).getBytes(StandardCharsets.UTF_8));
                String hexHash = HexFormat.of().formatHex(byteArray);

                if (hexHash.startsWith("00000") && fiveZerosIndex == -1) fiveZerosIndex = i;
                if (hexHash.startsWith("000000") && sixZerosIndex == -1) sixZerosIndex = i;
                ++i;
            }
            System.out.println("Starting with 5 zeros: " + fiveZerosIndex);
            System.out.println("Starting with 6 zeros: " + sixZerosIndex);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}
