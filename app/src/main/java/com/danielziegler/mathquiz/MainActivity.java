package com.danielziegler.mathquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;

import android.util.Log;

/**
 * Main Activity. App starts here.
 *
 * @author  Daniel Ziegler
 * @version 1.0
 * @since   10.06.2016
 */

public class MainActivity extends AppCompatActivity {
    Question question;

    ButtonHandler btnHandler;

    TextView questionField;
    TextView answerType;

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.mainLayout);

        question = new Question();
        btnHandler = new ButtonHandler(layout);

        questionField = (TextView)findViewById(R.id.questionField);
        answerType = (TextView)findViewById(R.id.answerType);

        int firstNumber = question.firstNumber;
        int secondNumber = question.secondNumber;
        int thirdNumber = question.thirdNumber;

        String questionString = firstNumber + " x " + secondNumber + " + " + thirdNumber + " = ???";

        questionField.setText(questionString);
    }
}
