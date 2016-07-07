package com.jc.androidrs.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Test1Receiver extends BroadcastReceiver {
    public Test1Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String strMsg = getResultData();
        Toast.makeText(context, "Test1Receiver: "+strMsg, Toast.LENGTH_SHORT).show();
        setResultData("Test1Receiver---"+strMsg);
    }
}
