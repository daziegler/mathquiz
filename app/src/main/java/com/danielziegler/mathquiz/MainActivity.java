package com.danielziegler.mathquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;

import android.util.Log;

/**
 * The MainActivity Class is the starting point for the app.
 *
 * @author  Daniel Ziegler
 * @version 1.0
 * @since   10.06.2016
 */

public class MainActivity extends AppCompatActivity {

    boolean usedSolution;

    Button newQuestionButton;
    Button submitButton;
    Button solutionButton;

    EditText answerField;

    Question question;

    TextView questionField;
    TextView answerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = new Question();

        newQuestionButton = (Button)findViewById(R.id.newQuestionButton);
        submitButton = (Button)findViewById(R.id.submitButton);
        solutionButton = (Button)findViewById(R.id.solutionButton);

        answerField = (EditText)findViewById(R.id.answerField);

        questionField = (TextView)findViewById(R.id.questionField);
        answerType = (TextView)findViewById(R.id.answerType);

        int firstNumber = question.firstNumber;
        int secondNumber = question.secondNumber;
        int thirdNumber = question.thirdNumber;

        usedSolution = false;

        String questionString = firstNumber + " x " + secondNumber + " + " + thirdNumber + " = ???";

        questionField.setText(questionString);

        answerField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkAnswer();
                    return true;
                } else {
                    return false;
                }
            }
        });

        newQuestionButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        createQuestion();
                        answerType.setText(R.string.noAnswer);
                        answerType.setTextColor(Color.BLUE);
                    }
                }
        );

        submitButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        checkAnswer();
                    }
                }
        );

        solutionButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        getSolution();
                    }
                }
        );
    }

    /**
     * Creates the Question shown to the user via the questionField and clears the answerField.
     */

    public void createQuestion(){
        question.createQuestion();

        int firstNumber = question.firstNumber;
        int secondNumber = question.secondNumber;
        int thirdNumber = question.thirdNumber;

        String questionString = firstNumber + " x " + secondNumber + " + " + thirdNumber + " = ???";
        String solutionString = "";

        answerField.setText(solutionString);
        questionField.setText(questionString);
    }

    /**
     * Compares the answer given via the answerField to the solution created in the question class.
     * If the answer matches the solution, a confirmation in the form of green text is given to the
     * user, if it does not match, the text is red.
     *
     * <p>If the answer was correct, the variable usedSolution is also set false again.
     * This variable prevents green success text from showing if the getSolution function was used.</p>
     */

    public void checkAnswer(){
        int answer;
        int solution = question.getSolution();

        String answerFromField = answerField.getText().toString();
        if (!answerFromField.isEmpty()) {
            answer = Integer.parseInt(answerFromField);
        }
        else{
            answer = 0;
        }

        if(solution == answer){
            if(!usedSolution) {
                answerType.setText(R.string.rightAnswer);
                answerType.setTextColor(Color.GREEN);
            }
            answerField.setText("");

            createQuestion();
            usedSolution = false;
        }
        else{
            answerType.setText(R.string.wrongAnswer);
            answerType.setTextColor(Color.RED);
        }
    }

    /**
     * Gets the solution and give confirmation to the user via blue colored text labels. Sets a
     * boolean variable that indicates that the getSolution function was triggered by the user.
     */

    public void getSolution(){
        int solution = question.getSolution();
        String solutionString = Integer.toString(solution);

        question.getSolution();
        usedSolution = true;
        answerField.setText(solutionString);
        answerType.setText(R.string.noAnswer);
        answerType.setTextColor(Color.BLUE);
    }
}
