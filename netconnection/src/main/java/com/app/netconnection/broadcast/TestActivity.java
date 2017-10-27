package com.app.netconnection.broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.a1merbng.tempdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**Android系统中的广播（Broadcast）
 * http://blog.csdn.net/luoshengyang/article/details/6730748
 */
public class TestActivity extends AppCompatActivity {
    @BindView(R.id.button_start)
    Button startButton;
    @BindView(R.id.button_stop)
    Button stopButton;
    @BindView(R.id.textview_counter)
    TextView counterText;
    private ICounterService counterService = null;
    private static final String LOG_TAG = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        Intent intent = new Intent(this, CounterService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.e(LOG_TAG, "Main Activity Created.");
    }

    @OnClick({R.id.button_start, R.id.button_stop})
    void click(View view) {
        if (view.getId() == R.id.button_start) {
            if (counterService != null) {
                counterService.startCounter(0);
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                Log.e(LOG_TAG, "button_start");
            }
        } else if (view.getId() == R.id.button_stop) {
            counterService.stopCounter();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            Log.e(LOG_TAG, "button_stop");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(CounterService.BROADCAST_COUNTER_ACTION);
        registerReceiver(counterActionReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }

    private BroadcastReceiver counterActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getIntExtra(CounterService.COUNTER_VALUE, 0);
            counterText.setText(counter + "");
            Log.e(LOG_TAG, "Receive counter event");
        }
    };
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            counterService = ((CounterService.CounterBinder) iBinder).getService();
            Log.e(LOG_TAG, "Counter Service Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            counterService = null;
            Log.e(LOG_TAG, "Counter Service Disconnected");
        }
    };
}
