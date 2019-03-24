package com.example.healthyshoulder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper=new DatabaseHelper(this,"HealthyShoulder.db",null,1);


        TextView textView_register=(TextView)findViewById(R.id.textView_register);
        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });


        Button button=(Button)findViewById(R.id.button_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_username=(EditText)findViewById(R.id.editText_username);
                EditText editText_pwd=(EditText)findViewById(R.id.editText_password);
                String username=editText_username.getText().toString();
                String pwd=editText_pwd.getText().toString();
                if(username.equals("")||pwd.equals(""))
                    Toast.makeText(Login.this,"用户名或者密码为空！",Toast.LENGTH_SHORT).show();
                else {
                    SQLiteDatabase db= dbHelper.getReadableDatabase();
                    Cursor cursor=db.rawQuery("select * from User where username=?",new String[]{username});

                    if(cursor.moveToFirst()){
                        String realpwd=cursor.getString(cursor.getColumnIndex("password"));
                        if(pwd.equals(realpwd))
                        {
                            Toast.makeText(Login.this,"登录成功！",Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor=getSharedPreferences("HealthyShoulder",MODE_PRIVATE).edit();
                            editor.putInt("loginId",cursor.getInt(cursor.getColumnIndex("id")));
                            editor.commit();
                            cursor.close();
                            Intent intent=new Intent(Login.this,MainActivity.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(Login.this,"密码错误！",Toast.LENGTH_SHORT).show();
                            cursor.close();
                        }
                    }else{
                        Toast.makeText(Login.this,"用户不存在！",Toast.LENGTH_SHORT).show();
                        cursor.close();
                    }
                }
            }
        });

    }
}
