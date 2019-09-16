package com.example.android1to3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        int match_parent= LinearLayout.LayoutParams.MATCH_PARENT;
        int wrap_content= LinearLayout.LayoutParams.WRAP_CONTENT;
        ll.setLayoutParams(new LinearLayout.LayoutParams(match_parent, match_parent));
        Button btnHello=new Button(this);
        btnHello.setLayoutParams(new LinearLayout.LayoutParams(wrap_content,wrap_content));
        btnHello.setText("Hello");

        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        ll.addView(btnHello);


        setContentView(ll);
    }
}
