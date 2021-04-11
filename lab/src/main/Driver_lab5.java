package main;

import java.util.Scanner;

public class Driver_lab5 {
    public static void main(String[] arg) {
        AESCipherLab5 cipher = new AESCipherLab5();
        Scanner in = new Scanner(System.in);
        String plainText = in.nextLine().toUpperCase();
        String keyHex = in.nextLine().toUpperCase();
        String cipherText = cipher.AES(plainText, keyHex);
        System.out.println(cipherText);
    }
}
