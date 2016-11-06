package com.example.syedinkisarahmed.sqlitesample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Syed Bakhtiyar on 11/6/2016.
 */
public class CustomListAdapter extends BaseAdapter {

    ArrayList<Record> record;
    LayoutInflater inflator;
    Context cont;


    TextView txtName, txtFname, txtMarks;

    public CustomListAdapter(ArrayList<Record> record, Context cont) {
        this.record = record;

        this.cont = cont;
    }

    @Override
    public int getCount() {
        return record.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflator.from(cont).inflate(R.layout.custom_list,parent,false);

        txtName = (TextView) convertView.findViewById(R.id.readName);
        txtFname = (TextView) convertView.findViewById(R.id.readFname);
        txtMarks = (TextView) convertView.findViewById(R.id.readMarks);

        txtName.setText(record.get(position).getName());
        txtFname.setText(record.get(position).getfName());
        txtMarks.setText(""+record.get(position).getMarks());


        return convertView;
    }
}
