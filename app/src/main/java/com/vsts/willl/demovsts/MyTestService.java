package com.vsts.willl.demovsts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyTestService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, final int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringExtra = intent.getStringExtra("test");
                if(stringExtra.equals("stop")){
                    MyTestService.this.stopSelf(startId);
                }
                Log.e("线程："+Thread.currentThread().getName()+" onStartCommand: ",stringExtra);
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
