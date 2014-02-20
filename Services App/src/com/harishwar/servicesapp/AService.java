package com.harishwar.servicesapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
 
    private final AsService.Stub mBinder = new AsService.Stub() {
        
        public void setCallback(final AsServiceCallback callback, final String url) throws RemoteException {
           // Log.d("", "");
            callback.onDownloadFinished(DownloadUtil.downloadFile(getApplicationContext(), url));
        }
    };
}
