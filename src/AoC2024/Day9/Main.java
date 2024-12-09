package AoC2024.Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/AoC2024/Day9/input.txt"));
        char[] input = scanner.nextLine().toCharArray();

        System.out.println("Part One: " + getResult(input, false));
        System.out.println("Part Two: " + getResult(input, true));
    }

    private static BigInteger getResult(char[] input, boolean partTwo) {
        final ArrayList<Node> array = createArray(input, partTwo);
        final ArrayList<Node> fixedArray = !partTwo ? shortenString(array) : shortenString2(array);
        return !partTwo ? getChecksum(fixedArray) : getChecksum2(fixedArray);
    }

    private static ArrayList<Node> createArray(char[] input, boolean partTwo) {
        ArrayList<Node> result = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) {
                int curr = input[i] - '0';
                if (!partTwo) {
                    for (int j = 0; j < curr; j++) {
                        result.add(new Node(String.valueOf(index)));
                    }
                } else {
                    result.add(new Node(String.valueOf(index), curr));
                }
                index++;
            } else {
                int curr = input[i] - '0';
                if (!partTwo) {
                    for (int j = 0; j < curr; j++) {
                        result.add(new Node("."));
                    }
                } else {
                    result.add(new Node(".", curr));
                }
            }
        }
        return result;
    }

    private static ArrayList<Node> shortenString(ArrayList<Node> input) {
        ArrayList<Node> result = new ArrayList<>();

        int lastPos = input.size() - 1;
        for (int i = 0; i < input.size(); i++) {
            String curr = input.get(i).value;
            if (curr.equals(".")) {
                for (int j = lastPos; j > i; --j) {
                    if (!input.get(j).value.equals(".")) {
                        result.add(new Node(input.get(j).value));
                        input.get(j).value = ".";
                        lastPos = j - 1;
                        break;
                    }
                }
            } else {
                result.add(new Node(curr));
            }
        }

        return result;
    }

    private static ArrayList<Node> shortenString2(ArrayList<Node> input) {
        ArrayList<Node> result = new ArrayList<>(input);

        for (int i = result.size() - 1; i >= 0; i--) {
            Node curr = result.get(i);
            if (!curr.value.equals(".")) {
                for (int j = 0; j < i; j++) {
                    Node toSwap = result.get(j);
                    if (toSwap.value.equals(".")) {
                        if (curr.size <= toSwap.size) {
                            result.add(j, new Node(curr.value, curr.size));
                            curr.value = ".";
                            toSwap.size -= curr.size;
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    private static BigInteger getChecksum(ArrayList<Node> fixedArray) {
        BigInteger result = BigInteger.ZERO;
        BigInteger multiplier;
        BigInteger digitValue;

        for (int i = 0; i < fixedArray.size(); i++) {
            String currentChar = fixedArray.get(i).value;
            if (currentChar.equals(".")) continue;
            digitValue = BigInteger.valueOf(Long.parseLong(currentChar));
            multiplier = BigInteger.valueOf(i);
            BigInteger term = digitValue.multiply(multiplier);
            result = result.add(term);
        }
        return result;
    }

    private static BigInteger getChecksum2(ArrayList<Node> fixedArray) {
        BigInteger result = BigInteger.ZERO;
        BigInteger multiplier;
        BigInteger digitValue;

        int index = 0;
        for (var node : fixedArray) {
            String currentChar = node.value;
            digitValue = (currentChar.equals(".")) ? BigInteger.ZERO : BigInteger.valueOf(Long.parseLong(currentChar));
            for (int i = 0; i < node.size; i++) {
                multiplier = BigInteger.valueOf(index);
                BigInteger term = digitValue.multiply(multiplier);
                result = result.add(term);
                index++;
            }
        }
        return result;
    }

    private static class Node {
        public String value;
        public int size;
        Node (String value) {
            this.value = value;
        }

        Node (String value, int size) {
            this.value = value;
            this.size = size;
        }
    }
}
