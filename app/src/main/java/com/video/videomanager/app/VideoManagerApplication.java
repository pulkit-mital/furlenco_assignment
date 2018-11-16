package com.video.videomanager.app;

import android.app.Application;
import android.content.Context;

import com.video.videomanager.data.Pref;

public class VideoManagerApplication extends Application {

    private static Pref pref;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }

    public static Context getAppContext(){
        return context;
    }

    /**
     * to access shared preference and get stored persistent data.
     * @return
     */
    public static Pref getPref(){
        if(pref == null){
            pref = new Pref(getAppContext());

        }
        return pref;
    }
}
