package com.example.roomsample.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.roomsample.Entity.Task;
import com.example.roomsample.R;
import com.example.roomsample.UpdateTaskActivity;

import java.util.List;

public class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;
    private List<Task> taskList;
    private Context mContext;

    public TasksViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewStatus = itemView.findViewById(R.id.textViewStatus);
        textViewTask = itemView.findViewById(R.id.textViewTask);
        textViewDesc = itemView.findViewById(R.id.textViewDesc);
        textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Task task = taskList.get(getAdapterPosition());
        Intent intent = new Intent(mContext, UpdateTaskActivity.class);
        intent.putExtra("task", task);
        mContext.startActivity(intent);

    }
}
