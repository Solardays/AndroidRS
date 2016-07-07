package com.jc.androidrs.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TestReceiver extends BroadcastReceiver {
    public TestReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String strMsg = getResultData();
        Toast.makeText(context, "TestReceiver: "+strMsg, Toast.LENGTH_SHORT).show();
        setResultData("TestReceiver--"+strMsg);
    }
}
