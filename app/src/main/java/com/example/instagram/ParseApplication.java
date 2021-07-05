package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("XAjBXlbOyhUJPOTqewimHm3621KCwYZKalpXuhk9")
                .clientKey("yvb5A3KokfOrW5j4yosMi7q0cbCtm9N9d6LTmGQ3")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
