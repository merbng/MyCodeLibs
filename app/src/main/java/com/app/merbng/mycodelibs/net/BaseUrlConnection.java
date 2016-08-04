package com.app.merbng.mycodelibs.net;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.app.merbng.mycodelibs.interfaces.GetCallBack;
import com.app.merbng.mycodelibs.utils.DateUtils;
import com.app.merbng.mycodelibs.utils.StringUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by taojunbin on 2015/9/6.
 */
public class BaseUrlConnection {
    private final static String TAG = "===BaseUrlConnection===";
    private final static int MAXTHREAD = 3;
    private final static int UPLOADMAXTHREAD = 3;
    //  维持每个空闲线程的时间
    private final static int POOLWAITTIME = 3;
    //  连接时间阀值为7秒
    private final static int CONNETCIONTIMEOUT = 7 * 1000;
    //  上传连接时间阀值为30秒
    private final static int UPLOADCONNETCIONTIMEOUT = 30 * 1000;
    //  连接方法
    private final static String GETMETHOD = "GET";
    private final static String POSTMETHOD = "POST";
    private final static String PUTMETHOD = "PUT";
    private final static String DELETEMETHOD = "DELETE";
    //  提示
    private final static String CONNECTION_FAIL_ERROR = "连接异常";
    private final static String CONNECTION_FAIL_NONE = "无数据返回";
    private final static String CONNECTION_PARSE_ERROR = "数据解析异常";
    //  单例
    private static BaseUrlConnection baseUrlConnection;
    //  线程池
    private static ExecutorService threadPool;
    //  上传图片线程池
    private static ExecutorService uploadPool;
    //  是否取消回调
    private static boolean isContinue;

    /*其他*//*
    // 每个post参数之间的分隔。随意设定，只要不会和其他的字符串重复即可。
    private static final String BOUNDARY = "----------HV2ymHFg03ehbqgZCaKO6jyH";
    // 头
    String boundary = BOUNDARY;
    // 传输内容
    StringBuffer contentBody = new StringBuffer("--" + BOUNDARY);
    // 尾
    String endBoundary = "\r\n--" + boundary + "--\r\n";
    *//*其他*/

    //  创建单例对象
    public static BaseUrlConnection getInstance() {
        isContinue = true;
        if (baseUrlConnection == null) {
            baseUrlConnection = new BaseUrlConnection();
        }
        if (uploadPool == null) {
            uploadPool = new ThreadPoolExecutor(UPLOADMAXTHREAD, UPLOADMAXTHREAD, POOLWAITTIME,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                    new ThreadPoolExecutor.DiscardPolicy());
        }
        if (threadPool == null) {
            threadPool = new ThreadPoolExecutor(MAXTHREAD, MAXTHREAD, POOLWAITTIME,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                    new ThreadPoolExecutor.DiscardPolicy());
        }
        return baseUrlConnection;
    }

    //  初始化线程池
    private BaseUrlConnection() {
        uploadPool = new ThreadPoolExecutor(UPLOADMAXTHREAD, UPLOADMAXTHREAD, POOLWAITTIME,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardPolicy());
        threadPool = new ThreadPoolExecutor(MAXTHREAD, MAXTHREAD, POOLWAITTIME,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    //  取消回调，回调成功前有效
    public void cancle() {
        isContinue = false;
    }

    //  销毁
    public void destory() {
        threadPool.shutdown();
        uploadPool.shutdown();
        baseUrlConnection = null;
    }

    /**
     * GET方法从服务器获取数据
     * 用于查询
     *
     * @param address  地址
     * @param params   参数集合
     * @param callback 返回数据的回调接口
     */
    public void getConnection(final String address, final HashMap<String, Object> params, final GetCallBack.CallBack callback) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("", "-->beginConnection: " + DateUtils.dateToString(new Date()));
                URL url;
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                StringBuilder addressUrl = new StringBuilder(address);
                try {
                    if (params != null) {
                        addressUrl.append("?");
                        for (HashMap.Entry<String, Object> entry : params.entrySet()) {
                            addressUrl.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue() + "", "UTF-8")).append('&');
                        }
                        addressUrl.deleteCharAt(addressUrl.length() - 1);
                        url = new URL(addressUrl.toString());
                    } else {
                        url = new URL(address);
                    }
                    trustAllHosts();
                    HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                    if (url.getProtocol().toLowerCase().equals("https")) {
                        https.setHostnameVerifier(DO_NOT_VERIFY);
                        connection = https;
                    } else {
                        connection = (HttpURLConnection) url.openConnection();
                    }
                    Log.i("", "-->获取链接url: " + url);
                    connection.setRequestMethod(GETMETHOD);
                    connection.setDoInput(true);
                    connection.setConnectTimeout(CONNETCIONTIMEOUT);
                    connection.setReadTimeout(CONNETCIONTIMEOUT);
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestProperty("SCApplication-Id", "application/json");
                    connection.setRequestProperty("SCApplication-Key", "application/json");
                    connection.setRequestProperty("Accept-Encoding", "gzip");
                    connection.setRequestProperty("device", "android");
                    connection.setRequestProperty("userId", "");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.connect();
                    if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 400 && isContinue == true) {
                        if (connection.getContentEncoding() != null && connection.getContentEncoding().contentEquals("gzip")) {
                            inputStream = new GZIPInputStream(connection.getInputStream());
                        } else {
                            inputStream = connection.getInputStream();
                        }
                        final String result = StringUtils.inputStream2String(inputStream);
                        Handler mHandler = new Handler(Looper.getMainLooper());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (result != null && result.length() > 0) {
                                    callback.onSuccess(result);
                                } else {
                                    callback.onFail(CONNECTION_FAIL_NONE);
                                }

                            }
                        });
                    } else if (isContinue == true) {
                        inputStream = connection.getErrorStream();
                        final String result = StringUtils.inputStream2String(inputStream);
                        JSONObject object = new JSONObject(result);
                        final String errorMsg = object.getString("message");
                        Handler mHandler = new Handler(Looper.getMainLooper());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFail(errorMsg);
                            }
                        });
                        Log.e(TAG, errorMsg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFail(CONNECTION_FAIL_ERROR);
                        }
                    });
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
