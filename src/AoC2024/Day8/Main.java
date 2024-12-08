package AoC2024.Day8;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/AoC2024/Day8/input.txt"));
        ArrayList<char[]> map = new ArrayList<>();

        while (scanner.hasNextLine()) {
            map.add(scanner.nextLine().toCharArray());
        }

        System.out.println("Part One: " + populateMap(map, false));
        System.out.println("Part Two: " + populateMap(map, true));
    }

    private static int populateMap(ArrayList<char[]> map, boolean partTwo) {
        boolean[][] antinodes = new boolean[map.size()][map.getFirst().length];
        for (boolean[] antinode : antinodes) {
            Arrays.fill(antinode, false);
        }

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                char letter = map.get(i)[j];
                if (letter == '.') continue;
                Point curr = new Point(i, j);
                for (int k = i; k < map.size(); k++) {
                    if (k == i) {
                        for (int l = j + 1; l < map.size(); l++) {
                            if (letter == map.get(k)[l]) {
                                if (!partTwo) putAntinodes(antinodes, curr, new Point(k, l));
                                else putAntinodes2(antinodes, curr, new Point(k, l));
                            }
                        }
                    } else {
                        for (int l = 0; l < map.get(k).length; l++) {
                            if (letter == map.get(k)[l]) {
                                if (!partTwo) putAntinodes(antinodes, curr, new Point(k, l));
                                else putAntinodes2(antinodes, curr, new Point(k, l));
                            }
                        }

                    }
                }
            }
        }
        int result = 0;
        for (boolean[] antinode : antinodes) {
            for (boolean b : antinode) {
                if (b) result++;
            }
        }
        return result;
    }

    private static void putAntinodes(boolean[][] antinodes, Point pos1, Point pos2) {
        Point difference = getDifference(pos1, pos2);
        Point antinode = new Point(pos1.x + difference.x, pos1.y + difference.y);
        if (isValid(antinode, antinodes)) antinodes[antinode.x][antinode.y] = true;

        difference = getDifference(pos2, pos1);
        antinode = new Point(pos2.x + difference.x, pos2.y + difference.y);
        if (isValid(antinode, antinodes)) antinodes[antinode.x][antinode.y] = true;
    }

    private static void putAntinodes2(boolean[][] antinodes, Point pos1, Point pos2) {
        Point difference = getDifference(pos2, pos1);
        Point antinode = new Point(pos1.x + difference.x, pos1.y + difference.y);
        while (isValid(antinode, antinodes)) {
            antinodes[antinode.x][antinode.y] = true;
            antinode = new Point(antinode.x + difference.x, antinode.y + difference.y);
        }

        difference = getDifference(pos1, pos2);
        antinode = new Point(pos2.x + difference.x, pos2.y + difference.y);
        while (isValid(antinode, antinodes)) {
            antinodes[antinode.x][antinode.y] = true;
            antinode = new Point(antinode.x + difference.x, antinode.y + difference.y);
        }
    }

    private static boolean isValid(Point point, boolean[][] antinodes) {
        int sizeX = antinodes.length;
        int sizeY = antinodes[0].length;
        return point.x >= 0 && point.x < sizeX && point.y >= 0 && point.y < sizeY;
    }

    private static Point getDifference(Point a, Point b) {
        return new Point(a.x - b.x, a.y - b.y);
    }
}
