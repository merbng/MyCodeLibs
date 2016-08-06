package com.app.merbng.mycodelibs.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.app.merbng.mycodelibs.Constent;
import com.app.merbng.mycodelibs.base.BaseActivity;
import com.app.merbng.mycodelibs.interfaces.GetCallBack;
import com.app.merbng.mycodelibs.net.MIQuery;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zx on 2016/7/15.
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;
    private String openid;
    private String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, Constent.WXAPP_ID, false);
            api.registerApp(Constent.WXAPP_ID);
        }
        api.handleIntent(getIntent(), this);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        SendAuth.Resp resp = new SendAuth.Resp(intent.getExtras());
        if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK://同意
                if(resp instanceof SendAuth.Resp){
                final String code = ((SendAuth.Resp) resp).code; //即为所需的code
                //获取Token
                getToken(code);
                }else
                if(resp instanceof SendMessageToWX.Resp){
                    show("分享成功");
                    finish();
                }

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://取消
//                发送 取消的消息
//                EventBusUtils.sendWXLoginFaile();
                
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://拒绝
//                  发送 取消的消息
//                EventBusUtils.sendWXLoginFaile();
                break;
            default:
                break;
        }
    }

    /**
     * 刷新Token
     *
     * @param code
     */
    private void refreshToken(String code) {
        MIQuery.WXrefreshToken(code, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    openid = jsonObject.getString("openid");
                    access_token = jsonObject.getString("access_token");
                    WClogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
//                发送失败的消息
//                EventBusUtils.sendWXLoginFaile();
            }
        });
    }

    /**
     * 调用项目登陆。
     */
    private void WClogin() {
/*        MICreate.otherLogin(openid, Constant.LOGIN_WEIXIN, new GetCallBack.GetCallBackInterface<MIUser>() {
            @Override
            public void onSuccess(MIUser miUser) {
                if (miUser != null) {//1、有我藏帐号----登陆
                    EventBusUtils.sendWXLoginSucc(miUser);
                } else {//2.无我藏帐号---注册
                    getUserInfo(access_token, openid);
                }
            }

            @Override
            public void onFail(String s) {
//                发送失败的消息
//                EventBusUtils.sendWXLoginFaile();
            }
        });*/
    }

    /**
     * 根据code获取：openid   access_token
     *
     * @param code
     */
    private void getToken(final String code) {
        MIQuery.WXgetToken(code, Constent.SECRET, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    openid = jsonObject.getString("openid");
                    access_token = jsonObject.getString("access_token");
                    //检测Token是否有效
                    MIQuery.WXCheckTokenIsOk(access_token, openid, new GetCallBack.CountIntCallBack() {
                        @Override
                        public void onSuccess(int errcode) {
                            if (errcode == 0) {//有效--- 判断 是否 注册了我藏帐号
                                WClogin();
                            } else {//无效---刷新Token
                                refreshToken(code);
                            }
                        }

                        @Override
                        public void onFail(String e) {
//                            发送失败的消息
//                            EventBusUtils.sendWXLoginFaile();
                        }
                    });
                } catch (JSONException e) {
//                    发送失败的消息
//                    EventBusUtils.sendWXLoginFaile();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
//                发送失败的消息
//                EventBusUtils.sendWXLoginFaile();
            }
        });
    }

    /**
     * 注册前获取用户信息
     *
     * @param access_token
     * @param openid
     */
    private void getUserInfo(String access_token, final String openid) {
        MIQuery.WXgetUserInfo(access_token, openid, new GetCallBack.CallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    final String openid1 = jsonObject.getString("openid");
                    final String nickname = jsonObject.getString("nickname");
                    String headimgurl = jsonObject.getString("headimgurl");
/*                    MIQuery.getOtherUserHeardImg(headimgurl, new GetCallBack.CallBack() {
                        @Override
                        public void onSuccess(String fileId) {
                            MICreate.otherRegister(openid1, fileId, nickname, Constent.LOGIN_WEIXIN, new GetCallBack.GetCallBackInterface<MIUser>() {
                                @Override
                                public void onSuccess(MIUser miUser) {
                                    //注册成功--登陆
                                    EventBusUtils.sendWXLoginSucc(miUser);
                                }

                                @Override
                                public void onFail(String s) {
                                    EventBusUtils.sendWXLoginFaile();
                                }
                            });
                        }

                        @Override
                        public void onFail(String msg) {
                            EventBusUtils.sendWXLoginFaile();
                        }
                    });*/
                } catch (JSONException e) {
//                    发送失败的消息
//                    EventBusUtils.sendWXLoginFaile();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String msg) {
//                发送失败的消息
//                EventBusUtils.sendWXLoginFaile();
            }
        });
    }

}