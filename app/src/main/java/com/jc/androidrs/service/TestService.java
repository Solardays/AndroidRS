package com.jc.androidrs.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.jc.androidrs.MainActivity;
import com.jc.androidrs.R;

public class TestService extends Service {
    String Tag = "TestService";

    TestBinder testBinder = new TestBinder();

    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(Tag,"onBind");
        return testBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        // 通过Notification.Builder来创建通知，注意API Level
        // API11之后才支持
        Notification notify2 = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示，如果在那里需要更换更大的图片，可以使用setLargeIcon(Bitmap
                // icon)
                .setTicker("TickerText:" + "您有新短消息，请注意查收！")// 设置在status
                // bar上显示的提示文字
                .setContentTitle("Notification Title")// 设置在下拉status
                // bar后Activity，本例子中的NotififyMessage的TextView中显示的标题
                .setContentText("This is the notification message")// TextView中显示的详细内容
                .setContentIntent(pendingIntent2) // 关联PendingIntent
                .setNumber(1) // 在TextView的右方显示的数字。这个number同时也起到一个序列号的左右，如果多个触发多个通知（同一ID），可以指定显示哪一个。
                .getNotification(); // 需要注意build()是在API level
        // 16及之后增加的，在API11中可以使用getNotificatin()来代替
        notify2.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, notify2);
        Log.i(Tag,"onCreate");
        startForeground(0,notify2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(Tag,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Tag,"onDestroy");
    }


    public class TestBinder extends Binder{
        public TestBinder() {
            Log.i(Tag,"TestBinder");
        }

        public void test() {
            Log.i(Tag,"test");
        }
    }
}
