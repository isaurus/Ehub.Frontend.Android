package com.isaac.ehub.ui.team.task;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isaac.ehub.R;

public class TaskFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragment (debes crearlo, ej. fragment_calendar.xml)
        return inflater.inflate(R.layout.fragment_task, container, false);
    }
}