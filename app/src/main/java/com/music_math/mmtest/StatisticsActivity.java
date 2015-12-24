package com.music_math.mmtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivity extends Activity implements View.OnClickListener{

    private TextView textViewRightPercent;
    private TextView textViewMissedPercent;
    private Button btnOk;
    private TextView textViewAnswersInRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinkViews();
        btnOk.setOnClickListener(this);

        Intent intent = getIntent();
        int maxAnswersNum = intent.getIntExtra("Maximum answers", 0);

        int rightAnswersNum = intent.getIntExtra("Right answers", 0);
        textViewRightPercent.setText(String.valueOf(rightAnswersNum*100/maxAnswersNum) + " %");

        int rightAnswersRowNum = intent.getIntExtra("Answers in a row", 0);
        textViewAnswersInRow.setText(String.valueOf(rightAnswersRowNum));

        int missedAnswersNum = intent.getIntExtra("Missed answers", 0);
        textViewMissedPercent.setText(String.valueOf(missedAnswersNum*100/maxAnswersNum) + " %");
    }

    private void LinkViews() {
        setContentView(R.layout.activity_statistics);
        textViewRightPercent = (TextView) findViewById(R.id.textViewPercentRightVal);
        textViewAnswersInRow = (TextView) findViewById(R.id.textViewAnswersRowVal);
        textViewMissedPercent = (TextView) findViewById(R.id.textViewPercentMissedVal);
        btnOk = (Button) findViewById(R.id.btnOk);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnOk)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
