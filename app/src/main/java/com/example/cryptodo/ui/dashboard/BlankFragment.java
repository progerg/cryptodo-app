package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cryptodo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DashboardFragment dashboard_fragment = new DashboardFragment();
    private ContractTypeFragment contract_type_fragment = new ContractTypeFragment();

    public BlankFragment() {
        dashboard_fragment.setButtonPressListener(new DashboardFragment.ButtonPressListener() {
            @Override
            public void buttonClicked(String name) {
                getChildFragmentManager().beginTransaction().replace(R.id.dashboard_area, ContractTypeFragment.newInstance(name))
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getChildFragmentManager().beginTransaction().replace(R.id.dashboard_area, dashboard_fragment)
                .setReorderingAllowed(true).commit();
    }
}