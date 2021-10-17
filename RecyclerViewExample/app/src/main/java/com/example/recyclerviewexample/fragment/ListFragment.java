package com.example.recyclerviewexample.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewexample.helper.MyAPI;
import com.example.recyclerviewexample.MyAdapter;
import com.example.recyclerviewexample.R;
import com.example.recyclerviewexample.entity.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListFragment extends Fragment {
    List<Student> studentList = new ArrayList<>();
    MyAdapter myAdapter;
    RecyclerView rc;

    IFragmentClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (IFragmentClickListener)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        rc = v.findViewById(R.id.rvStudents);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rc.addItemDecoration(itemDecoration);

        String BASE_URL = "https://6169a65b09e030001712c4f5.mockapi.io/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        MyAPI myRetrofitAPI = retrofit.create(MyAPI.class);
        myRetrofitAPI.getAllStudent().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    studentList = response.body();
                    myAdapter = new MyAdapter(studentList, getContext(), new IItemClickListener() {
                        @Override
                        public void ItemClick(String s) {
                            listener.onClick(s);
                        }
                    });
                    rc.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
            }
        });

        return v;

    }

}
