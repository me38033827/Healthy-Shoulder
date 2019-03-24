package com.example.healthyshoulder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private List<Record> recordList=new ArrayList<Record>();
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initRecord();
        RecordAdapter recordAdapter=new RecordAdapter(this,R.layout.history_item,recordList);

        ListView listView=(ListView)findViewById(R.id.listView_history);
        listView.setAdapter(recordAdapter);



    }


    private void initRecord(){

        Intent intent=getIntent();

        SharedPreferences sharedPreferences=getSharedPreferences("HealthyShoulder",MODE_PRIVATE);
        int userId=sharedPreferences.getInt("loginId",0);


        databaseHelper=new DatabaseHelper(this,"HealthyShoulder.db",null,1);
        SQLiteDatabase db= databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from record where user_id=?",new String[]{String.valueOf(userId)});

        if(cursor.moveToFirst()){
            do{
                String date;
                String index1;
                String index2;
                String index3;
                String index4;
                String index5;
                String index6;
                int id;

                date=cursor.getString(cursor.getColumnIndex("syc_date"));
                index1=String.valueOf(cursor.getFloat(cursor.getColumnIndex("index1")));
                index2=String.valueOf(cursor.getFloat(cursor.getColumnIndex("index2")));
                index3=String.valueOf(cursor.getFloat(cursor.getColumnIndex("index3")));
                index4=String.valueOf(cursor.getFloat(cursor.getColumnIndex("index4")));
                index5=String.valueOf(cursor.getFloat(cursor.getColumnIndex("index5")));
                index6=String.valueOf(cursor.getFloat(cursor.getColumnIndex("index6")));
                id=cursor.getInt(cursor.getColumnIndex("user_id"));

                Record record=new Record(date,index1,index2,index3,index4,index5,index6,id);
                recordList.add(record);



            }while(cursor.moveToNext());
        }


    }
}
