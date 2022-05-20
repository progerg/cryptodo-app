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
import com.example.cryptodo.db.DB;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    DB mDBConnector;

    private ImageButton bscButton;
    private ImageButton ethButton;
    private ImageButton polButton;
    private ImageButton tronButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBConnector = new DB(getActivity());

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bscButton = (ImageButton) getView().findViewById(R.id.bscButton);
        bscButton.setOnClickListener(this);

        ethButton = (ImageButton) getView().findViewById(R.id.ethButton);
        ethButton.setOnClickListener(this);

        polButton = (ImageButton) getView().findViewById(R.id.polButton);
        polButton.setOnClickListener(this);

        tronButton = (ImageButton) getView().findViewById(R.id.tronButton);
        tronButton.setOnClickListener(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        String blockchain;
        switch (v.getId()){
            case R.id.polButton:
                blockchain = "pol";
                break;
            case R.id.tronButton:
                Toast.makeText(getActivity(), "In development", Toast.LENGTH_LONG).show();
                return;
            case R.id.ethButton:
                blockchain = "eth";
                break;
            case R.id.bscButton:
                blockchain = "bsc";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        mDBConnector.insertNewCurrent(blockchain);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_dashboard, new ContractTypeFragment())
                .setReorderingAllowed(true).addToBackStack(null).commit();
        tronButton.setEnabled(false);
        polButton.setEnabled(false);
        ethButton.setEnabled(false);
        bscButton.setEnabled(false);

    }
}