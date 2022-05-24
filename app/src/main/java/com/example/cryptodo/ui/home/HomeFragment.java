package com.example.cryptodo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.cryptodo.R;
import com.example.cryptodo.db.ContractProfile;
import com.example.cryptodo.db.DB;
import com.example.cryptodo.ui.dashboard.ContractListAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private DB mDBConnector;

    public HomeFragment() {
        mDBConnector = new DB(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = getActivity().findViewById(R.id.list_view_profile);

        ArrayList<ContractProfile> contracts1 = mDBConnector.getAllSimpleContractsForProfile();
        ArrayList<ContractProfile> contracts2 = mDBConnector.getAllNftContractsForProfile();

        contracts1.addAll(contracts2);

        ContractListAdapter adapter = new ContractListAdapter(getActivity(), contracts1);
        listView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}