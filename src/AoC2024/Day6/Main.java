package AoC2024.Day6;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/AoC2024/Day6/input.txt"));
        int resultPt1 = 0, resultPt2 = 0;

        List<char[]> map = new ArrayList<>();
        Point guardPos = new Point(0, 0);

        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            map.add(line.toCharArray());
            int temp = line.indexOf("^");
            if (temp != -1) {
                guardPos = new Point(i, temp);
                map.get(guardPos.x)[guardPos.y] = '+';
            }
            ++i;
        }

        List<char[]> mapCopyP1 = copyTheMap(map);
        populateMap(mapCopyP1, guardPos);

        for (i = 0; i < map.size(); ++i) {
            for (int j = 0; j < map.get(i).length; ++j) {
                if (mapCopyP1.get(i)[j] == '+') {
                    resultPt1++;
                }

                List<char[]> mapCopyP2 = copyTheMap(map);
                if (mapCopyP2.get(i)[j] == '#' || mapCopyP2.get(i)[j] == '+') continue;
                else mapCopyP2.get(i)[j] = '#';
                if (!populateMap(mapCopyP2, guardPos)) resultPt2++;
            }
        }

        System.out.println("Part One: " + resultPt1);
        System.out.println("Part Two: " + resultPt2);
    }

    private static List<char[]> copyTheMap(List<char[]> map) {
        List<char[]> mapCopy = new ArrayList<>();
        for (char[] row : map) {
            mapCopy.add(row.clone());
        }
        return mapCopy;
    }

    private static boolean populateMap(List<char[]> map, Point guardPos) {
        Point direction = new Point(-1, 0);
        Set<String> visitedStates = new HashSet<>();

        Point nextPos = addPoints(guardPos, direction);
        while (!isFinish(map, nextPos)) {
            String currState = nextPos.x + "," + nextPos.y + "," + direction.x + "," + direction.y;
            if (visitedStates.contains(currState)) return false;
            visitedStates.add(currState);

            final char nextChar = map.get(nextPos.x)[nextPos.y];
            switch (nextChar) {
                case '^':
                case '+':
                case '.':
                    map.get(nextPos.x)[nextPos.y] = '+';
                    break;
                case '#':
                    nextPos = subtractPoints(nextPos, direction);
                    direction = rotateDirection(direction);
                    break;
            }
            nextPos = addPoints(nextPos, direction);
        }
        return true;
    }

    private static Point rotateDirection(Point direction) {
        if (direction.x == -1 && direction.y == 0) {
            return new Point(0, 1);
        } else if (direction.x == 0 && direction.y == 1) {
            return new Point(1, 0);
        } else if (direction.x == 1 && direction.y == 0) {
            return new Point(0, -1);
        } else if (direction.x == 0 && direction.y == -1) {
            return new Point(-1, 0);
        }
        return new Point(0, 0);
    }

    private static Point addPoints(Point a, Point b) {
        return new Point(a.x + b.x, a.y + b.y);
    }

    private static Point subtractPoints(Point a, Point b) {
        return new Point(a.x - b.x, a.y - b.y);
    }

    private static boolean isFinish(List<char[]> map, Point nextPos) {
        final Point mapSize = new Point(map.size(), map.get(0).length);
        return nextPos.x < 0 || nextPos.y < 0 || nextPos.x >= mapSize.x || nextPos.y >= mapSize.y;
    }
}
