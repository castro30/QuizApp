package com.example.android.quizapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;
import static com.example.android.quizapp.R.id.answer1;
import static com.example.android.quizapp.R.id.answer2;
import static com.example.android.quizapp.R.id.answer3;
import static com.example.android.quizapp.R.id.answer_field;
import static com.example.android.quizapp.R.id.question_field;

public class MainActivity extends AppCompatActivity {

    int questionNumber = 1;
    int wrongAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //    When Submit answer is clicked it checks the answer
    public void submitAnswer(View view) {


        RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
        boolean answer1Check = answer1.isChecked();
        RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
        boolean answer2Check = answer2.isChecked();
        RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
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
        EditText nameTextField = (EditText) findViewById(R.id.name_field_view);
        String nameText = nameTextField.getText().toString();

        if (answer>3){
            if (answer>5){
                answer = 5;
                return nameText+", you failed! You got "+answer+ " incorrect";
            }
            return nameText+", you failed! You got "+answer+ " incorrect";
        }
        else{
            return nameText+", you Passed! You got "+answer+" incorrect.";
        }
    }

    //    Places the next question and answers to the screen
    private void nextQuestion() {

        TextView textView = (TextView) findViewById(R.id.question_field);
        RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
        RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
        RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
        CheckBox resetBox = (CheckBox) findViewById(R.id.endProgram);

        if(resetBox.isChecked()){
            textView.setText("9 * 9 = ");
            answer1.setText("81");
            answer2.setText("72");
            answer3.setText("18");
            questionNumber=1;
            wrongAnswers=0;
        }

        if (questionNumber == 2) {

            textView.setText("8 * 8 = ");

            answer1.setText("26");

            answer2.setText("64");

            answer3.setText("81");

        }

        if (questionNumber == 3) {

            textView.setText("9 * 26 = ");

            answer1.setText("234");

            answer2.setText("68");

            answer3.setText("13");
        }

        if (questionNumber == 4) {

            textView.setText("125 * 26 = ");

            answer1.setText("1475");

            answer2.setText("3250");

            answer3.setText("151");
        }

        if (questionNumber == 5) {

            textView.setText("4 * 8 = ");

            answer1.setText("75");

            answer2.setText("18");

            answer3.setText("32");
        }


        answer1.setChecked(false);
        answer2.setChecked(false);
        answer3.setChecked(false);
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
