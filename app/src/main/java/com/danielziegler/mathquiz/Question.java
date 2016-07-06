package com.danielziegler.mathquiz;

import java.util.Random;

/**
 * Class that creates the questions for "MathQuiz" out of random numbers.
 *
 * @author Daniel Ziegler
 */
public class Question {

    int firstNumber;
    int secondNumber;
    int thirdNumber;

    int[] numberArray;

    public Question(){
        createRandomNumbers();
    }

    /**
     * Creates random numbers and saves them in an array.
     */
    public void createRandomNumbers(){
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

    /**
     * Gets the solution for the question that is later shown on screen.
     *
     * @return the value of the solution.
     */
    public int getSolution() {
        int solution = firstNumber * secondNumber + thirdNumber;
        return solution;
    }
}
