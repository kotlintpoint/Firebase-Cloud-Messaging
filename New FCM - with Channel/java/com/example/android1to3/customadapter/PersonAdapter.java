package com.example.android1to3.customadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android1to3.R;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {
    private ArrayList<Person> persons;

    public PersonAdapter(ArrayList<Person> persons) {
        this.persons=persons;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view= inflater.inflate(R.layout.person_row_item, viewGroup,false);
        }
        TextView tvFirstName=view.findViewById(R.id.tvFirstName);
        TextView tvLastName=view.findViewById(R.id.tvLastName);
        TextView tvMobile=view.findViewById(R.id.tvMobile);
        Person person=persons.get(position);
        tvFirstName.setText(person.getFirstName());
        tvLastName.setText(person.getLastName());
        tvMobile.setText(person.getMobile());
        return view;
    }
}
