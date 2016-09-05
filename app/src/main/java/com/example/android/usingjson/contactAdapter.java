package com.example.android.usingjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uche on 8/22/2016.
 */
public class contactAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public contactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(contacts object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        contentHolder contentHolder1;
        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contentHolder1 = new contentHolder();
            contentHolder1.fistname = (TextView) row.findViewById(R.id.firstname);
            contentHolder1.lastname = (TextView) row.findViewById(R.id.lastname);
            contentHolder1.party = (TextView) row.findViewById(R.id.party);
            row.setTag(contentHolder1);

        }
        else {
            contentHolder1 = (contentHolder) row.getTag();

        }

        contacts contacts1 = (contacts) this.getItem(position);
        contentHolder1.fistname.setText(contacts1.getFirstname());
        contentHolder1.lastname.setText(contacts1.getLastname());
        contentHolder1.party.setText(contacts1.getParty());

        return row;
    }

    static class contentHolder {

        TextView fistname, lastname, party;

    }
}
