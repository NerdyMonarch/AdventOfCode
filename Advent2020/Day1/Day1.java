package Advent2020.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    // The target sum needed for both stars
    public static final int TARGET = 2020;

    // The input file path
    public static final String FILE_PATH = "Advent2020/Day1/input.txt";

    public static void main(String[] args) {
        // Gets first star answer
        int star1 = star1();
        System.out.println("-----------------------------");

        // Gets second star answer
        int star2 = star2();
        System.out.println("-----------------------------");

        // Prints out solutions
        System.out.println("Star 1 Solution: " + star1);
        System.out.println("Star 2 Solution: " + star2);
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

    // Star one solution
    public static int star1() {
        // Scanner that loops for first number
        Scanner scanner1 = readFile(FILE_PATH);
        while(scanner1.hasNextInt()) {
            int num1 = scanner1.nextInt();

            // Scanner that loops for second number
            Scanner scanner2 = readFile(FILE_PATH);
            while(scanner2.hasNextInt()) {
                int num2 = scanner2.nextInt();

                // Prints to console to see program running
                System.out.println("NUM_1: " + num1 + " NUM_2: " + num2);

                // Checks if sum equals target
                if(num1 + num2 == TARGET) {
                    // Closes all scanners to avoid memory leak
                    scanner1.close();
                    scanner2.close();

                    // Returns multiplication of all nums
                    return num1 * num2;
                }
            }
        }
        // Default return
        return 0;
    }

    // Star two solution
    public static int star2() {
        // Scanner that loops for first number
        Scanner scanner1 = readFile(FILE_PATH);
        while(scanner1.hasNextInt()) {
            int num1 = scanner1.nextInt();

            // Scanner that loops for second number
            Scanner scanner2 = readFile(FILE_PATH);
            while(scanner2.hasNextInt()) {
                int num2 = scanner2.nextInt();

                // Ignores third loop is already greater than target
                if(num1 + num2 < TARGET) {
                    // Scanner that loops for third number
                    Scanner scanner3 = readFile(FILE_PATH);
                    while(scanner3.hasNext()) {
                        int num3 = scanner3.nextInt();

                        // Prints to console to see program running
                        System.out.println("NUM_1: " + num1 + " NUM_2: " + num2 + " NUM3: " + num3);

                        // Checks if sum equals target
                        if(num1 + num2 + num3 == TARGET) {
                            // Closes all scanners to avoid memory leak
                            scanner1.close();
                            scanner2.close();
                            scanner3.close();

                            // Returns multiplication of all nums
                            return num1 * num2 * num3;
                        }
                    }
                }
            }
        }
        // Default return
        return 0;
    }
}