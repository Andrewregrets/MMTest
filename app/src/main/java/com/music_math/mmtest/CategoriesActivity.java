package com.music_math.mmtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import java.sql.SQLException;

public class CategoriesActivity extends Activity {
//    DatabaseHelper sqlHelper;
//    Cursor userCursor;
//    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Button btnOk = (Button) findViewById(R.id.buttonOk);
        ListView listCategory = (ListView) findViewById(R.id.listViewCategortes);

//        sqlHelper = new DatabaseHelper(getApplicationContext());
//        // создаем базу данных
//        sqlHelper.create_db();

//        try {
//            sqlHelper.open();
//            userCursor = sqlHelper.database.rawQuery("select * from " + DatabaseHelper.TABLE, null);
//            String[] data = new String[]{DatabaseHelper.COLUMN_QUESTION, DatabaseHelper.COLUMN_RIGHT_ANSWER,
//                    DatabaseHelper.COLUMN_WRONG_ANSWER0,DatabaseHelper.COLUMN_WRONG_ANSWER1, DatabaseHelper.COLUMN_WRONG_ANSWER2};
//            userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
//                    userCursor, data, new int[]{android.R.id.text1, android.R.id.text2}, 0);
//            listCategory.setAdapter(userAdapter);
//        }
//        catch (SQLException ex){}
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriesActivity.this, QuestionActivity.class));
            }
        });
    }
}
