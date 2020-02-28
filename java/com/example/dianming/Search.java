package com.example.dianming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Search extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText editText=findViewById(R.id.edittext);

        final Addpeople addpeople=new Addpeople();
        addpeople.add();





        final Button button=findViewById(R.id.gosearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView=findViewById(R.id.tool);
                String uname=editText.getText().toString();


                Addpeople addpeople=new Addpeople();
                addpeople.add();
                int i, cont=0;;


                for (i=0;i<addpeople.getList().size();i++){
                    if(uname.equals(addpeople.getList().get(i).get("name"))){
                        break;
                    }
                }
                textView.setText(""+i);


                Intent intent=new Intent();

                intent.setClass(Search.this,Namesearch.class);
                intent.putExtra("number",""+i);
                startActivity(intent);

            }
        });

    }
}
