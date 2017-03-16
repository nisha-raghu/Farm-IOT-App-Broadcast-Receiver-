package com.example.nisha.iotfarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver Maker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Maker = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent receiver){
                // Data is received using the keys "temperature" and "humidity"
                String tem = receiver.getStringExtra("temperature");
                String hum= receiver.getStringExtra("humidity");
                TextView temperature = (TextView) findViewById(R.id.text_1);
                TextView humidity = (TextView) findViewById(R.id.text_2);
                // Set Text of temp and humidity received
                temperature.setText("Temperature: " + tem);
                humidity.setText("Humidity: "+ hum);
                Toast.makeText(MainActivity.this, "Broadcast Temperature:"+tem+" Humidity:"+hum, Toast.LENGTH_SHORT).show();
                abortBroadcast();
            }
        };
        Button fan_btn = (Button) findViewById(R.id.button_1);
        fan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fanStatusSender();
            }
        });
        Button fanSprnk_btn = (Button) findViewById(R.id.button_2);
        fanSprnk_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fanAndSprinklerSender();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(Maker, new IntentFilter("MyIoTApp"));
    }

    private int x = -1;
    private int y = -1;
    //Send Fan status by Broadcast
    private void fanStatusSender(){
        String fanData = String.valueOf(x = x * -1);
        Intent intent1= new Intent();
        intent1.putExtra("fan", fanData);
        intent1.setAction("MyIoTApp_Status");
        sendOrderedBroadcast(intent1, null);
    }
    //Send Fan & Sprinkler status by Broadcast
    private void fanAndSprinklerSender(){
        String fanSprinklerData = String.valueOf(y = y * -1);
        Intent intent2= new Intent();
        intent2.putExtra("spr", fanSprinklerData);
        intent2.setAction("MyIoTApp_Status");
        sendOrderedBroadcast(intent2, null);
    }

}


