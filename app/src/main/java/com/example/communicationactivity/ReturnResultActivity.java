package com.example.communicationactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnResultActivity extends AppCompatActivity {

    private TextView textViewMessage;
    private EditText edtMessageBack;
    private Button btnOk;
    private Button btnCancel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnresult);

        this.textViewMessage = (TextView) this.findViewById(R.id.textViewMessage);
        this.edtMessageBack = (EditText) this.findViewById(R.id.edtMessageBack);
        this.btnOk = (Button) this.findViewById(R.id.btnOK);
        this.btnCancel=(Button) findViewById(R.id.btnCancel);

        Intent MyIntent = getIntent();
        String Message = MyIntent.getStringExtra("Message");
        textViewMessage.setText(Message);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the text from the EditText
                edtMessageBack = (EditText) findViewById(R.id.edtMessageBack);
                String stringToPassBack = edtMessageBack.getText().toString();
// put the String to pass back into an Intent and close this activity
                Intent intent = new Intent();
                intent.putExtra("keyName", stringToPassBack);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("keyName", "Action Cancelled");
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }

}
