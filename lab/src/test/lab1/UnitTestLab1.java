package test.lab1;

import main.Driver_lab1;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class UnitTestLab1{

    @Test
    void test1(){
        File input = new File("src/test/lab1/in/input.1.in");
        File output = new File("src/test/lab1/out/input.1.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
                ){
                while(inputReader.hasNextLine()){
                    String answer = outputReader.nextLine();

                    int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                    assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

                }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }

    @Test
    void test2(){
        File input = new File("src/test/lab1/in/input.2.in");
        File output = new File("src/test/lab1/out/input.2.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }

        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }
    }

    @Test
    void test3(){
        File input = new File("src/test/lab1/in/input.3.in");
        File output = new File("src/test/lab1/out/input.3.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);
            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }

    @Test
    void test4(){
        File input = new File("src/test/lab1/in/input.4.in");
        File output = new File("src/test/lab1/out/input.4.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);
            }


        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }

    @Test
    void test5(){
        File input = new File("src/test/lab1/in/input.5.in");
        File output = new File("src/test/lab1/out/input.5.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }


    @Test
    void test6(){
        File input = new File("src/test/lab1/in/input.6.in");
        File output = new File("src/test/lab1/out/input.6.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }


    @Test
    void test7(){
        File input = new File("src/test/lab1/in/input.7.in");
        File output = new File("src/test/lab1/out/input.7.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }


    @Test
    void test8(){
        File input = new File("src/test/lab1/in/input.8.in");
        File output = new File("src/test/lab1/out/input.8.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }

    @Test
    void test9(){
        File input = new File("src/test/lab1/in/input.9.in");
        File output = new File("src/test/lab1/out/input.9.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }
    @Test
    void test10(){
        File input = new File("src/test/lab1/in/input.10.in");
        File output = new File("src/test/lab1/out/input.10.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }

    @Test
    void test11(){
        File input = new File("src/test/lab1/in/input.11.in");
        File output = new File("src/test/lab1/out/input.11.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }

    @Test
    void test12(){
        File input = new File("src/test/lab1/in/input.12.in");
        File output = new File("src/test/lab1/out/input.12.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }

    @Test
    void test13(){
        File input = new File("src/test/lab1/in/input.13.in");
        File output = new File("src/test/lab1/out/input.13.ans");

        try(
                Scanner inputReader = new Scanner(input);
                Scanner outputReader = new Scanner(output);
        ){
            while(inputReader.hasNextLine()){
                String answer = outputReader.nextLine();

                int[] ansAsInt = Arrays.stream(answer.split(" ")).mapToInt(Integer::parseInt).toArray();
                assertArrayEquals(Driver_lab1.str2int(inputReader.nextLine()),ansAsInt);

            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }

    }
}
