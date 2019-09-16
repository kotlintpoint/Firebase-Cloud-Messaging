package com.example.android1to3.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android1to3.R;
import com.example.android1to3.php_employee.EmployeeRoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpActivity extends AppCompatActivity {
    private ListView listView;
    private Context context;
    private EditText etFirstName, etLastName, etMobile;
    private Button btnSave;
    List<EmployeeRoot.Employees> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);

        listView=findViewById(R.id.listView);
        etFirstName=findViewById( R.id.etFirstName);
        etLastName=findViewById( R.id.etLastName);
        etMobile=findViewById( R.id.etMobile);
        btnSave=findViewById( R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveEmployee();
            }
        });

        context=this;
        fetchData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EmployeeRoot.Employees emp = employees.get(position);
                String id= emp.getId();
                Deletedata(id);
            }
        });
    }

    private void Deletedata(String id) {
        RetrofitService service = RetrofitClient.getService();
        Call<String> call=service.deleteEmployee("3",id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(EmpActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EmpActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void SaveEmployee() {
        RetrofitService service = RetrofitClient.getService();
        Call<String> call=service.saveEmployee("1", etFirstName.getText().toString(), etLastName.getText().toString(),etMobile.getText().toString() );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String msg=response.body();
                Toast.makeText(EmpActivity.this, msg, Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EmpActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchData() {
        RetrofitService service = RetrofitClient.getService();
        Call<EmployeeRoot> call=service.selectEmployee("4");
        call.enqueue(new Callback<EmployeeRoot>() {
            @Override
            public void onResponse(Call<EmployeeRoot> call, Response<EmployeeRoot> response) {
                EmployeeRoot root=response.body();
                employees = root.getEmployees();
                ArrayAdapter<EmployeeRoot.Employees> adapter
                        =new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, employees);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<EmployeeRoot> call, Throwable t) {
                Toast.makeText(EmpActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
