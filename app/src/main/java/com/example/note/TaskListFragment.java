package com.example.note;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends ListFragment {


    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<Task>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, MainActivity.myTask));
    }
}
