package com.example.intertec.easycook.App;

import android.app.Application;
import android.os.SystemClock;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class EasyCook extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }
}
