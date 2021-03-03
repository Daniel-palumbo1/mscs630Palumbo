import java.util.Scanner;
import java.io.*;

public class Driver_lab1{

    static final int CORRECTION_FACTOR = 10;
    static final int SPACE_NUM = 26;
    static final String FILE_NAME = "lab/input/input1.txt";
    
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