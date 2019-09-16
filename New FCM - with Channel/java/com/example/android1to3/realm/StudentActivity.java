package com.example.android1to3.realm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android1to3.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etId, etFirstName, etLastName, etMobile;
    private Button btnSave;
    private Realm realm;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        listView=findViewById(R.id.listView);
        realm=Realm.getDefaultInstance();
        btnSave=findViewById(R.id.btnSave);
        etId=findViewById(R.id.etId);
        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etMobile=findViewById(R.id.etMobile);

        btnSave.setOnClickListener(this);

        fetchStudents();
    }

    private void fetchStudents() {
        RealmResults<Student> students = realm.where(Student.class).findAll();
        setAdapter(students);

        students.addChangeListener(new RealmChangeListener<RealmResults<Student>>() {
            @Override
            public void onChange(RealmResults<Student> students) {
                setAdapter(students);
            }
        });
    }

    private void setAdapter(RealmResults<Student> students) {
        ArrayAdapter<Student> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,students);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        /*realm.beginTransaction();
        int id=Integer.parseInt(etId.getText().toString());
        Student student = realm.createObject(Student.class,id);
        student.setFirstName(etFirstName.getText().toString());
        student.setLastName(etLastName.getText().toString());
        student.setMobile(Long.parseLong(etMobile.getText().toString()));
        realm.commitTransaction();
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();*/

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int id=Integer.parseInt(etId.getText().toString());
                Student student = realm.createObject(Student.class,id);
                student.setFirstName(etFirstName.getText().toString());
                student.setLastName(etLastName.getText().toString());
                student.setMobile(Long.parseLong(etMobile.getText().toString()));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(StudentActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //fetchStudents();
    }

    void updateStudent(){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int id=Integer.parseInt(etId.getText().toString());
                Student student=realm.where(Student.class).equalTo("id", id)
                        .findFirst();
                student.setFirstName(etFirstName.getText().toString());
                student.setLastName(etLastName.getText().toString());
                student.setMobile(Long.parseLong(etMobile.getText().toString()));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }
    void deleteStudent(){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int id=Integer.parseInt(etId.getText().toString());
                Student student=realm.where(Student.class).equalTo("id", id)
                        .findFirst();
                student.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }
}
