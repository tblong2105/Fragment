package com.example.recyclerviewexample.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerviewexample.R;

public class MainActivityFragment extends AppCompatActivity implements IFragmentClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

    }

    @Override
    public void onClick(String s) {
        if(findViewById(R.id.activity_main_container)!=null) {
            DetailsFragment detailsFragment = DetailsFragment.createFragment(s);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.activity_main_container, detailsFragment);
            ft.addToBackStack(null);
            ft.commit();
        }else{
//            DetailsFragment detailsFragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id);
//            detailsFragment.setData(s);
        }
    }

}
