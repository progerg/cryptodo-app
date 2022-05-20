package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cryptodo.R;

public class ContractTypeFragment extends Fragment {

    public ContractTypeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton erc721 = (ImageButton) getView().findViewById(R.id.erc721);


        ImageButton erc20 = (ImageButton) getView().findViewById(R.id.erc20);
        erc20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "erc20", Toast.LENGTH_LONG).show();
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_ctype, new SimpleParams())
                        .setReorderingAllowed(true).commit();
                erc20.setEnabled(false);
                erc721.setEnabled(false);

            }
        });

        erc721.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "erc721", Toast.LENGTH_LONG).show();
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_ctype, new NftParams())
                        .setReorderingAllowed(true).commit();
                erc721.setEnabled(false);
                erc20.setEnabled(false);
            }
        });


    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ctype, container, false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}