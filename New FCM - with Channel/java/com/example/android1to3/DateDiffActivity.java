package com.example.android1to3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateDiffActivity extends AppCompatActivity {
    EditText etDate1, etDate2;
    TextView tvMessage;
    Button btnCalculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_diff);

        btnCalculate=findViewById(R.id.btnCalculate);
        etDate1=findViewById(R.id.etDate1);
        etDate2=findViewById(R.id.etDate2);
        tvMessage=findViewById(R.id.tvMessage);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateStart = etDate1.getText().toString();
                String dateStop = etDate2.getText().toString();

                //SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                /*SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
                Date d1 = null;
                Date d2 = null;
                try {
                    d1 = format.parse(dateStart);
                    d2 = format.parse(dateStop);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diff = d2.getTime() - d1.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000);
                int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
                String msg=diffInDays+"\n"+
                        diffHours+"\n"+
                        diffMinutes+"\n"+
                        diffSeconds+"\n";
                tvMessage.setText(msg);*/
                LocalDate today = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    today = LocalDate.now();

                    LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);

                    Period p = Period.between(birthday, today);
                    long p2 = ChronoUnit.DAYS.between(birthday, today);
                    System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                            " months, and " + p.getDays() +
                            " days old. (" + p2 + " days total)");
                }
                /*if (diffInDays > 1) {
                    System.err.println("Difference in number of days (2) : " + diffInDays);

                } else if (diffHours > 24) {

                    System.err.println(">24");

                } else if ((diffHours == 24) && (diffMinutes >= 1)) {
                    System.err.println("minutes");

                }*/

            }
        });

    }
}
