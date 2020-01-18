package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public ArrayList<Task> myTask;

    static {
        myTask = new ArrayList<Task>();
        myTask.add(new Task("Note1","Do task 1"));
        myTask.add(new Task("Note2","Do task 2"));
        myTask.add(new Task("Note3","Do task 3"));
    }

    public static final String  taskExtra = "Task";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TaskListFragment taskFr = (TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment);

        final ArrayAdapter<Task> taskAdapter = (ArrayAdapter<Task>) taskFr.getListAdapter();
        
        taskFr.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item selected", Toast.LENGTH_SHORT).show();
                startSecondActivity(parent, position);
            }
        });
    }

    private void startSecondActivity(AdapterView<?> parent, int position) {
        Intent intent = new Intent(this, TaskInfoActivity.class);

        Task tmp = (Task)parent.getItemAtPosition(position);

        intent.putExtra(taskExtra, tmp);
        startActivity(intent);
    }

    public void addClick(View view) {

        String task_title = ((EditText)findViewById(R.id.taskTitle)).getText().toString();
        String task_desk = ((EditText)findViewById(R.id.taskDescription)).getText().toString();

        if (task_desk.isEmpty() && task_title.isEmpty()){
            myTask.add(new Task(getResources().getString(R.string.defaultTitle), getResources().getString(R.string.defaultDesc)));
        } else {
            if (task_title.isEmpty())
                task_title = getResources().getString(R.string.defaultTitle);
            if (task_desk.isEmpty())
                task_title = getResources().getString(R.string.defaultDesc);
            myTask.add(new Task(task_title, task_desk));
        }
        TaskListFragment taskFr = (TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment);

        ArrayAdapter<Task> taskAdapter = (ArrayAdapter<Task>) taskFr.getListAdapter();

        taskAdapter.notifyDataSetChanged();

        ((EditText) findViewById(R.id.taskTitle)).setText(null);
        ((EditText) findViewById(R.id.taskDescription)).setText(null);

        View focusedView = getCurrentFocus();
        if  (focusedView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
