package com.example.nikhil.blue;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
public Button addsen_btn,mansen_btn,help_btn,conn_btn;
    private static final int REQUEST_ENABLE_BT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        addsen_btn=(Button) findViewById(R.id.add_sensor_id);
        mansen_btn=(Button) findViewById(R.id.manage_sensor_id);
        help_btn=(Button) findViewById(R.id.help_id);


        addsen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Addsensor.class);
                startActivity(i);
            }
        });

        mansen_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),managesensor.class);
                startActivity(i);
            }
        });

        help_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
//                conn_to_bt();

                Intent i=new Intent(getApplicationContext(),help.class);
                startActivity(i);
            }
        });
    }

    private String getBTMajorDeviceClass(int major){
        switch(major){
            case BluetoothClass.Device.Major.AUDIO_VIDEO:
                return "AUDIO_VIDEO";
            case BluetoothClass.Device.Major.COMPUTER:
                return "COMPUTER";
            case BluetoothClass.Device.Major.HEALTH:
                return "HEALTH";
            case BluetoothClass.Device.Major.IMAGING:
                return "IMAGING";
            case BluetoothClass.Device.Major.MISC:
                return "MISC";
            case BluetoothClass.Device.Major.NETWORKING:
                return "NETWORKING";
            case BluetoothClass.Device.Major.PERIPHERAL:
                return "PERIPHERAL";
            case BluetoothClass.Device.Major.PHONE:
                return "PHONE";
            case BluetoothClass.Device.Major.TOY:
                return "TOY";
            case BluetoothClass.Device.Major.UNCATEGORIZED:
                return "UNCATEGORIZED";
            case BluetoothClass.Device.Major.WEARABLE:
                return "AUDIO_VIDEO";
            default: return "unknown!";
        }
    }



    public void conn_to_bt()
    {
        ArrayAdapter<String> btArrayAdapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        BluetoothAdapter bluetoothAdapter
                = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices
                = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                String deviceBTName = device.getName();
                String deviceBTMajorClass
                        = getBTMajorDeviceClass(device
                        .getBluetoothClass()
                        .getMajorDeviceClass());
                String deviceid=device.getAddress();
                btArrayAdapter.add(deviceBTName + "\n"
                        + deviceBTMajorClass+"\n"+deviceid);

            }
        }

        for(int i=0;i<btArrayAdapter.getCount();i++)
        {
            System.out.println(btArrayAdapter.getItem(i));
        }
        System.out.println(isBluetoothHeadsetConnected());
    }
    public static boolean isBluetoothHeadsetConnected() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()
                && mBluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED;
    }




}
