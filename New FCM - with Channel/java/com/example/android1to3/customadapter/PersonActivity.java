package com.example.android1to3.customadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android1to3.R;

import java.util.ArrayList;

public class PersonActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Person> persons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        listView=findViewById(R.id.listView);
        prepareData();
        PersonAdapter adapter=new PersonAdapter(persons);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Person person=persons.get(position);
                Toast.makeText(PersonActivity.this, person.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareData() {
        persons=new ArrayList<>();
        persons.add(new Person("First Name 1","Last Name 1","123"));
        persons.add(new Person("First Name 2","Last Name 2","123"));
        persons.add(new Person("First Name 3","Last Name 3","123"));
        persons.add(new Person("First Name 4","Last Name 4","123"));
    }
}
