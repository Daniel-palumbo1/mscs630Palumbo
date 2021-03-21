/**
 *
 * file: Driver_lab2a.java
 * author: Daniel Palumbo
 * course: MSCS_630_711_21S
 * assignment: lab 3b
 * due date: 15 March, 2021
 * version: final
 *
 * This program calculates takes an input file and stores an identifier that is saved and a string that is also saved.
 * it then converts every 16 characters of the string to a 4 x 4 Ascii matrix and then returns it. If the string it
 * receives is < 16 then it fills the remaining empty indices of the matrix with the identifier.
 *
 */
package main;

import java.io.File;
import java.util.Scanner;

public class Driver_lab3b {
    public static String text = "";
    public final static int numberOfValues = 16;
    public final static int NxN = 16/4;
    public static char identifier;
    static String path = "src/test/lab3/in/input.3b.2.in";

    /**
     * getHexMatP
     *
     *
     * This method takes a character s and a String of length 16  or less and converts each letter to ascii
     * in an int[] array and then loads them into an int matrix. I definitely could have saved some space here but
     * for ease of me editing the code to fit unit tests this made it easier for me.
     *
     *
     * @param s char
     * @param p String
     * @return hexMat int[][] that is an matrix of ascii values pertaining to String p.
     */
    public static int[][] getHexMatP(char s, String p){
        int[][] hexMat = new int[NxN][NxN];
        int[] hex = new int[numberOfValues];

        for(int i = 0; i < p.toCharArray().length; i++){
            hex[i] = p.toCharArray()[i];
        }
        if(p.length() < numberOfValues){
            for(int i = p.toCharArray().length-1; i < hex.length; i++){
                hex[i] = s;
            }
        }
        int index = 0;
        for(int col = 0; col < hexMat.length; col++) {
            for (int row = 0; row < hexMat[0].length; row++) {
                hexMat[row][col] = hex[index];
                index++;
            }
        }
        for(int[] i: hexMat){
            for(int x: i){
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println();
        return hexMat;
    }

    //Main method is a little convoluted but I didn't realize the test files take care of this and I wouldn't need this.
    public static void main(String[] arg){
        try {
            File file = new File(path);
            Scanner input = new Scanner(file);
            identifier = input.next().charAt(0);
            while(input.hasNext()){
                text += input.next();
            }
            if(text.length()/numberOfValues < 1 || text.length() == 1)
                getHexMatP(identifier,text);
            else if(text.length() % 16 == 0){
                for(int i = 0; i < text.length(); i+= numberOfValues){
                    String subString = text.substring(i , i + numberOfValues);
                    getHexMatP(identifier,subString);
                }
            }
            else{
                int lastIndex = 0;
                for(int i = 0; i < text.length(); i += numberOfValues){
                    String subString = text.substring(i , i + numberOfValues);
                    getHexMatP(identifier,subString);
                    lastIndex = i;
                }
                String subString = text.substring(lastIndex);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
