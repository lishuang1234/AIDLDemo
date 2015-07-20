package com.baidu_lishuang10.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by baidu_lishuang10 on 15/7/15.
 */
public class AidlService extends Service {
    private CatBinder catBinder=new CatBinder();
    private Timer timer = new Timer();
    private String color;
    private double weight;
    private String[] colors = {"红", "黄", "蓝"};
    private double[] weights = {1.2, 2.4, 3.6};

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return catBinder;
    }

    private class CatBinder extends ICat.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getColor() throws RemoteException {
            return color;
        }

        @Override
        public double getWeight() throws RemoteException {
            return weight;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int random = (int) (Math.random() * 3);
                color = colors[random];
                weight = weights[random];
            }
        }, 0, 800);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
