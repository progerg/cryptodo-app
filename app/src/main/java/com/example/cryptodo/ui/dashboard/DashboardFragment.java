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

public class DashboardFragment extends Fragment implements View.OnClickListener {
    private ContractTypeFragment contract_type_fragment;


    public DashboardFragment()  {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton bscButton = (ImageButton) getView().findViewById(R.id.bscButton);
        bscButton.setOnClickListener(this);

        ImageButton ethButton = (ImageButton) getView().findViewById(R.id.ethButton);
        ethButton.setOnClickListener(this);

        ImageButton polButton = (ImageButton) getView().findViewById(R.id.polButton);
        polButton.setOnClickListener(this);

        ImageButton tronButton = (ImageButton) getView().findViewById(R.id.tronButton);
        tronButton.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.polButton:
                Toast.makeText(getActivity(), "pol", Toast.LENGTH_LONG).show();
                onButtonPress("pol");
                break;
            case R.id.tronButton:
                Toast.makeText(getActivity(), "tron", Toast.LENGTH_LONG).show();
                onButtonPress("tron");
                break;
            case R.id.ethButton:
                Toast.makeText(getActivity(), "eth", Toast.LENGTH_LONG).show();
                onButtonPress("eth");
                break;
            case R.id.bscButton:
                Toast.makeText(getActivity(), "bsc", Toast.LENGTH_LONG).show();
                onButtonPress("bsc");
                break;

        }
    }

    public interface ButtonPressListener {
        void buttonClicked(String blockchain);
    }

    private ButtonPressListener buttonPressListener = null;

    public void setButtonPressListener(ButtonPressListener buttonPressListener) {
        this.buttonPressListener = buttonPressListener;
    }

    protected void onButtonPress(String blockchain) {
        if (buttonPressListener != null) {
            buttonPressListener.buttonClicked(blockchain);
        }
    }
}