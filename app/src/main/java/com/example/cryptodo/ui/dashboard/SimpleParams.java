package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.cryptodo.R;

public class SimpleParams extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_params, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Switch burnSwitch = (Switch) getView().findViewById(R.id.burn_switch);
        Switch mintSwitch = (Switch) getView().findViewById(R.id.mint_switch);
        Switch safemoonSwitch = (Switch) getView().findViewById(R.id.safemoon_switch);


        burnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (safemoonSwitch.isChecked()) { safemoonSwitch.setChecked(false); }
            }
        });

        mintSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (safemoonSwitch.isChecked()) { safemoonSwitch.setChecked(false); }
            }
        });

        safemoonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mintSwitch.isChecked()) { mintSwitch.setChecked(false); }
                if (burnSwitch.isChecked()) { burnSwitch.setChecked(false); }
            }
        });
    }
}