/**
 * file: Driver_lab2a.java
 * author: Daniel Palumbo
 * course: MSCS_630_711_21S
 * assignment: lab 2a
 * due date: 15 March, 2021
 *
 * This program calculates the gcd(a,b) using euclids algorithm
 *
 */
package main;


public class Driver_lab2a {

    /**
     * euclidAlg
     *
     * This method takes 2 long params and calculates the gcd using euclidean algorithm
     *
     * @param a long
     * @param b long
     * @return divisor long which is the remainder from n-1 iterations.
     */
    public static long euclidAlg(long a, long b){
        if(b > a){
            long temp = b;
            b = a;
            a = temp;
        }
        long dividend = a;
        long divisor = b;
        while(dividend % divisor != 0){
            long remainder = dividend % divisor;
            dividend = divisor;
            divisor = remainder;
        }
        return divisor;
    }



    public static void main(String[] arg){
        System.out.println(euclidAlg(461135877,24));

    }
}
