package com.pachesoft.androidville;

import android.app.Application;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Flowable.just("____________Hello world").subscribe(new Consumer<String>() {
            @Override public void accept(String s) {
                System.out.println(s);
            }
        });
    }

}
