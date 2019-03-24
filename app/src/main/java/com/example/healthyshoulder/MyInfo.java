package com.example.healthyshoulder;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MyInfo extends AppCompatActivity {

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        String sex="";
        String nation="";
        String age="";
        String province="";
        String career="";

        TextView textView_sex=(TextView)findViewById(R.id.textView_myInfo_sex);
        TextView textView_nation=(TextView)findViewById(R.id.textView_myInfo_nation);
        TextView textView_age=(TextView)findViewById(R.id.textView_myInfo_age);
        TextView textView_province=(TextView)findViewById(R.id.textView_myInfo_province);
        TextView textView_career=(TextView)findViewById(R.id.textView_myInfo_career);


        TextView textView_mean_index1=(TextView)findViewById(R.id.textView_mean_index1);
        TextView textView_mean_index2=(TextView)findViewById(R.id.textView_mean_index2);
        TextView textView_mean_index3=(TextView)findViewById(R.id.textView_mean_index3);
        TextView textView_mean_index4=(TextView)findViewById(R.id.textView_mean_index4);
        TextView textView_mean_index5=(TextView)findViewById(R.id.textView_mean_index5);
        TextView textView_mean_index6=(TextView)findViewById(R.id.textView_mean_index6);


        TextView textView_std_index1=(TextView)findViewById(R.id.textView_std_index1);
        TextView textView_std_index2=(TextView)findViewById(R.id.textView_std_index2);
        TextView textView_std_index3=(TextView)findViewById(R.id.textView_std_index3);
        TextView textView_std_index4=(TextView)findViewById(R.id.textView_std_index4);
        TextView textView_std_index5=(TextView)findViewById(R.id.textView_std_index5);
        TextView textView_std_index6=(TextView)findViewById(R.id.textView_std_index6);

        SharedPreferences sharedPreferences=getSharedPreferences("HealthyShoulder",MODE_PRIVATE);
        int userId=sharedPreferences.getInt("loginId",0);


        databaseHelper=new DatabaseHelper(this,"HealthyShoulder.db",null,1);
        SQLiteDatabase db=databaseHelper.getReadableDatabase();



        Cursor cursor=db.rawQuery("select * from user where id=?",new String[]{String.valueOf(userId)});

        if(cursor.moveToFirst()){
            sex=cursor.getString(cursor.getColumnIndex("sex"));
            nation=cursor.getString(cursor.getColumnIndex("nation"));
            age=cursor.getString(cursor.getColumnIndex("birth_year"));
            province=cursor.getString(cursor.getColumnIndex("province"));
            career=cursor.getString(cursor.getColumnIndex("career"));
            cursor.close();

        }



        textView_sex.setText(sex);
        textView_nation.setText(nation);
        textView_age.setText(age);
        textView_province.setText(province);
        textView_career.setText(career);


        textView_mean_index1.setText("1");
        textView_mean_index2.setText("2");
        textView_mean_index3.setText("3");
        textView_mean_index4.setText("4");
        textView_mean_index5.setText("5");
        textView_mean_index6.setText("6");


        textView_std_index1.setText("1");
        textView_std_index2.setText("2");
        textView_std_index3.setText("3");
        textView_std_index4.setText("4");
        textView_std_index5.setText("5");
        textView_std_index6.setText("6");





    }
}
