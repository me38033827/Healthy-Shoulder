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
import android.widget.Toast;

public class Register extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText editText_username=(EditText)findViewById(R.id.editText_register_username);
        final EditText editText_password1=(EditText)findViewById(R.id.editText_register_password1);
        final EditText editText_password2=(EditText)findViewById(R.id.editText_register_password2);
        Button button=(Button)findViewById(R.id.button_register);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=editText_username.getText().toString();
                String pwd=editText_password1.getText().toString();
                String pwd1=editText_password2.getText().toString();
                if(username.equals("")||pwd.equals("")||pwd1.equals(""))
                    Toast.makeText(Register.this,"用户名或者密码为空！",Toast.LENGTH_SHORT).show();
                else if(!pwd.equals(pwd1))
                    Toast.makeText(Register.this,"密码不一致!",Toast.LENGTH_SHORT).show();
                else{
                    SharedPreferences.Editor editor=getSharedPreferences("HealthyShoulder",MODE_PRIVATE).edit();
                    editor.putString("username",username);
                    editor.putString("pwd",pwd);
                    editor.commit();

                    Intent intent=new Intent(Register.this,FillinInfo.class);
                    startActivity(intent);


                }
            }
        });



    }
}
