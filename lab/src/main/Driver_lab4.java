/**
 *
 * file: Driver_lab4.java
 * author: Daniel Palumbo
 * course: MSCS_630_711_21S
 * assignment: lab 4
 * due date: 22 March, 2021
 * version: final
 *
 * This file tests the AESCipher method aesRoundKeys and outputs the resulting String[]
 *
 */
package main;


public class Driver_lab4 {
    //Main method
    public static void main(String[] arg){
        AESCipher cipher = new AESCipher();
        String[] r = cipher.aesRoundKeys("5468617473206D79204B756E67204675");
        for(String out: r){
            System.out.println(out);
        }
    }
}
