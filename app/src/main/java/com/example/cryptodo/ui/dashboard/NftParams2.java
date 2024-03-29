package com.example.cryptodo.ui.dashboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cryptodo.R;
import com.example.cryptodo.api.ThreadAddContract;
import com.example.cryptodo.api.in_models.NFTContract;
import com.example.cryptodo.api.in_models.User;
import com.example.cryptodo.db.DB;

public class NftParams2 extends Fragment {
    private DB mDBConnector;

    public NftParams2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBConnector = new DB(getActivity());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) getView().findViewById(R.id.create_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = getView().findViewById(R.id.textView2);

                EditText perTxEdit = (EditText) getView().findViewById(R.id.edittext_per_transaction);
                String perTx = perTxEdit.getText().toString();

                EditText perWalletEdit = (EditText) getView().findViewById(R.id.edittext_per_wallet);
                String perWallet = perWalletEdit.getText().toString();

                EditText startPriceEdit = (EditText) getView().findViewById(R.id.edittext_start_price);
                String startPrice = startPriceEdit.getText().toString();

                EditText timeForGrownEdit = (EditText) getView().findViewById(R.id.edittext_time_for_grown);
                String timeForGrown = timeForGrownEdit.getText().toString();

                EditText urlEdit = (EditText) getView().findViewById(R.id.edittext_url);
                String url = urlEdit.getText().toString();

                if (!(!perTx.isEmpty() && !perWallet.isEmpty() && !startPrice.isEmpty() && !timeForGrown.isEmpty() && !timeForGrown.isEmpty())) {
                    textView.setText("Please fill in all the fields");
                    return;
                }

                Switch fixedSwitch = (Switch) getView().findViewById(R.id.fixed_switch);
                boolean fixedToken = fixedSwitch.isChecked();

                Switch presaleSwitch = (Switch) getView().findViewById(R.id.presale_switch);
                boolean presale = presaleSwitch.isChecked();

                mDBConnector.updateNft(Integer.parseInt(perTx), Integer.parseInt(perWallet), Float.parseFloat(startPrice), timeForGrown, url, fixedToken, presale);

                NFTContract contract = mDBConnector.getNftContract();
                User user = mDBConnector.getUser();

                ThreadAddContract threadAddContract = new ThreadAddContract(user.user_id, contract, mDBConnector);
                threadAddContract.start();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                getChildFragmentManager().beginTransaction().replace(R.id.fragment_nft_params2, new SuccessfulFragment())
                        .setReorderingAllowed(true).commit();
                button.setEnabled(false);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nft_params2, container, false);
    }

}