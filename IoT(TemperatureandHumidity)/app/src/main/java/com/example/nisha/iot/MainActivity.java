package com.example.nisha.iot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_cancel = (Button) findViewById (R.id.button_2);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appClose();
            }
        });
        Button btn_set = (Button) findViewById (R.id.button_1);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcast();
            }
        });
    }
    //Entered values are sent through Broadcast
    private void broadcast(){
        EditText input_1 = (EditText) findViewById (R.id.editText_1);
        String temperature = input_1.getText().toString();
        Intent sender= new Intent();
        sender.putExtra("temperature", temperature);

        EditText input_2 = (EditText) findViewById (R.id.editText_2);
        String humidity = input_2.getText().toString();
        sender.putExtra("humidity", humidity);
        sender.setAction("MyIoTApp");

        sendOrderedBroadcast(sender, null);
   }
    //Close the Application
    private void appClose(){
        finish();
        System.exit(0);
    }
}
