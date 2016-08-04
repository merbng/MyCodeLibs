package com.app.merbng.mycodelibs.net;

import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.interfaces.GetCallBack;
import com.app.merbng.mycodelibs.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by taojunbin on 2015/9/12.
 */
public class MIQuery {
    /**
     * 第三方用户头像上传接口
     * 传 url  返回  fileId
     *
     * @param imageUrl
     * @param callBack
     */
    public static void getOtherUserHeardImg(String imageUrl, final GetCallBack.CallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("imageUrl", imageUrl);
        BaseUrlConnection.getInstance().getConnection(Constent.OTHERUSERHEARDIMG, params, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String fileId = jsonObject.getString("fileId");
                    callBack.onSuccess(fileId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }


    /**
     * 微信登陆---刷新Token
     *
     * @param code
     * @param callBack
     */
    public static void WXrefreshToken(String code, final GetCallBack.CallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("appid", Constent.WXAPP_ID);
        params.put("refresh_token", code);
        params.put("grant_type", "REFRESH_TOKEN");
        BaseUrlConnection.getInstance().getConnection(Constent.WXREFRESHTOKEN, params, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtil.log.e("刷新Token----result:" + result);
                callBack.onSuccess(result);
            }

            @Override
            public void onFail(String msg) {
                LogUtil.log.e("刷新Token----msg:" + msg);
                callBack.onFail(msg);
            }
        });
    }

    /**
     * 微信登陆--获取Token
     *
     * @param code
     * @param SECRET
     * @param callBack
     */

    public static void WXgetToken(String code, String SECRET, final GetCallBack.CallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("appid", Constent.WXAPP_ID);
        params.put("secret", SECRET);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        BaseUrlConnection.getInstance().getConnection(Constent.WXGETTOKEN, params, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }

    /**
     * 微信登陆 获取用户信息
     *
     * @param access_token
     * @param openid
     * @param callBack
     */
    public static void WXgetUserInfo(String access_token, String openid, final GetCallBack.CallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("access_token", access_token);
        params.put("openid", openid);
        BaseUrlConnection.getInstance().getConnection(Constent.WXUSERINFOURL, params, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }


    /**
     * 检验授权凭证（access_token）是否有效
     *
     * @param access_token
     * @param openid
     * @param callBack
     */
    public static void WXCheckTokenIsOk(String access_token, String openid, final GetCallBack.CountIntCallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("access_token", access_token);
        params.put("openid", openid);
        BaseUrlConnection.getInstance().getConnection(Constent.WXCHECKTOKEN, params, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int errcode = jsonObject.getInt("errcode");
                    callBack.onSuccess(errcode);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }
}