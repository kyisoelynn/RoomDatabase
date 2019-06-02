package com.example.roomsample;

import android.arch.persistence.room.Delete;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.roomsample.Entity.Task;
import com.example.roomsample.database.DatabaseClient;

public class UpdateTaskActivity extends AppCompatActivity {

    EditText editTextTask, editTextDesc, editTextFinishBy;
    CheckBox checkBoxFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        checkBoxFinished = findViewById(R.id.checkBoxFinished);

        final Task task = (Task) getIntent().getSerializableExtra("task");
        loadTask(task);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask(task);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateTaskActivity.this);
                alertDialog.setTitle("Are you sure to delete?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTask(task);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }

    public void loadTask(final Task task) {
        editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        editTextFinishBy.setText(task.getFinishby());
        checkBoxFinished.setChecked(task.isFinished());

    }

    public void updateTask(final Task task) {
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()) {
            editTextTask.setError("Task required");
            editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            editTextDesc.setError("Description required");
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy.setError("Finish By required");
            editTextFinishBy.requestFocus();
            return;
        }

        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishby(sFinishBy);
                task.setFinished(checkBoxFinished.isChecked());

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().update(task);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                finish();

                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }

        UpdateTask updateTask = new UpdateTask();
        updateTask.execute();
    }

    public void deleteTask(final Task task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }

        DeleteTask deleteTask = new DeleteTask();
        deleteTask.execute();
    }
}
