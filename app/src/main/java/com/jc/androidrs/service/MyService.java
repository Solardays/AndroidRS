package com.jc.androidrs.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.jc.androidrs.MyAIDLService;

/**
 * 远程server
 */
public class MyService extends Service {
    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str != null) {
                return str.toUpperCase();
            }
            return null;
        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }
    };


}
