package fr.anthonyquere.fizzbuzz;

import java.util.List;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzz {

    public static void main(String[] args) {
        System.out.println(startFizzBuzz(15));
    }

    public static List<String> startFizzBuzz(int count) {

        List<String> myList = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                myList.add("FizzBuzz");
            }
            else if (i % 3 == 0) {
                myList.add("Fizz");
            }
            else if (i % 5 == 0) {
                myList.add("Buzz");
            }
            else{
                myList.add(String.valueOf(i));
            }
        }
        return myList;
    }
}
