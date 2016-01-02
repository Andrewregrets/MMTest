package com.music_math.mmtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;

public class CategoriesActivity extends Activity implements View.OnClickListener{
    private static final String TABLE_NAME_TAG = "TableName";
    private static final String QUESTION_NUMBER_TAG = "QuestionNumber";

    private static final int CATEGORIES_NUM = 2;
    Button btnOk;
    Button btnsCategory[];// Add dependency

    private Cursor categoriesData;
    private static final String CATEGORIES_NAME = "Categories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linkViews();

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        categoriesData = mDbHelper.getTestData(CATEGORIES_NAME);
///////////////////////////// Add dependency
        for (int i = 0; i < CATEGORIES_NUM; i++) {
            btnsCategory[i].setText(categoriesData.getString(0));
            categoriesData.moveToNext();
        }
/////////////////////////////

        mDbHelper.close();

        btnOk.setOnClickListener(this);
        for (int i = 0; i < CATEGORIES_NUM; i++)
            btnsCategory[i].setOnClickListener(this);
    }

    private void linkViews() {
        setContentView(R.layout.activity_categories);
        btnOk = (Button) findViewById(R.id.buttonOk);
        btnsCategory = new Button[2];
        btnsCategory[0] = (Button) findViewById(R.id.btnCategory0);
        btnsCategory[1] = (Button) findViewById(R.id.btnCategory1);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnCategory0)
        {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra(TABLE_NAME_TAG, btnsCategory[0].getText());
            intent.putExtra(QUESTION_NUMBER_TAG, 13);/////
            startActivity(intent);
        }
        if(v.getId() == R.id.btnCategory1)
        {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra(TABLE_NAME_TAG, btnsCategory[1].getText());
            intent.putExtra(QUESTION_NUMBER_TAG, 10);/////
            startActivity(intent);
        }
    }
}
