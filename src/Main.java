import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/AoC2015/DayX/input.txt");
            Scanner input = new Scanner(inputFile);



            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }
}