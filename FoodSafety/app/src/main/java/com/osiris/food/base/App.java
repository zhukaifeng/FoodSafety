package com.osiris.food.base;


import android.app.Application;

import io.realm.Realm;

public class App extends Application {
    public final static String EXTRA_UNCAUGHT_EXCEPTION = "uncaughtException";
    public static App instance;
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
    }
}
