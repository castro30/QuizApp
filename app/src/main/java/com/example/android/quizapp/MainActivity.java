package com.example.android.quizapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;
import static com.example.android.quizapp.R.id.answer1;
import static com.example.android.quizapp.R.id.answer_field;
import static com.example.android.quizapp.R.id.question_field;

public class MainActivity extends AppCompatActivity {

    int questionNumber = 1;
    int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //    When Submit answer is clicked it checks the answer
    public void submitAnswer(View view) {


        CheckBox answer1 = (CheckBox) findViewById(R.id.answer1);
        boolean answer1Check = answer1.isChecked();
        CheckBox answer2 = (CheckBox) findViewById(R.id.answer2);
        boolean answer2Check = answer2.isChecked();
        CheckBox answer3 = (CheckBox) findViewById(R.id.answer3);
        boolean answer3Check = answer3.isChecked();

        oneAnswer(answer1Check, answer2Check, answer3Check);

        String theAnswer = firstQuestion(answer1Check, answer2Check, answer3Check);

        displayAnswer(theAnswer);



        if (theAnswer == "Your answer is correct.") {

            questionNumber++;
        }

        if(theAnswer=="Incorrect Answer."){
            wrongAnswers++;
        }

        if (questionNumber==6){

            displayAnswer(passFail(wrongAnswers));
        }

        nextQuestion();


    }

    //    Displays the answer to the screen
    private void displayAnswer(String answer) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.answer_field);

        orderSummaryTextView.setText(answer);
    }

    //    Lets user know if they passed or failed
    private String passFail(int answer){
        if (answer>3){
            if (answer>5){
                answer = 5;
                return "You failed! You got "+answer+ " incorrect";
            }
            return "You failed! You got "+answer+ "incorrect";
        }
        else{
            return "You Passed! You got "+answer+" incorrect.";
        }
    }

    //    Places the next question and answers to the screen
    private void nextQuestion() {
        if (questionNumber == 2) {
            TextView textView = (TextView) findViewById(R.id.question_field);

            textView.setText("8 * 8 = ");

            CheckBox answer1 = (CheckBox) findViewById(R.id.answer1);

            answer1.setText("26");

            CheckBox answer2 = (CheckBox) findViewById(R.id.answer2);

            answer2.setText("64");

            CheckBox answer3 = (CheckBox) findViewById(R.id.answer3);

            answer3.setText("81");

        }

        if (questionNumber == 3) {
            TextView textView = (TextView) findViewById(R.id.question_field);

            textView.setText("9 * 26 = ");

            CheckBox answer1 = (CheckBox) findViewById(R.id.answer1);

            answer1.setText("234");

            CheckBox answer2 = (CheckBox) findViewById(R.id.answer2);

            answer2.setText("68");

            CheckBox answer3 = (CheckBox) findViewById(R.id.answer3);

            answer3.setText("13");
        }

        if (questionNumber == 4) {
            TextView textView = (TextView) findViewById(R.id.question_field);

            textView.setText("125 * 26 = ");

            CheckBox answer1 = (CheckBox) findViewById(R.id.answer1);

            answer1.setText("1475");

            CheckBox answer2 = (CheckBox) findViewById(R.id.answer2);

            answer2.setText("3250");

            CheckBox answer3 = (CheckBox) findViewById(R.id.answer3);

            answer3.setText("151");
        }

        if (questionNumber == 5) {
            TextView textView = (TextView) findViewById(R.id.question_field);

            textView.setText("4 * 8 = ");

            CheckBox answer1 = (CheckBox) findViewById(R.id.answer1);

            answer1.setText("75");

            CheckBox answer2 = (CheckBox) findViewById(R.id.answer2);

            answer2.setText("18");

            CheckBox answer3 = (CheckBox) findViewById(R.id.answer3);

            answer3.setText("32");
        }
    }

//    Picks the correct answer
    private String firstQuestion(boolean answer1, boolean answer2, boolean answer3) {
//        Finds if question 1 and question 3 are correct
        if ((questionNumber == 1) || (questionNumber == 3)) {
            if (answer2 || answer3) {
                return "Incorrect Answer.";
            }

            if (answer1) {
                return "Your answer is correct.";
            }
        }

//        Finds if question 2 or 4 are correct
        if ((questionNumber == 2) || (questionNumber == 4)) {
            if (answer1 || answer3) {
                return "Incorrect Answer.";
            }

            if (answer2) {
                return "Your answer is correct.";
            }
        }

//        Finds the answer to question 5
        if (questionNumber == 5) {
            if (answer1 || answer2) {
                return "Incorrect Answer.";
            }

            if (answer3) {
                return "Your answer is correct.";
            }
        }
        return "";
    }




//    Makes sure that only one answer is chosen and at least one answer is chosen

    private void oneAnswer (boolean answer1, boolean answer2, boolean answer3){
        if (answer1 || answer2 || answer3){
            if ((answer1&&answer2)||(answer2&&answer3)||(answer3&&answer1)) {
                Context contextMultipleAnswers = getApplicationContext();
                CharSequence text = "Please choose only one answer";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(contextMultipleAnswers, text, duration);
                toast.show();
            }
        }
        else {
            Context contextAtLeastOneAnswer = getApplicationContext();
            CharSequence text = "You must choose one answer";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(contextAtLeastOneAnswer, text, duration);
            toast.show();
        }
    }
}
