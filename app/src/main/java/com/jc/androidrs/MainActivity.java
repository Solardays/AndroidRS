package com.jc.androidrs;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jc.androidrs.service.MyService;
import com.jc.androidrs.service.TestService;

public class MainActivity extends AppCompatActivity {

    private final String ACTION = "com.example.broadcastreceivertest.TEST";
    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String strMsg = getResultData();
            Toast.makeText(getApplicationContext(), "接收成功:" + strMsg, Toast.LENGTH_SHORT).show();
            setResultData("MainActivity---" + strMsg);
        }
    };

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("TestService", "onServiceConnected");
            TestService.TestBinder testBinder = (TestService.TestBinder) service;
            testBinder.test();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("TestService", "onServiceDisconnected");
        }
    };

    ServiceConnection conn2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("TestService", "onServiceConnected");
            MyAIDLService myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int plus = myAIDLService.plus(3, 2);
                Log.i("TestService", "plus: " + plus);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("TestService", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter(ACTION);
        intentFilter.setPriority(1000);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void sendcast(View view) {
        Intent intent = new Intent();
        intent.setAction(ACTION);
//        sendOrderedBroadcast(intent,null,null,null,0,"你好啊",null);
        sendOrderedBroadcast(intent, null);
    }

    public void startServ(View view) {
        Intent intent = new Intent(this, TestService.class);
        startService(intent);
    }

    public void stopServ(View view) {
        Intent intent = new Intent(this, TestService.class);
        stopService(intent);
    }

    public void bindServ(View view) {
        Intent intent = new Intent(this, TestService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        unbindService(conn);
    }

    public void bindRomoteServ(View view) {
        Intent intent = new Intent();
        intent.setAction("com.jc.androidrs.MyAIDLService");
        bindService(intent, conn2, BIND_AUTO_CREATE);
    }


}
