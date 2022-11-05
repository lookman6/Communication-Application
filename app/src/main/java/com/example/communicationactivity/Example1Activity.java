package com.example.communicationactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Example1Activity extends AppCompatActivity {


    private Button butBack;
    private TextView textView;
    private TextView textMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);
        butBack = (Button) findViewById(R.id.btn1);
        textView = (TextView) findViewById(R.id.textView);
        textMessage = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        //****************************************************************************
        // Get the intent sent from MainActivity.

        // Parameter in Intent, sent from MainActivity
 /*       String value1 = intent.getStringExtra("text1");
        // Parameter in Intent, sent from MainActivity
        String Message = intent.getStringExtra("Message");
        this.textView.setText(Message);
        this.textMessage.setText(value1);*/
        //****************************************************************************

       //Get the Bundle object
        Bundle myBundle = getIntent().getExtras();
        String value1 = myBundle.getString("text1");
        String Message = myBundle.getString("Message");
        this.textView.setText(value1);
        this.textMessage.setText(Message);

        //****************************************************************************

        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Back to previous Activity.
                 Example1Activity.this.finish();
            }
        });

    }
}
