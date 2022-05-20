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
import com.example.cryptodo.db.DB;

public class ContractTypeFragment extends Fragment implements View.OnClickListener {
    private ImageButton erc721;
    private ImageButton erc20;

    DB mDBConnector;

    private NftParams nftparams = new NftParams();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBConnector = new DB(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        erc721 = (ImageButton) getView().findViewById(R.id.erc721);
        erc721.setOnClickListener(this);

        erc20 = (ImageButton) getView().findViewById(R.id.erc20);
        erc20.setOnClickListener(this);

    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ctype, container, false);
        return root;
    }

    @Override
    public void onClick(View v) {
        String type;
        switch (v.getId()){
            case R.id.erc20:
                type = "erc20";
            case R.id.erc721:
                type = "erc721";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        mDBConnector.addTypeToCurrent(type);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_ctype, nftparams)
                .setReorderingAllowed(true).addToBackStack(null).commit();
        erc721.setEnabled(false);
        erc20.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}