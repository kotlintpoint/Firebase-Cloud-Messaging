package com.example.android1to3.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android1to3.R;

import java.util.ArrayList;

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener, DoctorAdapter.OnDoctorClickListener {
    RecyclerView recyclerView;
    EditText etFirstName,etLastName,etMobile;
    Button btnSave;
    ArrayList<Doctor> doctors;
    DoctorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etMobile=findViewById(R.id.etMobile);
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        prepareData();
        adapter=new DoctorAdapter(doctors,this);
        recyclerView.setAdapter(adapter);


    }

    private void prepareData() {
        doctors=new ArrayList<>();
        /*doctors.add(new Doctor("First Name1", "Last Name1","123123" ));
        doctors.add(new Doctor("First Name2", "Last Name2","123123" ));
        doctors.add(new Doctor("First Name3", "Last Name3","123123" ));
        doctors.add(new Doctor("First Name3", "Last Name3","123123" ));
        doctors.add(new Doctor("First Name3", "Last Name3","123123" ));
        doctors.add(new Doctor("First Name3", "Last Name3","123123" ));
        doctors.add(new Doctor("First Name3", "Last Name3","123123" ));*/
    }

    @Override
    public void onClick(View view) {
        String firstName=etFirstName.getText().toString();
        String lastName=etLastName.getText().toString();
        String mobile=etMobile.getText().toString();
        Doctor doctor=new Doctor(firstName,lastName,mobile);
        doctors.add(doctor);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDoctorClick(Doctor doctor) {
        Toast.makeText(this, doctor.toString(), Toast.LENGTH_SHORT).show();
    }
}
