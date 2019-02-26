package com.vsts.willl.demovsts;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String test = intent.getStringExtra("test");
        Log.e("线程："+Thread.currentThread().getName()+" onStartCommand: ",test);
    }
}
