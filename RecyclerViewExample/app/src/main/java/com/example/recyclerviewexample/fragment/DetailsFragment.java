package com.example.recyclerviewexample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import com.example.recyclerviewexample.R;

public class DetailsFragment extends Fragment {
    String mData = "";
    TextView txt;
    public DetailsFragment(){

    }

    public static DetailsFragment createFragment(String s){
        DetailsFragment fragment = new DetailsFragment();
        Bundle arg = new Bundle();
        arg.putString("mydata",s);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mData = getArguments().getString("mydata");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.details_fragment,container,false);
        txt = v.findViewById(R.id.txt_detail);
        txt.setText(mData);
        return v;
    }

    public void setData(String s){
        txt.setText(s);
    }
}
