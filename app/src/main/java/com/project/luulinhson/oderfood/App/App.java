package com.project.luulinhson.oderfood.App;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Admin on 1/11/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
