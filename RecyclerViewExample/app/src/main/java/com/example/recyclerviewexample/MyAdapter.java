package com.example.recyclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewexample.entity.Student;
import com.example.recyclerviewexample.fragment.IItemClickListener;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Student> studentList;
    IItemClickListener listener;

    public MyAdapter(List<Student> studentList, Context context, IItemClickListener listener) {
        this.studentList = studentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        if(student == null){
            return;
        }
        holder.nameTextView.setText(student.getName());
        holder.classTextView.setText(student.get_class());

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ItemClick(student.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(studentList != null){
            return studentList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView classTextView;

        LinearLayout layout_item;

        public ViewHolder(View itemView) {

            super(itemView);
            nameTextView = itemView.findViewById(R.id.student_name);
            classTextView = itemView.findViewById(R.id.student_class);

            layout_item = itemView.findViewById(R.id.layout_item);

        }
    }
}
