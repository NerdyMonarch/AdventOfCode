package Advent2020.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day5 {
    // File path to input
    public static final String filePath = "Advent2020/Day5/input.txt";
    
    public static void main(String[] args) {
        // Reads input file
        Scanner input = readFile(filePath);

        ArrayList<Integer> ids = new ArrayList<Integer>();

        // Holds largest seat id
        int largest = 0;

        // Runs through each line in file
        while(input.hasNextLine()) {
            // Gets next line
            String code = input.nextLine();

            // Gets row and column
            int row = binaryRange(0, 127, code.substring(0, 7));
            int col = binaryRange(0, 7, code.substring(7));
            
            // Adds to ids
            ids.add(row * 8 + col);

            // Checks if seat id is greater than the largest
            if(largest < row * 8 + col) {
                // Updates largest
                largest = row * 8 + col;
            }
        }

        // Closes input to avoid memory leaks
        input.close();

        // Prints solutions
        System.out.println("Star Solution 1: " + largest);
        System.out.println("Star Solution 2: " + findSeat(ids));
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

    // Gets number using binary space partitioning
    public static int binaryRange(int min, int max, String code) {
        // Looks at next char in code
        char key = code.charAt(0);
        // Checks if code means lower half or upper half
        boolean lower = key == 'F' || key == 'L';

        // Base case -> Last letter left in code
        if(code.length() == 1) {
            if(lower) {
                // Returns lower number
                return min;
            }
            // Returns higher number
            return max;
        }
        else {
            // Calculates change to range
            int change = (max + 1 - min) / 2;

            if(lower) {
                // Uses lower half of range
                return binaryRange(min, max - change, code.substring(1));
            }
            // Uses higher half of range
            return binaryRange(min + change, max, code.substring(1));
        }
    }

    // Finds missing id in list (solution to second star)
    public static int findSeat(ArrayList<Integer> ids) {
        // Sorts ids
        Collections.sort(ids);

        // Calculates next expected id
        int expected = ids.get(0) + 1;

        // Loops through all ids expect first which was already checked
        for(int i = 1; i < ids.size(); i++) {
            // If id is not expected, expected id was missing
            if(ids.get(i) != expected) {
                return expected;
            }
            // Updates expected
            expected++;
        }

        // No missing id found
        return -1;
    }
}
