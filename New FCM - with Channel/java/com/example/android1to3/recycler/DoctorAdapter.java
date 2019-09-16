package com.example.android1to3.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android1to3.R;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DocterViewHolder> {
    private ArrayList<Doctor> doctors;

    // For Row Item Click pass to activity or fragment we need
    // interface
    public interface OnDoctorClickListener{
        void onDoctorClick(Doctor doctor);
    }
    OnDoctorClickListener listener;

    public DoctorAdapter(ArrayList<Doctor> doctors, OnDoctorClickListener listener) {
        this.doctors=doctors;
        this.listener=listener;
    }
    @NonNull
    @Override
    public DocterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.person_row_item,parent,false);
        DocterViewHolder viewHolder=new DocterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DocterViewHolder holder, int position) {
        final Doctor doctor=doctors.get(position);
        holder.tvFirstName.setText(doctor.getFirstName());
        holder.tvLastName.setText(doctor.getLastName());
        holder.tvMobile.setText(doctor.getMobile());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Doctor", doctor.toString());
                // call back to activity or fragment from where
                // adaoter set
                listener.onDoctorClick(doctor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class DocterViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName, tvLastName, tvMobile;
        public DocterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirstName=itemView.findViewById(R.id.tvFirstName);
            tvLastName=itemView.findViewById(R.id.tvLastName);
            tvMobile=itemView.findViewById(R.id.tvMobile);
        }
    }
}
