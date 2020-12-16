package Advent2020.Day6;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6 {
    // File path to input
    public static final String filePath = "Advent2020/Day6/input.txt";

    public static void main(String[] args) {
        // Reads input file
        Scanner input = readFile(filePath);

        // Holds groups data
        String group = "";
        // Sums all group answers
        int sum = 0;
        int sum2 = 0;
        // Counts number of people in group
        int peopleCounter = 0;

        // Loops through each line in file
        while(input.hasNextLine()) {
            // Gets line of input
            String line = input.nextLine();

            // Empty lines divide data from each group
            if(line.length() > 0) {
                // Adds on to group data
                group += line;

                // Each line is one person
                peopleCounter++;
            }
            else {
                // Loops till group data is empty
                while(group.length() > 0) {
                    // Looks at next question answered yes
                    String yes = group.substring(0, 1);

                    // Holds length before replacement
                    int length = group.length();

                    // Removes all occurences of yes
                    group = group.replaceAll(yes, "");

                    // Sum2 added if all members answered the same way
                    if(peopleCounter == length - group.length()) {
                        sum2++;
                    }

                    // Adds to sum
                    sum++;
                }

                // Resets people counter
                peopleCounter = 0;
            }
        }

        // Loops for last group data
        while(group.length() > 0) {
            // Looks at next question answered yes
            String yes = group.substring(0, 1);

            // Holds length before replacement
            int length = group.length();

            // Removes all occurences of yes
            group = group.replaceAll(yes, "");

            // Sum2 added if all members answered the same way
            if(peopleCounter == length - group.length()) {
                sum2++;
            }

            // Adds to sum
            sum++;
        }

        // Closes input to avoid memory leaks
        input.close();

        // Prints solutions
        System.out.println("Solution 1: " + sum);
        System.out.println("Solution 2: " + sum2);
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
}
