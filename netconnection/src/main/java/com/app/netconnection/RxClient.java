package com.app.netconnection;

import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by merbng on 2017/10/26.
 */

public class RxClient {
    /**
     * 获取星座运势
     *
     * @param consName
     * @param type
     * @return
     */
    public static Observable<LuckBean> getAonstellationLuck(String consName, String type) {
        return ApiManager.getInstance().upapiService().getAonstellationLuck(consName, type,"6e4ff13d898d1171b9f281d474cc5df8");
    }
    public static Observable<LuckYearBean> getYearLuck(String consName) {
        return ApiManager.getInstance().upapiService().getYearAonstellationLuck(consName, "year","6e4ff13d898d1171b9f281d474cc5df8");
    }

    /**
     * @param name
     * @param type
     * @return
     */
    public static Observable<ResponseBody> postDataTest(String name, int type) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("type", type);
        String jsonStr = new JSONObject(map).toString();
        return ApiManager.getInstance().upapiService().postDataTest(RequestBody.create(MediaType.parse(jsonStr), jsonStr));
    }
}
