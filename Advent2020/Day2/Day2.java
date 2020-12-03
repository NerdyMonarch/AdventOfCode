package Advent2020.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    // The input file path
    public static final String FILE_PATH = "Advent2020/Day2/input.txt";

    public static void main(String[] args) {
        // Reads the input file
        Scanner reader = readFile(FILE_PATH);

        // Holds number of valid passwords
        int counter1 = 0;
        int counter2 = 0;

        // Loops through all passwords
        while(reader.hasNextLine()) {
            // Gets a password
            String line = reader.nextLine();

            // Cleans up line for splitting
            line = line.replace('-', ' ');
            line = line.replace(':', ' ');

            // Splits the line into parts we need
            String[] para = line.split(" ");

            // Gets password validations using the parameters
            boolean valid1 = starValider(Integer.parseInt(para[0]), Integer.parseInt(para[1]), para[2], para[4]);
            boolean valid2 = starValider2(Integer.parseInt(para[0]) - 1, Integer.parseInt(para[1]) - 1, para[2].charAt(0), para[4]);

            // Adds to counter if password is valid
            if(valid1) {
                counter1++;
            }
            if(valid2) {
                counter2++;
            }
        }

        System.out.println("Star 1 Solution: " + counter1);
        System.out.println("Star 2 Solution: " + counter2);
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

    public static boolean starValider(int min, int max, String letter, String password) {
        // Counter number of times the letter appears
        int counter = 0;
        while(password.contains(letter)) {
            // Increments counter because occurence of letter was found
            counter++;

            // Changes password to look for next occurence
            if(password.indexOf(letter) != password.length() - 1) {
                password = password.substring(password.indexOf(letter) + 1);
            }
            else {
                password = "";
            }
        }
        return counter >= min && counter <= max;
    }

    public static boolean starValider2(int index1, int index2, char letter, String password) {
        // Avoids index out of bounds errors
        if(password.length() > index2) {
            // True if only one of the indices hold the letter
            return (password.charAt(index1) == letter) != (password.charAt(index2) == letter);
        }
        else {
            // Only checks for first index
            return password.charAt(index1) == letter;
        }
    }
}
