package com.example.android1to3.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android1to3.MyReceiver;
import com.example.android1to3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorActivity extends AppCompatActivity {

    EditText etFirstName,etLastName,etMoblie;
    Button btnSubmit;
    ListView listView;
    DatabaseReference myRef;
    ArrayList<String> keys;
    ArrayList<Doctor> doctors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor2);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        myRef=database.getReference("doctors");

        listView=findViewById(R.id.listView);
        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etMoblie=findViewById(R.id.etMobile);
        btnSubmit=findViewById(R.id.btnSubmit);

btnSubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Doctor doctor=new Doctor( );
        doctor.setFirstName(etFirstName.getText().toString());
        doctor.setLastName(etLastName.getText().toString());
        doctor.setMoblie(etMoblie.getText().toString());

        myRef.push().setValue(doctor);



    }
});

    readDoctors();
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            String key=keys.get(position);
            myRef.child(key).removeValue();
        }
    });

        BroadcastReceiver broadcastReceiver = new MyReceiver();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        registerReceiver(broadcastReceiver, filter);
    }

    private void readDoctors() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                doctors=new ArrayList<>();
                keys=new ArrayList<>();
                for(DataSnapshot ds:iterable){
                    String key=ds.getKey();
                    Doctor doctor=ds.getValue(Doctor.class);
                    Log.i("KEY123", key+">>"+doctor);
                    doctors.add(doctor);
                    keys.add(key);
                }
                ArrayAdapter<Doctor> adapter=new ArrayAdapter<>(DoctorActivity.this, android.R.layout.simple_list_item_1,doctors);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DoctorActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
