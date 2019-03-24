package com.example.healthyshoulder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        Button button_history=(Button)findViewById(R.id.button_history1);
        Button button_myInfo=(Button)findViewById(R.id.button_myInfo);
        Button button_logout=(Button)findViewById(R.id.button_logout);



        button_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyList.this,History.class);
                startActivity(intent);
            }
        });



        button_myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyList.this,MyInfo.class);
                startActivity(intent);
            }
        });


        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("HealthyShoulder",MODE_PRIVATE).edit();
                editor.remove("loginId");
                editor.commit();

                Intent intent=new Intent(MyList.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
