package com.sonetmathapp.sagor.mathapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModeCount> arrayList1 = new ArrayList<>();
    private LayoutInflater inflater;

    CustomAdapter(Context context, ArrayList<ModeCount> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
    }

    @Override
    public int getCount() {
        return arrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_1_layout, parent, false);
        }

        ModeCount tempMode = (ModeCount) getItem(position);
        TextView txt1 = convertView.findViewById(R.id.txtVmodeid1);
        TextView txt2 = convertView.findViewById(R.id.txtVmodeid2);
        txt1.setText(tempMode.getElement());
        txt2.setText(tempMode.getCount());


        return convertView;
    }
}