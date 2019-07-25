package com.example.makeaguess;

import android.app.Application;

import io.realm.Realm;

public class GameRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
