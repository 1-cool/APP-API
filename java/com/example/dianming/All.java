package com.example.dianming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class All extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        final ListView listView=findViewById(R.id.alllistview);


        final Addpeople addpeople=new Addpeople();
        addpeople.add();


        MyAdapter adapter=new MyAdapter(this);
        adapter.setList(addpeople.getList());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(All.this,allinformation.class);
                intent.putExtra("name",""+position);
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(All.this,"oooo",Toast.LENGTH_SHORT).show();
                return true;
            }
        });









    }
}
