package com.example.cryptodo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cryptodo.R;
import com.example.cryptodo.db.ContractProfile;
import com.example.cryptodo.db.DB;
import com.example.cryptodo.ui.dashboard.ContractListAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private DB mDBConnector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDBConnector = new DB(getActivity());
        ListView listView = (ListView) getView().findViewById(R.id.list_view_profile);

        ArrayList<ContractProfile> contracts1 = mDBConnector.getAllSimpleContractsForProfile();
        ArrayList<ContractProfile> contracts2 = mDBConnector.getAllNftContractsForProfile();

        contracts1.addAll(contracts2);

        if (!contracts1.isEmpty()) {
            ContractListAdapter adapter = new ContractListAdapter(getActivity(), contracts1);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}