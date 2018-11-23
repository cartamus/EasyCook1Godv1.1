package com.example.intertec.easycook.App;

import android.app.Application;
import android.os.SystemClock;

public class EasyCook extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
