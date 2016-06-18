package com.danielziegler.mathquiz;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Daniel on 17.06.2016.
 */

class ButtonHandler implements OnClickListener{

    boolean usedSolution;

    Button submitButton, solutionButton, newQuestionButton;

    private Context context;
    public static RelativeLayout layout;

    EditText answerField;

    Question question;

    TextView questionField, answerType;

    @Override
    public void onClick(View v) {

    }

    public ButtonHandler(RelativeLayout layout){
/*
        newQuestionButton = (Button)findViewById(R.id.newQuestionButton);
        submitButton = (Button)findViewById(R.id.submitButton);
        solutionButton = (Button)findViewById(R.id.getSolutionButton);

        answerField = (EditText)findViewById(R.id.answerField);
*/
        usedSolution = false;

        this.layout = layout;
        context = layout.getContext();

        newQuestionButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        newQuestion();
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
    }

    public void newQuestion(){
        createQuestion();
        answerType.setText(R.string.noAnswer);
        answerType.setTextColor(Color.BLUE);
    }

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
