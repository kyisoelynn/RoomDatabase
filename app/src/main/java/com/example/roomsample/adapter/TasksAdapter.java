package com.example.roomsample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomsample.Entity.Task;
import com.example.roomsample.R;
import com.example.roomsample.ViewHolder.TasksViewHolder;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksViewHolder> {

    private Context mContext;

    private List<Task> taskList;

    public TasksAdapter(Context mContext, List<Task> taskList) {
        this.mContext = mContext;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_task_item, viewGroup, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.textViewTask.setText(task.getTask());
        holder.textViewDesc.setText(task.getDesc());
        holder.textViewFinishBy.setText(task.getFinishby());

        if (task.isFinished()){
            holder.textViewStatus.setText("Completed");
        }else {
            holder.textViewStatus.setText("Not Completed");
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
