package com.app.merbng.mycodelibs.base;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringDef;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * Created by taojunbin on 2016/7/13.
 * Role:
 */
public class Connect {
    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String DELETE = "DELETE";
    public final static String PUT = "PUT";
    private final static int TIMEOUT = 10 * 1000;
    private String url;
    private String method;
    private RequestParams params;
    private IRequestBack callBack;
    private Handler mHanlder;

    public Connect() {
        mHanlder = new Handler(Looper.getMainLooper());
    }

    @StringDef({GET, PUT, POST, DELETE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Method {
    }

    public Connect setMethod(@Method String method) {
        this.method = method;
        return this;
    }

    public Connect setParams(RequestParams params) {
        this.params = params;
        return this;
    }

    public Connect setUrl(String url) {
        this.url = url;
        return this;
    }

    public Connect setListener(IRequestBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public void cancel() {
        mHanlder.removeCallbacksAndMessages(null);
        callBack = null;
    }

    public String work() {
        String result = null;
        if (method.equals(GET)) {
            try {
                url = createGetString();
            } catch (UnsupportedEncodingException e) {
                throwException(callBack, e);
            }
        } else if (method.equals(DELETE)) {
            try {
                url = createDeleteString();
            } catch (UnsupportedEncodingException e) {
                throwException(callBack, e);
            }
        }
        HttpURLConnection connection = null;
        try {
            connection = createConnectHead();
        } catch (IOException e) {
            throwException(callBack, e);
        }
        if (connection != null) {
            InputStream inputStream = null;
            DataOutputStream dataOutputStream = null;
            try {
                if (method.equals(PUT)
                        || method.equals(POST)) {
                    if (params != null) {
                        dataOutputStream = new DataOutputStream(connection
                                .getOutputStream());
                        JSONObject object = new JSONObject(params.getValue());
                        dataOutputStream.write(object.toString().getBytes("UTF-8"));
                        dataOutputStream.flush();
                        dataOutputStream.close();
                    }
                }
                int requestCode = connection.getResponseCode();
                if (requestCode >= HttpURLConnection.HTTP_OK
                        && requestCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                    if (connection.getContentEncoding() != null && connection.getContentEncoding().contentEquals("gzip")) {
                        inputStream = new GZIPInputStream(connection.getInputStream());
                    } else {
                        inputStream = connection.getInputStream();
                    }
                    result = ins2Str(inputStream);
                    throwSuccess(callBack, result);
                } else {
                    inputStream = connection.getErrorStream();
                    JSONObject object = new JSONObject(ins2Str(inputStream));
                    result = object.getString("message");
                    throwException(callBack, new Exception(result));
                }
            } catch (Exception e) {
                throwException(callBack, e);
            } finally {
                connection.disconnect();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        throwException(callBack, e);
                    }
                }
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        throwException(callBack, e);
                    }
                }
            }
        } else {
            throwException(callBack, new Exception("The HttpUrlConnection is null!"));
        }
        return result;
    }

    private HttpURLConnection createConnectHead() throws IOException {
        if (checkString(url)) {
            URL address = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) address.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setReadTimeout(TIMEOUT);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setChunkedStreamingMode(10 * 1024);
            return urlConnection;
        } else {
            return null;
        }
    }

    private String createGetString() throws UnsupportedEncodingException {
        if (checkString(url)) {
            if (params != null) {
                StringBuilder addressUrl = new StringBuilder(url);
                addressUrl.append("?");
                for (HashMap.Entry<String, Object> entry : params.getValue().entrySet()) {
                    addressUrl.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue() + "", "UTF-8")).append('&');
                }
                addressUrl.deleteCharAt(addressUrl.length() - 1);
                return addressUrl.toString();
            } else {
                return url;
            }
        }
        return null;
    }

    private String createDeleteString() throws UnsupportedEncodingException {
        if (checkString(url)) {
            StringBuilder addressUrl = new StringBuilder(url);
            if (params != null) {
                addressUrl.append("?");
                for (HashMap.Entry<String, Object> entry : params.getValue().entrySet()) {
                    addressUrl.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue() + "", "UTF-8")).append('&');
                }
                addressUrl.deleteCharAt(addressUrl.length() - 1);
                return addressUrl.toString();
            } else {
                return url;
            }
        }
        return null;
    }

    private boolean checkString(String res) {
        return res != null && res.trim().length() > 0;
    }

    private void throwException(final IRequestBack callBack, final Exception e) {
        if (callBack != null) {
            mHanlder.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onFail(e);
                }
            });
        }
    }

    private void throwSuccess(final IRequestBack callBack, final String result) {
        if (callBack != null) {
            mHanlder.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess(result);
                }
            });
        }
    }

    private String ins2Str(InputStream inputStream) {
        StringBuilder out = new StringBuilder();
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(inputStream, "UTF-8");
            int len;
            char[] buf = new char[10 * 1024];
            while ((len = isr.read(buf)) != -1) {
                out.append(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.toString();
    }


    private void trustAllHosts() {
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
