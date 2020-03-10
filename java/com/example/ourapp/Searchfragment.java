package com.example.ourapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Searchfragment extends Fragment {
    private Namesearchfragment namesearchfragment;
    private Sendnumber sendnumber;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.searchfragment,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final EditText editText=view.findViewById(R.id.edittext);

        final Addpeople addpeople=new Addpeople();
        addpeople.add();






        final Button button=view.findViewById(R.id.gosearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity activity= (MainActivity) getActivity();

                String uname=editText.getText().toString();


                Addpeople addpeople=new Addpeople();
                addpeople.add();
                int i, mark=0;



                    for (i = 0; i < addpeople.getList().size(); i++) {
                        if (uname.equals(addpeople.getList().get(i).get("name"))) {
                            break;
                        }

                    }
                    if (i==34){
                        Toast.makeText(getActivity(),"不存在该部员",Toast.LENGTH_SHORT).show();
                        return;
                    }



                namesearchfragment=Namesearchfragment.setnumber(i);
                getFragmentManager().beginTransaction().replace(R.id.fragment_cont,namesearchfragment).commitAllowingStateLoss();

                Log.d("iiiiiii", "onClick: "+addpeople.getList().size()+"///"+i);









            }
        });

    }
}

