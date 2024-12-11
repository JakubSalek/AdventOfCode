package AoC2024.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/AoC2024/Day10/input.txt"));
        int resultPt1 = 0, resultPt2 = 0;
        ArrayList<int[]> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            input.add(Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray());
        }

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                if (input.get(i)[j] == 0) {
                    HashSet<Point> visited = new HashSet<>();
                    resultPt1 += getTrailScore(input, 0, new Point(i, j), visited, false);
                    resultPt2 += getTrailScore(input, 0, new Point(i, j), visited, true);
                }
            }
        }

        System.out.println("Part One: " + resultPt1);
        System.out.println("Part Two: " + resultPt2);
    }

    private static int getTrailScore(ArrayList<int[]> map, int number, Point currPos, Set<Point> visited, boolean partTwo) {
        int result = 0;
        Point[] points = {new Point(currPos.x - 1, currPos.y), new Point(currPos.x, currPos.y + 1),
                            new Point(currPos.x + 1, currPos.y), new Point(currPos.x, currPos.y - 1)};
        for (Point point : points) {
            int nextNum = number + 1;
            if (isValid(map, point) && map.get(point.x)[point.y] == nextNum) {
                if (nextNum != 9) result += getTrailScore(map, nextNum, point, visited, partTwo);
                else {
                    if (!partTwo) {
                        if (!visited.contains(point)) {
                            visited.add(point);
                            result += 1;
                        }
                    } else {
                        result += 1;
                    }
                }
            }
        }
        return result;
    }

    private static boolean isValid(ArrayList<int[]> map, Point nextPos) {
        int sizeX = map.size();
        int sizeY = map.get(0).length;
        return nextPos.x >= 0 && nextPos.x < sizeX && nextPos.y >= 0 && nextPos.y < sizeY;
    }

    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}
