package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cryptodo.R;
import com.example.cryptodo.ui.home.HomeFragment;


public class SuccessfulFragment extends Fragment {
    private Button profileButton;


    public SuccessfulFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileButton = (Button) getView().findViewById(R.id.go_to_profile_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_successful, new HomeFragment())
                        .setReorderingAllowed(true).commit();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_succesful, container, false);
    }
}