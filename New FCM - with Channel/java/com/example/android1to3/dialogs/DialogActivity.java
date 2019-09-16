package com.example.android1to3.dialogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android1to3.R;

import java.util.Calendar;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDate, btnTime, btnPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("My App");

        btnPopup=findViewById(R.id.btnPopup);
        btnDate=findViewById(R.id.btnDate);
        btnTime=findViewById(R.id.btnTime);

        registerForContextMenu(btnDate);

        btnPopup.setOnClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.test, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_file:
                Toast.makeText(this, "File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cut:
                Toast.makeText(this, "Cut", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_copy:
                Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        // Condition
        //MenuItem item=menu.findItem(R.id.action_file);
        //item.setVisible(false);
        //==============
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_file:
                Toast.makeText(this, "File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cut:
                Toast.makeText(this, "Cut", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_copy:
                Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(View view) {
        Context context=this;
        String msg="This is Toast";
        int duration=Toast.LENGTH_LONG;
        //Toast toast=Toast.makeText(context, msg, duration);
        Toast toast=new Toast(context);
        View toastView=getLayoutInflater().inflate(R.layout.toast_view, null);
        TextView tvMessage=toastView.findViewById(R.id.tvMessage);
        tvMessage.setText(msg);

        toast.setView(toastView);
        toast.setDuration(duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showAlertDialog(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        View toastView=getLayoutInflater().inflate(R.layout.toast_view, null);
        builder.setView(toastView);

        builder.setTitle("This is Title");
        builder.setMessage("This is Message");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this, "Yes is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this, "No is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this, "Cancel is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public void datePicker(View view) {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                btnDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },year ,month ,dayOfMonth);

        dialog.getDatePicker().setMinDate(System.currentTimeMillis());
        dialog.show();
    }

    public void timePicker(View view) {
        Calendar calendar=Calendar.getInstance();
        int hourOfDay=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                btnTime.setText(hour+":"+minute);
            }
        }, hourOfDay, minute,false);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        PopupMenu popupMenu=new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.test, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_file:
                        Toast.makeText(getApplicationContext(), "File", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_cut:
                        Toast.makeText(getApplicationContext(), "Cut", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_copy:
                        Toast.makeText(getApplicationContext(), "Copy", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
