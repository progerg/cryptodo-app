package com.example.cryptodo.ui.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cryptodo.R;
import com.example.cryptodo.db.DB;

public class SimpleParams extends Fragment {
    private DB mDBConnector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBConnector = new DB(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple_params, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button button = (Button) getView().findViewById(R.id.create_button2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = getView().findViewById(R.id.textView2);

                EditText ownerEdit = (EditText) getView().findViewById(R.id.edittext_owner);
                String owner = ownerEdit.getText().toString();

                if (!(owner.length() == 42 && owner.startsWith("0x"))) {
                    textView.setText("Wrong owner wallet address");
                    return;
                }

                EditText countEdit = (EditText) getView().findViewById(R.id.edittext_count);
                String count = countEdit.getText().toString();

                EditText nameEdit = (EditText) getView().findViewById(R.id.edittext_name);
                String name = nameEdit.getText().toString();

                EditText symbolEdit = (EditText) getView().findViewById(R.id.edittext_symbol);
                String symbol = symbolEdit.getText().toString();

                EditText decimalsEdit = (EditText) getView().findViewById(R.id.edittext_decimals);
                String decimals = decimalsEdit.getText().toString();

                if (!(!name.isEmpty() && !count.isEmpty() && !symbol.isEmpty() && !decimals.isEmpty())) {
                    textView.setText("Please fill in all the fields");
                    return;
                }

                Switch mintSwitch = (Switch) getView().findViewById(R.id.mint_switch);
                boolean mint = mintSwitch.isChecked();

                Switch burnSwitch = (Switch) getView().findViewById(R.id.burn_switch);
                boolean burn = burnSwitch.isChecked();

                Switch safemoonSwitch = (Switch) getView().findViewById(R.id.safemoon_switch);
                boolean safemoon = safemoonSwitch.isChecked();

                mDBConnector.insertSimple(owner, Long.parseLong(count), name, symbol,
                        Integer.parseInt(decimals), burn, mint, safemoon);

                getChildFragmentManager().beginTransaction().replace(R.id.fragment_simple_params, new SuccessfulFragment())
                        .setReorderingAllowed(true).commit();
                button.setEnabled(false);

            }
        });

        Switch burnSwitch = (Switch) getView().findViewById(R.id.burn_switch);
        Switch mintSwitch = (Switch) getView().findViewById(R.id.mint_switch);
        Switch safemoonSwitch = (Switch) getView().findViewById(R.id.safemoon_switch);

        burnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (safemoonSwitch.isChecked()) { safemoonSwitch.setChecked(false); }
            }
        });

        mintSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (safemoonSwitch.isChecked()) { safemoonSwitch.setChecked(false); }
            }
        });

        safemoonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mintSwitch.isChecked()) { mintSwitch.setChecked(false); }
                if (burnSwitch.isChecked()) { burnSwitch.setChecked(false); }
            }
        });
    }
}