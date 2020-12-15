package Advent2020.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    // File path to input
    public static final String filePath = "Advent2020/Day4/input.txt";
    
    // Holds all valid 
    public static void main(String[] args) {
        // Reads input file
        Scanner input = readFile(filePath);

        // Holds passport data
        String pass = "";
        // Number of passports with valid number of values
        int passCounter = 0;
        // Number of passports with valid values
        int validCounter = 0;

        // Runs through all lines in input file
        while(input.hasNextLine()) {
            // Reads line in file
            String line = input.nextLine();

            // Checks if line is not empty
            if(line.length() > 0) {
                // Adds to passport data
                pass += " " + line;
            }
            else {
                // Checks if there are a valid number of values
                if(passportValid(pass)) {
                    passCounter++;
                    // Checks if all values are valid
                    if(passportCredValid(pass)) {
                        validCounter++;
                    }
                }

                // Resets passport data
                pass = "";
            }
        }

        // Checks final passport has a valid number of values
        if(passportValid(pass)) {
            passCounter++;
            // Checks if all values are valid
            if(passportCredValid(pass)) {
                validCounter++;
            }
        }

        // Closes file scanner to avoid memory leak
        input.close();

        // Prints solutions
        System.out.println("Star 1 Solution: " + passCounter);
        System.out.println("Star 2 Solution: " + validCounter);
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

    // Counts number of valid inputs
    public static boolean passportValid(String pass) {
        // Holds number of valid fields
        int counter = 0;
        
        // Scanner for passport
        Scanner passParse = new Scanner(pass);

        // Loops through each field
        while(passParse.hasNext()) {
            // Holds name of field
            String cred = passParse.next();
            cred = cred.substring(0, 3);

            // Checks if field is valid (ignoring cid)
            Pattern pattern = Pattern.compile("byr|iyr|eyr|hgt|hcl|ecl|pid");
            Matcher matcher = pattern.matcher(cred);

            // Increments counter if field is valid
            if(matcher.find()) {
                counter++;
            }
        }

        // Closes scanner to avoid memory leak
        passParse.close();

        // Checks if there is a valid number of fields
        return counter >= 7;
    }

    // Checks if all values are valid in passport
    public static boolean passportCredValid(String pass) {
        // Scanner for passport
        Scanner passParse = new Scanner(pass);

        // Loops through each field
        while(passParse.hasNext()) {
            String cred = passParse.next();
            // Ignores cid field
            if(!cred.substring(0, 3).equals("cid")) {
                // Checks if value is valid
                if(!credValid(cred)) {
                    // Closes scanner to avoid memory leak
                    passParse.close();

                    // Returns false because value was not valid
                    return false;
                }
            }
        }

        // Closes scanner to avoid memory leak
        passParse.close();

        // All values were valid
        return true;
    }

    public static boolean credValid(String cred) {
        // Seperates key from val
        String key = cred.substring(0, 3);
        String val = cred.substring(4);

        // Uses regex to check if the value is correct based on the key
        if(key.equals("byr")) {
            Pattern pattern = Pattern.compile("19[2-9][0-9]|200[0-2]");
            Matcher matcher = pattern.matcher(val);
            return matcher.find();
        }
        else if(key.equals("iyr")) {
            Pattern pattern = Pattern.compile("201[0-9]|2020");
            Matcher matcher = pattern.matcher(val);
            return matcher.find();
        }
        else if(key.equals("eyr")) {
            Pattern pattern = Pattern.compile("202[0-9]|2030");
            Matcher matcher = pattern.matcher(val);
            return matcher.find();
        }
        else if(key.equals("hgt")) {
            Pattern pattern = Pattern.compile("((1[5-8][0-9]|19[0-3])cm)|((59|6[0-9]|7[0-6])in)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(val);
            return matcher.find();
        }
        else if(key.equals("hcl")) {
            Pattern pattern = Pattern.compile("#([0-9|aA-fF]{6}$)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(val);
            return matcher.find();
        }
        else if(key.equals("ecl")) {
            Pattern pattern = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(val);
            return matcher.find() && val.length() == 3;
        }
        else if(key.equals("pid")) {
            Pattern pattern = Pattern.compile("[0-9]{9}$");
            Matcher matcher = pattern.matcher(val);
            return matcher.find() && val.length() == 9;
        }
        else if(key.equals("cid")) {
            // Returns true because we ignore cid
            return true;
        }

        // All keys was invalid
        return false;
    }
}
