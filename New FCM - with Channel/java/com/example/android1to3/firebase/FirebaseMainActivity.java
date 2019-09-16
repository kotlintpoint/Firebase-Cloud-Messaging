package com.example.android1to3.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android1to3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseMainActivity extends AppCompatActivity {
    private EditText etMessage;
    private Button btnSave;
    private TextView tvMessage;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_main);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        myRef=database.getReference("message");


        tvMessage=findViewById(R.id.tvMessage);
        etMessage=findViewById(R.id.etMessage);
        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFirebase();
            }
        });

        readFirebase();
    }

    private void readFirebase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                tvMessage.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FirebaseMainActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeFirebase() {
        String value=etMessage.getText().toString();
        myRef.setValue(value)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(FirebaseMainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(FirebaseMainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
