package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.example.recyclerviewexample.entity.Student;
import com.example.recyclerviewexample.fragment.IItemClickListener;
import com.example.recyclerviewexample.helper.DBContext;
import com.example.recyclerviewexample.helper.MyAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Student> studentList;
    private EditText nameInput;
    private EditText classInput;
//    private Button button;
    DBContext dbContext;
    Context contex;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contex = this;
        setContentView(R.layout.activity_main);

        String BASE_URL = "https://6169a65b09e030001712c4f5.mockapi.io/";

        studentList= new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(studentList, getApplicationContext(), new IItemClickListener() {
            @Override
            public void ItemClick(String s) {

            }
        });
                RecyclerView rc = (RecyclerView) findViewById(R.id.rvStudents);
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) .build();
        MyAPI myRetrofitAPI = retrofit.create(MyAPI.class);
        myRetrofitAPI.getAllStudent().enqueue(new Callback<List<Student>>() {

            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    studentList = response.body();
                    MyAdapter myAdapter = new MyAdapter(studentList, getApplicationContext(),
                            new IItemClickListener() {
                                @Override
                                public void ItemClick(String s) {

                                }
                            });
                    rc.setAdapter(myAdapter);
                }

            }
            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
            }
        });
        rc.setAdapter(myAdapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();

                String _class = classInput.getText().toString();

                Student student = new Student(name, _class);
                myRetrofitAPI.createStudent(student).enqueue(new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        if (response.isSuccessful()) {
                            Student student1 = response.body();
                            Log.e("AA", student1.getName());
                            studentList.add(student1);
                            MyAdapter myAdapter = new MyAdapter(studentList, getApplicationContext());
                            rc.setAdapter(myAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {

                    }
                });

                MyAdapter myAdapter = new MyAdapter(studentList, getApplicationContext());

                rc.setAdapter(myAdapter);

            }
        });*/


    }

}