package com.example.android1to3.sqlite_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android1to3.R;

import java.util.List;

public class PersonActivity extends AppCompatActivity {
    private EditText etId, etFirstName, etLastName;
    private Button btnSave, btnDelete, btnCancel;
    ListView listView;
    private PersonDao dao;
    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person2);

        listView=findViewById(R.id.listView);
        etId=findViewById(R.id.etId);
        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        btnSave=findViewById(R.id.btnSave);
        btnDelete=findViewById(R.id.btnDelete);
        btnCancel=findViewById(R.id.btnCancel);

        AppDatabase db= Room
                .databaseBuilder(this,AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();
        dao=db.personDao();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePerson();
            }
        });
        setAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Person person=persons.get(position);
                etId.setText(person.getId()+"");
                etId.setEnabled(false);
                etFirstName.setText(person.getFirstName());
                etLastName.setText(person.getLastName());
                btnSave.setText("Update");
                btnDelete.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
            }
        });
    }
    private void setAdapter() {
        persons=dao.getAll();
        ArrayAdapter<Person> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,persons);
        listView.setAdapter(adapter);
    }

    private void savePerson() {

        String string =btnSave.getText().toString();

        Person person = new Person();

        person.setId(Integer.parseInt(etId.getText().toString()));
        person.setFirstName(etFirstName.getText().toString());
        person.setLastName(etLastName.getText().toString());

        if(string.equals("Update")){
            dao.updatePerson(person);
        }else {
            dao.savePerson(person);
        }
        reset();

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        setAdapter();
    }

    private void reset() {
        btnSave.setText("Save");
        etId.setText("");
        etFirstName.setText("");
        etLastName.setText("");
        etId.setEnabled(true);
        btnDelete.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    public void deletePerson(View view) {
        Person person = new Person();
        person.setId(Integer.parseInt(etId.getText().toString()));
        dao.deletePerson(person);
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
        reset();
        setAdapter();
    }

    public void cancelOperation(View view) {
        reset();
    }
}
