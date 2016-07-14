package com.app.merbng.mycodelibs.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringDef;

import com.app.merbng.mycodelibs.utils.StringUtils;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

/**
 * use:request net data
 * Created by taojunbin on 2016/5/16.
 */
public class Connection {
    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String DELETE = "DELETE";
    public final static String PUT = "PUT";
    private String connectionId;
    private final static int TIMEOUT = 7 * 1000;
    private IConnectListener callBack;
    private String url;
    private String method;
    private HashMap<String, Object> params;
    private Context mContext;

    public Connection(Context context) {
        mContext = context;
        connectionId = UUID.randomUUID().toString();
    }

    private static Connection with(Context context) {
        return new Connection(context);
    }

    public interface IConnectListener {
        void onSuccess(String result);

        void onFail(String failMsg);

        void onException(Exception e);
    }

    private Connection setMethod(@Method String method) {
        this.method = method;
        return this;
    }

    private Connection setParams(HashMap<String, Object> params) {
        this.params = params;
        return this;
    }

    private Connection setUrl(String url) {
        this.url = url;
        return this;
    }

    private Connection setListener(IConnectListener callBack) {
        this.callBack = callBack;
        return this;
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
            urlConnection.setRequestProperty("SCApplication-Id", "application/json");
            urlConnection.setRequestProperty("SCApplication-Key", "application/json");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip");
            urlConnection.setRequestProperty("device", "android");
            urlConnection.setRequestProperty("userId", "12345");
            urlConnection.setRequestProperty("Content-Type", "application/json");
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
                for (HashMap.Entry<String, Object> entry : params.entrySet()) {
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
                for (HashMap.Entry<String, Object> entry : params.entrySet()) {
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

    private void throwException(final IConnectListener callBack, final Exception e) {
        if (callBack != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    callBack.onException(e);
                }
            });
        }
    }

    private void throwSuccess(final IConnectListener callBack, final String result) {
        if (callBack != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess(result);
                }
            });
        }
    }

    private void throwFail(final IConnectListener callBack, final String failMsg) {
        if (callBack != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    callBack.onFail(failMsg);
                }
            });
        }
    }

    public void cancel() {
        callBack = null;
    }

    private String getConnectionId() {
        return connectionId;
    }

    @StringDef({GET, PUT, POST, DELETE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Method {

    }

    @Override
    public int hashCode() {
        return connectionId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else {
            if (o instanceof Connection) {
                Connection compare = (Connection) o;
                return connectionId.equals(compare.getConnectionId());
            }
        }
        return false;
    }

    public static class Builder {
        private Context mContext;
        private String url;
        private String method;
        private HashMap<String, Object> params;
        private IConnectListener callBack;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setMethod(@Method String method) {
            this.method = method;
            return this;
        }

        public Builder setParams(HashMap<String, Object> params) {
            this.params = params;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setListener(IConnectListener callBack) {
            this.callBack = callBack;
            return this;
        }

        public Connection build() {
            return Connection.with(mContext)
                    .setUrl(url)
                    .setMethod(method)
                    .setParams(params)
                    .setListener(callBack);
        }
    }

    public Context getmContext() {
        return mContext;
    }

    public Runnable getTask() {
        return new Runnable() {
            @Override
            public void run() {
                work();
            }
        };
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
            if (callBack != null) {
                callBack.onException(e);
            }
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
                        JSONObject object = new JSONObject(params);
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
                    result = StringUtils.inputStream2String(inputStream);
                    throwSuccess(callBack, result);
                } else {
                    inputStream = connection.getErrorStream();
                    JSONObject object = new JSONObject(StringUtils.inputStream2String(inputStream));
                    result = object.getString("message");
                    throwFail(callBack, result);
                }
            } catch (Exception e) {
                result = e.getMessage();
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
            result = "The HttpUrlConnection is null!";
            throwFail(callBack, result);
        }
        return result;
    }
}
