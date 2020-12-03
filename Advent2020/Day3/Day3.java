package Advent2020.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    // The input file path
    public static final String FILE_PATH = "Advent2020/Day3/input.txt";

    public static void main(String[] args) {
        // Solution to star 1
        long solution = slopeChecker(3, 1);
        System.out.println("Star 1 solution is: " + solution);

        // Solution to star 2
        solution *= slopeChecker(1, 1);
        solution *= slopeChecker(5, 1);
        solution *= slopeChecker(7, 1);
        solution *= slopeChecker(1, 2);
        System.out.println("Star 2 solution is: " + solution);
    }

    // Read files
    public static Scanner readFile(String path) {
        // Cursor for file reading (initialized to avoid compiler warning)
        Scanner reader = new Scanner(System.in);

        // Incase the file cannot be read
        try {
            // Tries opening the file and setting it to the reader
            File file = new File(path);
            reader = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            // Throws error
            System.out.println("File Could Not Be Read!");
            e.printStackTrace();
        }

        // Returns the cursor
        return reader;
    }

    public static int slopeChecker(int right, int down) {
        // Obtains input
        Scanner input = readFile(FILE_PATH);

        // Tracks edge of input and counts number of trees
        int edge = input.nextLine().length();
        int counter = 0;

        // Locates first target position
        int x_pos = right;
        int y_pos = 1;
        boolean validLine = y_pos % down == 0;

        // Loops through every line
        while(input.hasNextLine()) {
            // Reads line
            String line = input.nextLine();

            if(validLine) {
                // Increases counter
                if(line.charAt(x_pos) == '#') {
                    //System.out.println("TREE FOUND!");
                    counter++;
                }

                // Updates x_pos
                x_pos = (x_pos + right) % edge;
            }
            
            // Updates y_pos
            y_pos++;
            validLine = y_pos % down == 0;
        }

        return counter;
    }
}
