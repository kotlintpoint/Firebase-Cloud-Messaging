package com.example.android1to3.php_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android1to3.R;

import java.util.HashMap;

public class NewEmployeeActivity extends AppCompatActivity implements View.OnClickListener, EmployeeAsyncTask.AsyncCallBack {
    private EditText etFirstName, etLastName, etMobile;
    private Button btnSubmit;
    private EmployeeRoot.Employees employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etMobile=findViewById(R.id.etMobile);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        Intent intent=getIntent();
        employee=intent.getParcelableExtra("employee");
        if(employee!=null){
            etFirstName.setText(employee.getFirstname());
            etLastName.setText(employee.getLastname());
            etMobile.setText(employee.getMobile());
        }
    }

    @Override
    public void onClick(View view) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("firstname", etFirstName.getText().toString());
        hashMap.put("lastname", etLastName.getText().toString());
        hashMap.put("mobile", etMobile.getText().toString());

        if(employee!=null){
            // Update
            hashMap.put("id", employee.getId());
            hashMap.put("flag", "2");
        }else {
            // Insert
            hashMap.put("flag", "1");
        }
        EmployeeAsyncTask asyncTask=new EmployeeAsyncTask(this,hashMap);
        asyncTask.execute(Constants.URL);
    }

    @Override
    public void onResponse(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
