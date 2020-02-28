package com.example.dianming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Namesearch extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namesearch);
        Addpeople addpeople = new Addpeople();
        addpeople.add();

        TextView name=findViewById(R.id.setname);
        TextView num=findViewById(R.id.setnum);
        TextView work=findViewById(R.id.setwork);
        TextView teacher=findViewById(R.id.setteacher);
        TextView qq=findViewById(R.id.setqq);
        TextView phone=findViewById(R.id.setphone);
        TextView cont=findViewById(R.id.setcont);
        Integer i= Integer.valueOf(getIntent().getStringExtra("number"));
        name.setText(String.valueOf(addpeople.getList().get(i).get("name")));
        num.setText(String.valueOf(addpeople.getList().get(i).get("num")));
        work.setText(String.valueOf(addpeople.getList().get(i).get("work")));
        qq.setText(String.valueOf(addpeople.getList().get(i).get("qq")));
        teacher.setText(String.valueOf(addpeople.getList().get(i).get("teacher")));
        phone.setText(String.valueOf(addpeople.getList().get(i).get("phone")));


        cont.setText(String.valueOf(Integer.valueOf(addpeople.getList().get(i).get("cont").toString())+1+1+1+1+11));


    }
}
