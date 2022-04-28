package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cryptodo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NftParams#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NftParams extends Fragment implements View.OnClickListener {


    private static final String ARG_PARAM1 = "blockchain";
    private static final String ARG_PARAM2 = "contract_type";
    private String blockchain;
    private String contractType;

    public static NftParams newInstance(String blockchain, String contractType) {
        NftParams f = new NftParams();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, blockchain);
        args.putString(ARG_PARAM2, contractType);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            blockchain = getArguments().getString(ARG_PARAM1);
            contractType = getArguments().getString(ARG_PARAM1);
        }
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

    @Override
    public void onClick(View v) {
        System.out.println("HELLO WORLD");
    }
}