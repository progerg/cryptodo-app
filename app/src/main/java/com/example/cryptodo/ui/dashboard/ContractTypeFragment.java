package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cryptodo.R;
import com.example.cryptodo.databinding.FragmentHomeBinding;
import com.example.cryptodo.ui.home.HomeViewModel;

public class ContractTypeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "name";
    private String blockchain = "";

    public ContractTypeFragment() {
    }

    public static ContractTypeFragment newInstance(String blockchain) {
        ContractTypeFragment f = new ContractTypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, blockchain);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            blockchain = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton erc721 = (ImageButton) getView().findViewById(R.id.erc721);
        erc721.setOnClickListener(this);

        ImageButton erc20 = (ImageButton) getView().findViewById(R.id.erc20);
        erc20.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.erc20:
                Toast.makeText(getActivity(), "erc20", Toast.LENGTH_SHORT).show();
                onButtonPress(blockchain, "erc20");
                break;
            case R.id.erc721:
                Toast.makeText(getActivity(), "erc721", Toast.LENGTH_SHORT).show();
                onButtonPress(blockchain, "erc721");
                break;
        }
    }

    public interface ButtonPressListener {
        void buttonClicked(String blockchain, String ctype);
    }

    private ContractTypeFragment.ButtonPressListener buttonPressListener = null;

    public void setButtonPressListener(ContractTypeFragment.ButtonPressListener buttonPressListener) {
        this.buttonPressListener = buttonPressListener;
    }

    protected void onButtonPress(String blockchain, String ctype) {
        if (buttonPressListener != null) {
            buttonPressListener.buttonClicked(blockchain, ctype);
        }
    }


}