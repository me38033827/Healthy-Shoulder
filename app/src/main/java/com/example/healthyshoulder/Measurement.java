package com.example.healthyshoulder;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.value;

public class Measurement extends AppCompatActivity {


    private DatabaseHelper databaseHelper;


    private BluetoothLeService mBluetoothLeService;
    private String deviceAddress="50:65:83:7C:AA:8F";

    private String PROTOCOL=":123=";
    byte[] WriteBytes = new byte[20];
    byte[] WriteBytes1 = new byte[20];

    String uuid = null;
    public static String FIRST = "0000ffe0-0000-1000-8000-00805f9b34fb";
    public static String SECOND= "0000ffe5-0000-1000-8000-00805f9b34fb";

    public static String CHAR1 = "0000ffe4-0000-1000-8000-00805f9b34fb";
    public static String CHAR2 = "0000ffe9-0000-1000-8000-00805f9b34fb";


    BluetoothGattCharacteristic characteristic1;
    BluetoothGattCharacteristic characteristic2;


    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Toast.makeText(Measurement.this,"未初始化",Toast.LENGTH_SHORT).show();
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            Toast.makeText(Measurement.this,"已初始化",Toast.LENGTH_SHORT).show();
            mBluetoothLeService.connect(deviceAddress);
            Toast.makeText(Measurement.this,"已连接",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);


        databaseHelper=new DatabaseHelper(this,"HealthyShoulder.db",null,1);

        Button button_sync =(Button)findViewById(R.id.button_sync);
        Button button_history=(Button)findViewById(R.id.button_history2);



        Button button=(Button)findViewById(R.id.button);


        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);




        button_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                Date date=new Date(System.currentTimeMillis());
                String sync_date=simpleDateFormat.format(date);

                TextView textView_index1=(TextView)findViewById(R.id.textView_index1);
                TextView textView_index2=(TextView)findViewById(R.id.textView_std_index2);
                TextView textView_index3=(TextView)findViewById(R.id.textView_index3);
                TextView textView_index4=(TextView)findViewById(R.id.textView_index4);
                TextView textView_index5=(TextView)findViewById(R.id.textView_index5);
                TextView textView_index6=(TextView)findViewById(R.id.textView_index6);

                Float index1=Float.parseFloat(textView_index1.getText().toString());
                Float index2=Float.parseFloat(textView_index2.getText().toString());
                Float index3=Float.parseFloat(textView_index3.getText().toString());
                Float index4=Float.parseFloat(textView_index4.getText().toString());
                Float index5=Float.parseFloat(textView_index5.getText().toString());
                Float index6=Float.parseFloat(textView_index6.getText().toString());


                SharedPreferences sharedPreferences=getSharedPreferences("HealthyShoulder",MODE_PRIVATE);
                int userId=sharedPreferences.getInt("loginId",0);




                SQLiteDatabase db=databaseHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put("user_id",userId);
                contentValues.put("index1",index1);
                contentValues.put("index2",index2);
                contentValues.put("index3",index3);
                contentValues.put("index4",index4);
                contentValues.put("index5",index5);
                contentValues.put("index6",index6);
                contentValues.put("syc_date",sync_date);
                db.insert("record",null,contentValues);






                Toast.makeText(Measurement.this,"同步成功！",Toast.LENGTH_SHORT).show();

            }
        });




        button_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Measurement.this,History.class);
                startActivity(intent);
            }
        });











        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Toast.makeText(Measurement.this,String.valueOf(mBluetoothLeService.getSupportedGattServices().size()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(Measurement.this,String.valueOf(mBluetoothLeService.mConnectionState),Toast.LENGTH_SHORT).show();

                List<BluetoothGattService> gattServices =mBluetoothLeService.getSupportedGattServices();

//                Toast.makeText(Measurement.this,String.valueOf(gattServices.size()),Toast.LENGTH_SHORT).show();
                if(gattServices==null||gattServices.size()<1){
                    Toast.makeText(Measurement.this,"null",Toast.LENGTH_SHORT).show();
                }else{

//                    Toast.makeText(Measurement.this,gattServices.size(),Toast.LENGTH_SHORT).show();
                    for (BluetoothGattService gattService : gattServices) {

                        List<BluetoothGattCharacteristic> gattCharacteristics =
                                gattService.getCharacteristics();

                        // Loops through available Characteristics.
                        for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                            uuid = gattCharacteristic.getUuid().toString();
                            if(CHAR1.equals(uuid)){
//                                Toast.makeText(Measurement.this,"1",Toast.LENGTH_SHORT).show();
                                characteristic1=gattCharacteristic;
                            }else if(CHAR2.equals(uuid)){
//                                Toast.makeText(Measurement.this,"2",Toast.LENGTH_SHORT).show();
                                characteristic2=gattCharacteristic;
                            }
                        }
                    }

//                    Toast.makeText(Measurement.this,String.valueOf(gattServices.size()),Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Measurement.this,characteristic1.getUuid().toString(),Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Measurement.this,characteristic2.getUuid().toString(),Toast.LENGTH_SHORT).show();
                }

                byte[] value = new byte[20];
                value[0] = (byte) 0x00;
                final int charaProp1 = BluetoothGattCharacteristic.PROPERTY_WRITE;
                final int charaProp2 = BluetoothGattCharacteristic.PROPERTY_NOTIFY;
                WriteBytes= PROTOCOL.getBytes();




                characteristic2.setValue(value[0],
                        BluetoothGattCharacteristic.FORMAT_UINT8, 0);
                characteristic2.setValue(WriteBytes);
                mBluetoothLeService.writeCharacteristic(characteristic2);



//                mBluetoothLeService.setCharacteristicNotification(
//                        characteristic2, true);


//
//
//                characteristic1.setValue(value[0],
//                        BluetoothGattCharacteristic.FORMAT_UINT8, 0);
//                characteristic1.setValue(WriteBytes);
//                mBluetoothLeService.writeCharacteristic(characteristic1);

                mBluetoothLeService.setCharacteristicNotification(
                        characteristic1, true);






//                Toast.makeText(Measurement.this,characteristic1.getUuid().toString()+"por="+charaProp1,Toast.LENGTH_SHORT).show();


//                Toast.makeText(Measurement.this,characteristic2.getUuid().toString()+"por="+charaProp2,Toast.LENGTH_SHORT).show();

//                characteristic2.setValue(value[0],BluetoothGattCharacteristic.FORMAT_UINT8, 0);
//
//
//                characteristic2.setValue(WriteBytes);
//
//                mBluetoothLeService.writeCharacteristic(characteristic2);


            }
        });


    }


    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
//                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
//                Toast.makeText(Measurement.this,"1",Toast.LENGTH_SHORT).show();
                Toast.makeText(Measurement.this,intent.getStringExtra(BluetoothLeService.EXTRA_DATA),Toast.LENGTH_SHORT).show();
            }
        }
    };



    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());

        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(deviceAddress);
        }
    }


    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
}
