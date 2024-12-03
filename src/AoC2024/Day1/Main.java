package AoC2024.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        try  (Scanner input = new Scanner(new File("src/AoC2024/Day1/input.txt"))) {
            List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] tokens = line.split(" {3}");
                list1.add(Integer.parseInt(tokens[0]));
                list2.add(Integer.parseInt(tokens[1]));
            }
            list1.sort(Integer::compareTo);
            list2.sort(Integer::compareTo);

            int sumPt1 = 0;
            int sumPt2 = 0;
            for (int i = 0; i < list1.size(); i++) {
                sumPt1 += abs(list2.get(i) - list1.get(i));
                int count = 0;
                for (Integer integer : list2) {
                    if (Objects.equals(list1.get(i), integer)) ++count;
                }
                sumPt2 += list1.get(i) * count;
            }
            System.out.println("Part One: " + sumPt1);
            System.out.println("Part Two: " + sumPt2);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}
