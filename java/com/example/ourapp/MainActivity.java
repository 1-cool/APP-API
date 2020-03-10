package com.example.ourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Searchfragment searchfragment;
    private Mainfragment mainfragment;
    private  Paihangfragment paihangfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchfragment=new Searchfragment();
        mainfragment=new Mainfragment();
        paihangfragment=new Paihangfragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_cont,mainfragment).commitAllowingStateLoss();


        RadioGroup radioGroup =findViewById(R.id.rg_main);
        radioGroup.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()){
            case  R.id.shouye:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,mainfragment).commitAllowingStateLoss();
                break;
            case  R.id.chaxun:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,searchfragment).commitAllowingStateLoss();
                break;
            case  R.id.paihang:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,paihangfragment).commitAllowingStateLoss();
                break;
            case  R.id.geren:
               // getSupportFragmentManager().beginTransaction().replace(R.id.waitlayout,chaxunfragment).commitAllowingStateLoss();
                break;

            default:
                break;

        }

    }


}
