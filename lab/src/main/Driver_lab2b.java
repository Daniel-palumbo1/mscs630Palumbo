package main;
public class Driver_lab2b {

    static long[] euclidAlg(long a, long b){
        boolean switched = false;
        if(b > a){
            long temp = a;
            a = b;
            b = temp;
            switched = true;
        }
        long divisor = a;
        long dividend = b;
        long c0 = 0;
        long c1 = 1;
        long[] coeff = new long[3];
        while(dividend % divisor != 0){
            long btwn = c1;
            long q = (long) Math.floor(dividend/divisor);
            c1 = c0 - (q*btwn);
            c0 = btwn;

            long remainder = dividend % divisor;
            dividend = divisor;
            divisor = remainder;
        }
        coeff[0] = c1;
        coeff[1] = (divisor-(a*c1))/b;
        coeff[2] = divisor;

        if(switched){
            long revMap = coeff[0];
            coeff[0] = coeff[1];
            coeff[1] = revMap;
        }
        return coeff;
    }


    public static void main(String[] arg){
        long[] test = euclidAlg(1398,324);
        for(long a: test){
            System.out.println(a);
        }
    }

}
