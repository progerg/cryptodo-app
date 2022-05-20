package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cryptodo.R;
import com.example.cryptodo.db.DB;


public class NftParams extends Fragment {
    DB mDBConnector;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBConnector = new DB(getActivity());

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button button = (Button) getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "next", Toast.LENGTH_LONG).show();

                EditText ownerEdit = (EditText) getView().findViewById(R.id.edittext_owner);
                String owner = ownerEdit.getText().toString();

                EditText countEdit = (EditText) getView().findViewById(R.id.edittext_count);
                String count = countEdit.getText().toString();

                EditText nameEdit = (EditText) getView().findViewById(R.id.edittext_name);
                String name = nameEdit.getText().toString();

                EditText symbolEdit = (EditText) getView().findViewById(R.id.edittext_symbol);
                String symbol = symbolEdit.getText().toString();

                EditText founderEdit = (EditText) getView().findViewById(R.id.edittext_founder);
                String founder = founderEdit.getText().toString();

                mDBConnector.firstInsertNft(owner, Long.parseLong(count), name, symbol, founder);

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