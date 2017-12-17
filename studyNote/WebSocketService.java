package org.manbucloud.app.app.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.manbucloud.app.R;
import org.manbucloud.app.app.Config;
import org.manbucloud.app.app.FunHotelApplication;
import org.manbucloud.app.app.utils.CacheUtil;
import org.manbucloud.app.app.utils.LogUtil;
import org.manbucloud.app.app.utils.SPUtil;
import org.manbucloud.app.app.utils.StringUtil;
import org.manbucloud.app.app.utils.SystemUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UpdateService extends Service {
    public final static String UPDATE_TYPE_APP = "update_app";
    public final static String UPDATE_TYPE_TV = "update_tv";
    public final static String UPDATE_TYPE_DLNA = "update_DLNA";
    private boolean isExcuted = true;
    private FileDownloader fileDownLoader;
    private HashMap<String, String> taskQueue;
    private List<String> currentTask;
    private String tvPackageName;
    WebSocketClient client;

    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        try {
            client = new WebSocketClient(new URI("ws://jp.8fet.com:7272")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    LogUtil.log.e("开启连接");
                }

                @Override
                public void onMessage(String s) {
                    LogUtil.log.e(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    LogUtil.log.e(i + " - " + s + " - " + b);
                }

                @Override
                public void onError(Exception e) {
                    LogUtil.log.e(e.getMessage());
                }
            };
            new Thread(new Runnable() {
                @Override
                public void run() {
                    client.connect();
                }
            }).start();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendMsg("{\"type\":\"get_auctions\",\"for_new\":0,\"mid\":0,\"page\":1,\"per_page\":10,\"cid\":0}");
            }
        }).start();
        currentTask = new ArrayList<>();
        taskQueue = new HashMap<>();
        fileDownLoader = FileDownloader.getImpl();
        fileDownLoader.setMaxNetworkThreadCount(6);
        excutedDeleteInavlidFile();
    }

    private void sendMsg(String msg) {
        LogUtil.log.e("正在向服务器发送：" + msg);
        try {
            client.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMessage(final Intent intent) {
        if (intent.getAction() == null) {
            return;
        }
        switch (intent.getAction()) {
            case Config.MESSAGE_UPDATE_FILE:
                String tag = intent.getStringExtra("tag");
                String url = intent.getStringExtra("url");
                if (intent.getExtras().containsKey("packageName")) {
                    tvPackageName = intent.getStringExtra("packageName");
                }
                //  如果删除任务正在执行就将任务放入执行队列，反之则直接执行下载
                if (isExcuted) {
                    taskQueue.put(tag, url);
                } else {
                    down(tag, url);
                }
                break;
            default:
                break;
        }
    }

    private void excutedDeleteInavlidFile() {
        isExcuted = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                CacheUtil.deleteInavlidCache();
                isExcuted = false;
                Iterator iterator = taskQueue.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    down((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }).start();
    }

    private void down(final String tag, final String url) {
        if (currentTask.contains(tag)) {
            return;
        }
        currentTask.add(tag);
        final String filePath = FunHotelApplication.getInstance().getCachePath() + StringUtil.cutFileNameByURL(url);
        if (filePath.trim().length() == 0) {
            return;
        }
        fileDownLoader.create(url)
                .setAutoRetryTimes(2)
                .setPath(filePath)
                .setForceReDownload(true)
                .setCallbackProgressTimes(0)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        if (tag.equals(UPDATE_TYPE_APP)) {
                            showTips(R.string.update_tip);
                            SystemUtil.install(FunHotelApplication.getInstance().getApplicationContext(), filePath);
                        } else if (tag.equals(UPDATE_TYPE_TV)) {
                            showTips(R.string.ready_to_install_tv);
                            SystemUtil.install(FunHotelApplication.getInstance().getApplicationContext(), filePath, tvPackageName);
                        } else if (tag.equals(UPDATE_TYPE_DLNA)) {
                            showTips(R.string.ready_to_install_dlna);
                            SystemUtil.installDlna(FunHotelApplication.getInstance().getApplicationContext(), filePath);
                        } else {
                            String content = url + "," + filePath;
                            SPUtil.getInstance().saveVideoPath(tag, content);
                        }
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }
                }).start();
    }

    private void showTips(final int content) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(FunHotelApplication.getInstance().getApplicationContext(), content, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void startThis(Context context) {
        context.startService(new Intent(context, UpdateService.class));
    }
}
