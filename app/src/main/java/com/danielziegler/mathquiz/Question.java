package com.danielziegler.mathquiz;

import java.util.Random;


/**
 * Created by Daniel on 17.06.2016.
 */
public class Question {

    int firstNumber;
    int secondNumber;
    int thirdNumber;

    int[] numberArray;

    public Question(){
        createQuestion();
    }

    public void createQuestion(){
        int min = 1;
        int max = 10;

        int maxArray = 10;

        numberArray = new int[maxArray];

        Random random = new Random();

        for (int i = 0; i<=3; i++){
            numberArray[i] = random.nextInt((max - min) + 1) + min;
        }

        firstNumber = numberArray[0];
        secondNumber = numberArray[1];
        thirdNumber = numberArray[2];
    }

    public int getSolution() {
        int solution = firstNumber * secondNumber + thirdNumber;
        return solution;
    }
}
