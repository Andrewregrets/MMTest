package com.music_math.mmtest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import java.io.IOException;
import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionActivity extends Activity implements View.OnClickListener {
    private static final int ANSWERS_NUMBER = 4;
    private static final int FIRST_WRONG_ANSWER_COLUMN = 2;
    private static final int MAX_QUESTION_NUMBER = 10;

    TextView textViewQuestion;
    TextView textViewQNum;

    Button btnsAnswer[];

    Cursor questionData;
    String rightAnswer;

    int currentQuestion = 0;

    private Toast wrongToast;
    private Toast rightToast;
    private int rightAnswersNum = 0;
    private int missedAnswersNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linkViews();
        rightToast = Toast.makeText(getApplicationContext(), "Right you are!", Toast.LENGTH_SHORT);
        wrongToast = Toast.makeText(getApplicationContext(), "You are wrong!!!", Toast.LENGTH_SHORT);

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        questionData = mDbHelper.getTestData();

        for(int i = 0; i < ANSWERS_NUMBER; i++)
            btnsAnswer[i].setOnClickListener(this);

        fillViews();

        mDbHelper.close();

    }

    @Override
    public void onClick(View v) {
        if(validateAnswer(v)) {
            rightToast.show();
            rightAnswersNum++;
        }
        else wrongToast.show();

        saveAnswerStatistic();
        questionData.moveToNext();
        currentQuestion++;
        if(currentQuestion < MAX_QUESTION_NUMBER)
            fillViews();
        else{
            Intent intent = new Intent(this, StatisticsActivity.class);
            intent.putExtra("Maximum answers", MAX_QUESTION_NUMBER);
            intent.putExtra("Right answers", rightAnswersNum);
            intent.putExtra("Missed answers", missedAnswersNum);
            startActivity(intent);
        }
    }

    private void linkViews()
    {
        setContentView(R.layout.activity_question);

        textViewQNum = (TextView) findViewById(R.id.textViewQNum);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);

        btnsAnswer = new Button[4];
        btnsAnswer[0] = (Button) findViewById(R.id.btnAnswer0);
        btnsAnswer[1] = (Button) findViewById(R.id.btnAnswer1);
        btnsAnswer[2] = (Button) findViewById(R.id.btnAnswer2);
        btnsAnswer[3] = (Button) findViewById(R.id.btnAnswer3);
    }

    private void fillViews() {
        textViewQNum.setText(String.valueOf(currentQuestion+1) + '/' + String.valueOf(MAX_QUESTION_NUMBER));

        textViewQuestion.setText(questionData.getString(0));
        rightAnswer = questionData.getString(1);

        int randomNum;
        Random a = new Random();
        randomNum = a.nextInt(3);

        btnsAnswer[randomNum].setText(rightAnswer);

        for(int i = 0, j = FIRST_WRONG_ANSWER_COLUMN; i < ANSWERS_NUMBER; i++)
        {
            if(i != randomNum){
                btnsAnswer[i].setText(questionData.getString(j++));
            }
        }
    }

    private void saveAnswerStatistic() {

    }

    private boolean validateAnswer(View v) {
        if(v.getId() == R.id.btnAnswer0)
            if(rightAnswer.equals(btnsAnswer[0].getText()))
                return true;
            else return false;
        else
        if(v.getId() == R.id.btnAnswer1)
            if(rightAnswer.equals(btnsAnswer[1].getText()))
                return true;
            else return false;
        else
        if(v.getId() == R.id.btnAnswer2)
            if(rightAnswer.equals(btnsAnswer[2].getText()))
                return true;
            else return false;
        else
        if(v.getId() == R.id.btnAnswer3)
            if(rightAnswer.equals(btnsAnswer[3].getText()))
                return true;
            else return false;
        else return true;
    }
}
