package main;

import java.io.File;
import java.util.Scanner;


public class Driver_lab3a {
    static String path = "src/test/lab3/in/in.test";
    static int modulo;
    static int dim;

    public static int cofModDet(int m, int[][] A){
        int determinant = 0;
//        System.out.println(A.length);
//        System.out.println(m);
//        System.out.println(A[0][0] % m);
//        System.out.println(Math.floorMod(A[0][0],m));

        //base case1: matrix is 1 value, return val mod m
        if(A.length == 1){
            determinant = Math.floorMod(A[0][0],m);
        }//base case2: matrix is 2 x 2, return ad-bc mod m
        else if(A.length == 2){
            determinant = Math.floorMod(Math.floorMod(A[0][0],m)*Math.floorMod(A[1][1],m) - Math.floorMod(A[1][0],m)*Math.floorMod(A[0][1],m),m);
        }
        else{

//            for(int[] x: A){
//                for(int y: x){
//                    System.out.print(y + " ");
//                }
//                System.out.println();
//            }
            for(int curr = 0; curr < A.length; curr++){
                //System.out.println("current column: " + curr + " Value: " + A[0][curr]);

                int[][] smallerA = new int[A.length-1][A.length-1];
                for(int row = 1; row < A.length; row++){
                    for(int col = 0; col < A.length; col++){
                        if(col < curr) {
                            //System.out.println("row: " + row + " col: " + col);
                            smallerA[row - 1][col] = Math.floorMod(A[row][col],m);
                        }
                        else if(col > curr){
                            //System.out.println("row: " + row + " col: " + col);
                            smallerA[row - 1][col - 1] = Math.floorMod(A[row][col],m);
                        }
                    }
                }
                if(curr % 2 == 0)
                    determinant += Math.floorMod((Math.floorMod(A[0][curr],m)*cofModDet(m,smallerA)),m);
                else
                    determinant -= Math.floorMod((Math.floorMod(A[0][curr],m)*cofModDet(m,smallerA)),m);

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
