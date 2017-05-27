package com.app.merbng.mycodelibs.connection;


import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * Created by zh on 2016/12/8.
 * 一个用来发起网络请求的类
 */
@SuppressWarnings("CheckStyle")
public final class RxClient {
    private static final String TOPICID = "topicId";
    private static final String CONTENT = "content";
    private static final String USERID = "userId";
    private static final String PASSWORD = "password";
    private static final String IMGSURL = "imgsUrl";

    /**
     * 私有.
     */
    private RxClient() {
    }

    /**
     * 退出社区
     *
     * @return
     */
    public static Observable<ResponseBody> exitCommunity(String communityId) {
        return ApiManager.getInstance().commentAPIService().getComments(0,0,communityId);
    }

    /**
     * 用户申请创建一个社区.
     *
     * @param name     社区名称
     * @param detail   社区的简介
     * @param rule     社区的规则
     * @param coverImg 社区的封面的图片
     * @return
     */
    public static Observable<ResponseBody> createCommunity(String name, String detail, String rule, String coverImg) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("detail", detail);
        params.put("rule", rule);
        params.put("coverImg", coverImg);
        String jsonString = new JSONObject(params).toString();
        return ApiManager.getInstance().commentAPIService().editComment(RequestBody.create(MediaType.parse(jsonString), jsonString));
    }
}
