package com.example.dianming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class allinformation extends AppCompatActivity {
    private Button button;
    final Addpeople addpeople = new Addpeople();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allinformation);

        addpeople.add();
        final Integer findname= Integer.parseInt(getIntent().getStringExtra("name"));
        TextView name=findViewById(R.id.setname);
        TextView num=findViewById(R.id.setnum);
        TextView work=findViewById(R.id.setwork);
        TextView teacher=findViewById(R.id.setteacher);
        TextView qq=findViewById(R.id.setqq);
        TextView phone=findViewById(R.id.setphone);
        TextView cont=findViewById(R.id.setcont);
        ImageView image=findViewById(R.id.setpic);
        name.setText(String.valueOf(addpeople.getList().get(findname).get("name")));
        num.setText(String.valueOf(addpeople.getList().get(findname).get("num")));
        work.setText(String.valueOf(addpeople.getList().get(findname).get("work")));
        qq.setText(String.valueOf(addpeople.getList().get(findname).get("qq")));
        teacher.setText(String.valueOf(addpeople.getList().get(findname).get("teacher")));
        phone.setText(String.valueOf(addpeople.getList().get(findname).get("phone")));
        image.setImageResource((Integer)addpeople.getList().get(findname).get("pic"));
        cont.setText(String.valueOf(Integer.valueOf(addpeople.getList().get(findname).get("cont").toString())+1));

        Log.d("conttest", "first:"+Integer.parseInt(addpeople.getList().get(findname).get("cont").toString()));

    }
}
