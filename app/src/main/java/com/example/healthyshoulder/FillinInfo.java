package com.example.healthyshoulder;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class FillinInfo extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillin_info);

        dbHelper=new DatabaseHelper(this,"HealthyShoulder.db",null,1);


        final RadioButton radioButton=(RadioButton)findViewById(R.id.radioButton_male);
        final EditText editText_birth_year=(EditText)findViewById(R.id.editText_birth_year);
        final EditText editText_birth_month=(EditText)findViewById(R.id.editText_birth_month);
        final EditText editText_nation=(EditText)findViewById(R.id.editText_nation);
        final EditText editText_province=(EditText)findViewById(R.id.editText_province);
        final EditText editText_career=(EditText)findViewById(R.id.editText_career);





        Button button=(Button)findViewById(R.id.button_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sex=(radioButton.isChecked())?"男":"女";
                int birth_year=Integer.parseInt(editText_birth_year.getText().toString());
                int birth_month=Integer.parseInt(editText_birth_month.getText().toString());
                String nation=editText_nation.getText().toString();
                String province=editText_province.getText().toString();
                String career=editText_career.getText().toString();


                SharedPreferences sharedPreferences=getSharedPreferences("HealthyShoulder",MODE_PRIVATE);
                String username=sharedPreferences.getString("username","");
                String password=sharedPreferences.getString("pwd","");


                SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put("username",username);
                contentValues.put("password",password);
                contentValues.put("sex",sex);
                contentValues.put("birth_year",birth_year);
                contentValues.put("birth_month",birth_month);
                contentValues.put("province",province);
                contentValues.put("nation",nation);
                contentValues.put("career",career);


                sqLiteDatabase.insert("user",null,contentValues);

                Toast.makeText(FillinInfo.this,"注册成功！",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(FillinInfo.this,Login.class);
                startActivity(intent);

            }
        });











    }
}
