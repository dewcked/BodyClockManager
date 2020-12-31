package com.example.capston_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.user_profile, container, false);
        TextView textview1 = v.findViewById(R.id.textView17);
        TextView textview2 = v.findViewById(R.id.textView);
        TextView textview3 = v.findViewById(R.id.textView17);
        TextView textview4 = v.findViewById(R.id.textView17);


        return v;
    }
}

