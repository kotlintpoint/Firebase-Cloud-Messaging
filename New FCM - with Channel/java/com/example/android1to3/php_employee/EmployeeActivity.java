package com.example.android1to3.php_employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android1to3.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity implements EmployeeAsyncTask.AsyncCallBack {
    private ListView listView;
    private List<EmployeeRoot.Employees> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        listView=findViewById(R.id.listView);




        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("flag","4");
        EmployeeAsyncTask asyncTask=new EmployeeAsyncTask(this,hashMap);
        asyncTask.execute(Constants.URL);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EmployeeRoot.Employees employee=employees.get(position);
                Intent intent=new Intent(EmployeeActivity.this, NewEmployeeActivity.class);
                intent.putExtra("employee", employee);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.employee_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_new){
            Intent intent=new Intent(this, NewEmployeeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(String data) {
        try {
            Gson gson = new Gson();
            EmployeeRoot root = gson.fromJson(data, EmployeeRoot.class);
            employees=root.getEmployees();
            ArrayAdapter<EmployeeRoot.Employees> adapter
                    =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
            listView.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
