package com.app.netconnection.broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**计数器服务
 * Created by merbng on 2017/10/28.
 */

public class CounterService extends Service implements ICounterService {
    private static final String LOG_TAG = "CounterService";
    private IBinder binder = new CounterBinder();
    private boolean stop = false;
    public static final String COUNTER_VALUE = "COUNTER_VALUE";
    public static final String BROADCAST_COUNTER_ACTION = "BROADCAST_COUNTER_ACTION";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(LOG_TAG, "Counter Service Created.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG, "Counter Service Destroyed.");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void startCounter(int initVal) {
        AsyncTask<Integer, Integer, Integer> task = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... vals) {
                Integer initCounter = vals[0];

                stop = false;
                while(!stop) {
                    publishProgress(initCounter);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    initCounter++;
                }

                return initCounter;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);

                int counter = values[0];

                Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
                intent.putExtra(COUNTER_VALUE, counter);

                sendBroadcast(intent);
            }

            @Override
            protected void onPostExecute(Integer val) {
                int counter = val;

                Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
                intent.putExtra(COUNTER_VALUE, counter);

                sendBroadcast(intent);
            }

        };

        task.execute(0);
    }

    @Override
    public void stopCounter() {
        stop = true;
    }

    public class CounterBinder extends Binder {
        public CounterService getService() {
            return CounterService.this;
        }
    }
}
