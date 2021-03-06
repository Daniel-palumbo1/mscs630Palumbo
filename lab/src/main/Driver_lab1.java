/**
 * file: Driver_lab1.java
 * author: Daniel Palumbo
 * course: MSCS_630_711_21S
 * assignment: lab 1
 * due date: 6 March, 2021
 *
 * This file contains a driver main method that takes in a file and prints
 * the file as integers corresponding to the characters of the file.
 */
package main;

import java.io.File;
import java.util.Scanner;

public class Driver_lab1{

    static final int CORRECTION_FACTOR = 10; //adjustment value for getNumericValue a = 10
    static final int SPACE_NUM = 26;
    static final String FILE_NAME = "src/test/in/input.10.in";

    /**
     * str2int
     *
     * This method takes a String of plain text, converts all characters in the
     * string to integers using Character.getNumericValue() and then returns an array
     * containing those integer values. a-z = 0-25 and space = 26.
     *
     * @param plainText String containing any string.
     * @return output int[] containing characters as integers.
     */
    public static int[] str2int(String plainText){
        int[] output = new int[plainText.length()];
        char[] outputAsChar = plainText.toCharArray();
        int index = 0;

        for(char n: outputAsChar){
            if(Character.getNumericValue(n) != -1){
                output[index] = Character.getNumericValue(n) - CORRECTION_FACTOR;
            }
            else{
                output[index] = SPACE_NUM;
            }
            index++;
        }
        return output;
    }


    public static void main(String[] arg){
        try{
            File file = new File(FILE_NAME);
            Scanner in = new Scanner(file);

            while(in.hasNextLine()){
                int[] out = str2int(in.nextLine());

                for(int n: out)
                    System.out.print(n + " ");
                System.out.println();
            }
            in.close();
        }catch(Exception e){
            System.out.println("Something went wrong most likely with FILE_NAME variable");
            System.out.println(e);
        }
    }
}