package com.example.recyclerviewexample.helper;


import com.example.recyclerviewexample.entity.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyAPI {

    @GET("student")
    Call<List<Student>> getAllStudent();

    @POST("student")
    Call<Student> createStudent(@Body Student student);

}
