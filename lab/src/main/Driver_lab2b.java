package main;
public class Driver_lab2b {

    public static long[] euclidAlgExt(long a, long b){
        boolean switched = false;
        if(b > a ){
            long temp = a;
            a = b;
            b = temp;
            switched = true;
        }
        else if(a == b){
            switched = true;
        }
        long divisor = a;
        long dividend = b;
        long ax0 = 0;
        long ax1 = 1;
        long bx0 = 1;
        long bx1 = 0;

        long[] coeff = new long[3];
        while(dividend % divisor != 0){
            long btwnA = ax1;
            long btwnB = bx1;
            long q = Math.floorDiv(dividend,divisor);
            ax1 = ax0 - (q*btwnA);
            ax0 = btwnA;
            bx1 = bx0 - (q*btwnB);
            bx0 = btwnB;

            long remainder = dividend % divisor;
            dividend = divisor;
            divisor = remainder;
        }
        coeff[1] = ax1;
        coeff[2] = bx1;
        coeff[0] = divisor;

        if(switched){
            long revMap = coeff[2];
            coeff[2] = coeff[1];
            coeff[1] = revMap;
        }
        return coeff;
    }


    public static void main(String[] arg){
        long[] test = euclidAlgExt(1398,324);
        for(long a: test){
            System.out.println(a);
        }
    }

}
