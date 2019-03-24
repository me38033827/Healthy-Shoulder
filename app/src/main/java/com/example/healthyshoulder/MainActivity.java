package com.example.healthyshoulder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_my=(Button)findViewById(R.id.button_my);
        Button button_measure=(Button)findViewById(R.id.button_measure);



        button_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Measurement.class);
                startActivity(intent);
            }
        });



        button_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("HealthyShoulder",MODE_PRIVATE);
                int loginId=sharedPreferences.getInt("loginId",0);
                if(loginId!=0){
                    Intent intent=new Intent(MainActivity.this,MyList.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }

            }
        });


    }
}
