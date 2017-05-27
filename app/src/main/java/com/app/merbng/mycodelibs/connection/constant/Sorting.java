package com.app.merbng.mycodelibs.connection.constant;

import android.util.SparseArray;
import android.util.SparseBooleanArray;

/**
 * Created by ght on 2017/2/18.
 * 控制排序方式的请求参数的类
 */

public final class Sorting {
    private static final SparseBooleanArray REQUIRES_TIMEFRAME = new SparseBooleanArray() {
    };
    public static final int SORT_TIMEFRAME_ALL = 6;
    public static final int SORT_TIMEFRAME_DAY = 2;
    public static final int SORT_TIMEFRAME_HOUR = 1;
    public static final int SORT_TIMEFRAME_MONTH = 4;
    public static final int SORT_TIMEFRAME_UNDEFINED = -1;
    public static final int SORT_TIMEFRAME_WEEK = 3;
    public static final int SORT_TIMEFRAME_YEAR = 5;


    private static final SparseArray<String> SORT_TIMEFRAME_TO_API = new SparseArray<>();


    public static final int SORT_TYPE_BEST = 0;
    public static final int SORT_TYPE_COMMENTS = 4;
    public static final int SORT_TYPE_CONTROVERSIAL = 6;
    public static final int SORT_TYPE_HOT = 2;
    public static final int SORT_TYPE_NEW = 1;
    public static final int SORT_TYPE_QA = 5;
    public static final int SORT_TYPE_TOP = 3;
    private static final SparseArray<String> SORT_TYPE_TO_API = new SparseArray<>();
    private static final SparseArray<String> SORT_TYPE_TO_API_PATH = new SparseArray<>();

    static {
        SORT_TYPE_TO_API_PATH.put(SORT_TYPE_BEST, "best");
        SORT_TYPE_TO_API_PATH.put(SORT_TYPE_NEW, "createdAt");
        SORT_TYPE_TO_API_PATH.put(SORT_TYPE_HOT, "hotScore");
        SORT_TYPE_TO_API_PATH.put(SORT_TYPE_TOP, "top");
        SORT_TYPE_TO_API_PATH.put(SORT_TYPE_QA, "qa");
        SORT_TYPE_TO_API_PATH.put(SORT_TYPE_CONTROVERSIAL, "controversial");
    }

    static {
        SORT_TYPE_TO_API.put(SORT_TYPE_BEST, "相关度");
        SORT_TYPE_TO_API.put(SORT_TYPE_NEW, "新鲜度");
        SORT_TYPE_TO_API.put(SORT_TYPE_HOT, "热度");
        SORT_TYPE_TO_API.put(SORT_TYPE_TOP, "好评度");
        SORT_TYPE_TO_API.put(SORT_TYPE_COMMENTS, "评论数");
        SORT_TYPE_TO_API.put(SORT_TYPE_QA, "问答");
        SORT_TYPE_TO_API.put(SORT_TYPE_CONTROVERSIAL, "分歧度");
    }

    static {
        REQUIRES_TIMEFRAME.put(SORT_TYPE_BEST, false);
        REQUIRES_TIMEFRAME.put(SORT_TYPE_NEW, false);
        REQUIRES_TIMEFRAME.put(SORT_TYPE_HOT, false);
        REQUIRES_TIMEFRAME.put(SORT_TYPE_TOP, true);
        REQUIRES_TIMEFRAME.put(SORT_TYPE_COMMENTS, true);
        REQUIRES_TIMEFRAME.put(SORT_TYPE_QA, false);
        REQUIRES_TIMEFRAME.put(SORT_TYPE_CONTROVERSIAL, false);

    }

    public static final int SORT_TYPE_UNDEFINED = -1;

    public static boolean requiresTimeframe(int paramInt) {
        return REQUIRES_TIMEFRAME.get(paramInt);
    }


    public static String sortToApi(int paramInt) {
        return SORT_TYPE_TO_API.get(paramInt);
    }

    /**
     * 通过sortType获取对应的urlPath
     *
     * @param sortType {@link #SORT_TYPE_BEST,#SORT_TYPE_COMMENTS}
     * @return urlPath
     */
    public static String sortToAPIPath(int sortType) {
        return SORT_TYPE_TO_API_PATH.get(sortType);
    }

    public static String timeframeToApi(int paramInt) {
        return SORT_TIMEFRAME_TO_API.get(paramInt);
    }

    /**
     * 工具类不能实例化.
     */
    private Sorting() {
    }
}
