package com.example.shugan.myapplicationalarm;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {

    ServiceConnection m_service;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
    private ServiceConnection m_serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            m_service = (ServiceConnection) ((MyBinder)service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            m_service = null;
        }
    };
}