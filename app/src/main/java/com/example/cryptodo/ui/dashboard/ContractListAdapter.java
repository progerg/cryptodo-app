package com.example.cryptodo.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cryptodo.R;
import com.example.cryptodo.db.ContractProfile;

import java.util.ArrayList;


public class ContractListAdapter extends ArrayAdapter<ContractProfile> {

    private final Activity context;
    private final ArrayList<ContractProfile> contract;

    public ContractListAdapter(Activity context, ArrayList<ContractProfile> contract) {
        super(context, R.layout.custom_list_item, contract);
        this.context = context;
        this.contract = contract;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_item, null, true);

        TextView title = rowView.findViewById(R.id.title_profile);
        TextView type = rowView.findViewById(R.id.type_profile);
        TextView status = rowView.findViewById(R.id.status_profile);
        ImageView image = rowView.findViewById(R.id.blockchain_profile_icon);


        Button button = rowView.findViewById(R.id.profile_button);

        title.setText(contract.get(position).title);
        type.setText(contract.get(position).type);
        status.setText(contract.get(position).status);

        switch (contract.get(position).blockchain) {
            case "bsc":
                image.setImageResource(R.drawable.ic_bsc_profile);
                break;
            case "eth":
                image.setImageResource(R.drawable.ic_ether_profile);
                break;
            case "tron":
                image.setImageResource(R.drawable.ic_tron_profile);
                break;
            case "pol":
                image.setImageResource(R.drawable.ic_polygon_profile);
                break;
        }

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String url = contract.get(position).url;

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(context, "No url for this contract", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rowView;
    }
}
