package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cryptodo.R;


public class NftParams extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button button = (Button) getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "next", Toast.LENGTH_LONG).show();
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_nft_params, new NftParams2())
                        .setReorderingAllowed(true).commit();
                button.setEnabled(false);
            }
        });
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nft_params, container, false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}