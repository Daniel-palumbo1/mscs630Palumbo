/**
 * file: Driver_lab2a.java
 * author: Daniel Palumbo
 * course: MSCS_630_711_21S
 * assignment: lab 3a
 * due date: 15 March, 2021
 * version: final
 *
 * This program calculates the determinant of an n x n matrix in mod m
 *
 */
package main;

import java.io.File;
import java.util.Scanner;


public class Driver_lab3a {
    static String path = "src/test/lab3/in/in.test";
    static int modulo;
    static int dim;

    /**
     * cofModDet
     *
     * This method takes an n x n matrix and an integer denoting modulus. It then finds the determinant mod m
     * using 3 different cases. Case1 is 1 x 1 matrix, case2 is 2 x 2 matrix, and case 3 is n x n in which the
     * determinant is found via summation of det submatrices recursively.
     *
     * @param m int
     * @param A int[][]
     * @return determinant int which represents the determinant mod m.
     */
    public static int cofModDet(int m, int[][] A){
        int determinant = 0;
        //base case1: matrix is 1 value, return val mod m
        if(A.length == 1){
            determinant = Math.floorMod(A[0][0],m);
        }//case2: matrix is 2 x 2, return ad-bc mod m
        else if(A.length == 2){
            determinant = Math.floorMod(Math.floorMod(A[0][0],m)*Math.floorMod(A[1][1],m) - Math.floorMod(A[1][0],m)*Math.floorMod(A[0][1],m),m);
        }//case3: matrix is n x n calls itself to until we get to a 2 x 2 or 1 x 1
        else{
            for(int curr = 0; curr < A.length; curr++){

                int[][] smallerA = new int[A.length-1][A.length-1];
                for(int row = 1; row < A.length; row++){
                    for(int col = 0; col < A.length; col++){
                        if(col < curr) {
                            smallerA[row - 1][col] = Math.floorMod(A[row][col],m);
                        }
                        else if(col > curr){
                            smallerA[row - 1][col - 1] = Math.floorMod(A[row][col],m);
                        }
                    }
                }
                if(curr % 2 == 0)
                    determinant += Math.floorMod((Math.floorMod(A[0][curr],m)*cofModDet(m,smallerA)),m);
                else
                    determinant -= Math.floorMod((Math.floorMod(A[0][curr],m)*cofModDet(m,smallerA)),m);

//                -printstatement for editing purposes-
//                for(int[] x: smallerA){
//                    for(int y: x){
//                        System.out.print(y + " ");
//                    }
//                    System.out.println();
//                }
            }

        }
        return Math.floorMod(determinant,m);
    }


    public static void main(String[] arg){
        try{
            File file = new File(path);
            Scanner input = new Scanner(file);

            modulo = input.nextInt();
            dim = input.nextInt();
            int[][] matrix = new int[dim][dim];

            for(int i = 0; i < dim; i++){
                for(int j = 0; j < dim; j++){
                    if(input.hasNextInt()){
                        matrix[i][j] = input.nextInt();
                    }
                }
            }
            System.out.println(cofModDet(modulo,matrix));
            for(int[] s: matrix){
                for(int x: s){
                    System.out.print(x + " ");
                }
                System.out.println();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
