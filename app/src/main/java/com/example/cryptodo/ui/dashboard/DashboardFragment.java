package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cryptodo.R;

public class DashboardFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton bscButton = (ImageButton) getView().findViewById(R.id.bscButton);
        ImageButton ethButton = (ImageButton) getView().findViewById(R.id.ethButton);
        ImageButton polButton = (ImageButton) getView().findViewById(R.id.polButton);
        ImageButton tronButton = (ImageButton) getView().findViewById(R.id.tronButton);

        tronButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tron", Toast.LENGTH_LONG).show();
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_dashboard, new ContractTypeFragment())
                        .setReorderingAllowed(true).commit();
                tronButton.setEnabled(false);
                polButton.setEnabled(false);
                ethButton.setEnabled(false);
                bscButton.setEnabled(false);

            }
        });

        polButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "pol", Toast.LENGTH_LONG).show();
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_dashboard, new ContractTypeFragment())
                        .setReorderingAllowed(true).commit();
                tronButton.setEnabled(false);
                polButton.setEnabled(false);
                ethButton.setEnabled(false);
                bscButton.setEnabled(false);

            }
        });

        ethButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "eth", Toast.LENGTH_LONG).show();
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_dashboard, new ContractTypeFragment())
                        .setReorderingAllowed(true).commit();
                tronButton.setEnabled(false);
                polButton.setEnabled(false);
                ethButton.setEnabled(false);
                bscButton.setEnabled(false);
            }
        });

        bscButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "bsc", Toast.LENGTH_LONG).show();
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_dashboard, new ContractTypeFragment())
                        .setReorderingAllowed(true).commit();
                tronButton.setEnabled(false);
                polButton.setEnabled(false);
                ethButton.setEnabled(false);
                bscButton.setEnabled(false);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}