package com.example.dianming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.gotoall);
        button.setOnClickListener(this);
        Button button2=findViewById(R.id.gototest);
        button2.setOnClickListener(this);
        Button button3=findViewById(R.id.gotosearch);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gotoall:
                
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,All.class);
                startActivity(intent);
                break;
            case R.id.gototest:
                Intent intent2=new Intent();
                intent2.setClass(MainActivity.this,test.class);
                startActivity(intent2);
                break;
            case R.id.gotosearch:
                Intent intent3=new Intent();
                intent3.setClass(MainActivity.this,Search.class);
                startActivity(intent3);
                break;
        }
    }
}
