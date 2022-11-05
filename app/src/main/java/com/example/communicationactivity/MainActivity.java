package com.example.communicationactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button but1;
    private Button but2;
    private EditText edtMessage;
    ActivityResultLauncher<Intent> lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    Intent intent = result.getData(); //retourne les données sous
                            switch(resultCode){
                                case RESULT_OK :
                                    Toast.makeText(MainActivity.this, "Action validée", Toast.LENGTH_LONG).show();
                                    return;
                                case RESULT_CANCELED :
                                    Toast.makeText(MainActivity.this, "Action annulée", Toast.LENGTH_LONG).show();
                                    return;
                                default:
//des instructions à faire
                                    return;
                            }

                }});
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but1 = (Button) findViewById(R.id.btnActivity1);
        but2 = (Button) findViewById(R.id.btnActivity2);
        edtMessage = (EditText) findViewById(R.id.edtMessage);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent:
                Intent MyIntent1 = new Intent( MainActivity.this,Example1Activity.class);
                //****************************************************************************

                // Put parameters
               // Put parameters sing Bundle
 /*                MyIntent1.putExtra("text1","This text1 sent from MainActivity at"+ new Date());
                MyIntent1.putExtra("Message",edtMessage.getText().toString() );
*/
                //****************************************************************************

                // Put parameters sing Bundle
                Bundle myBundle = new Bundle();
                myBundle.putString("text1","This text1 sent from MainActivity at"+ new Date());
                myBundle.putString("Message", edtMessage.getText().toString());
                MyIntent1.putExtras(myBundle);

                //****************************************************************************

                //StartExample1Activity
                startActivity(MyIntent1);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the ReturnResultActivity
                Intent intent = new Intent(MainActivity.this,
                        ReturnResultActivity.class);
                lanceur.launch(intent);
                //startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });





    }

    public void TestImplicit(View view){

        //To Force Android OS to show all apps that could implement
//the implicit intent even if there is a default app already defined
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("sms:12345"));
        intent.putExtra("sms_body","Hello");
        Intent i = Intent.createChooser(intent,"Choose App");
        startActivity(i);

    }


    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK: {
                    Toast.makeText(this, "Action validée",
                            Toast.LENGTH_LONG).show();
                    // get String data from Intent
                    String returnString = data.getStringExtra("keyName");
                    // set text view with string
                    edtMessage.setText(returnString);
                }
                case RESULT_CANCELED: {
                    Toast.makeText(this, "Action annulée",
                            Toast.LENGTH_LONG).show();
                    // get String data from Intent
                    String returnString = data.getStringExtra("keyName");
                    // set text view with string
                    edtMessage.setText(returnString);
                }
            }}}
}