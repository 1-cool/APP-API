package com.example.ourapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Namesearchfragment extends Fragment {

    public  static  Namesearchfragment setnumber(Integer integer){
        Namesearchfragment namesearchfragment=new Namesearchfragment();
        Bundle bundle=new Bundle();
        bundle.putInt("i",integer);
        namesearchfragment.setArguments(bundle);
        return namesearchfragment;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.namesearchfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer i = null;
        if (getArguments() != null) {
            i = getArguments().getInt("i");
        }


        Addpeople addpeople = new Addpeople();
        addpeople.add();

        TextView name = view.findViewById(R.id.setname);
        TextView num = view.findViewById(R.id.setnum);
        TextView work = view.findViewById(R.id.setwork);
        TextView teacher = view.findViewById(R.id.setteather);
        TextView qq = view.findViewById(R.id.setqq);
        TextView phone = view.findViewById(R.id.setphone);
        TextView cont = view.findViewById(R.id.setcont);



        name.setText(String.valueOf(addpeople.getList().get(i).get("name")));
        num.setText(String.valueOf(addpeople.getList().get(i).get("num")));
        work.setText(String.valueOf(addpeople.getList().get(i).get("work")));
        qq.setText(String.valueOf(addpeople.getList().get(i).get("qq")));
        teacher.setText(String.valueOf(addpeople.getList().get(i).get("teacher")));
        phone.setText(String.valueOf(addpeople.getList().get(i).get("phone")));


        cont.setText(String.valueOf(Integer.valueOf(addpeople.getList().get(i).get("cont").toString()) + 1 + 1 + 1 + 1 + 11));
    }
}
