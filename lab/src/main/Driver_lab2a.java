package main;

public class Driver_lab2a {


    static long euclidAlg(long a, long b){
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
        System.out.println(euclidAlg(24,24));

    }
}
