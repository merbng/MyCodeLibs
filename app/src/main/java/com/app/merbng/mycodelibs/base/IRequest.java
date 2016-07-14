package com.app.merbng.mycodelibs.base;

/**
 * Created by taojunbin on 2016/7/13.
 * Role:向外部暴露的方法
 */
public interface IRequest {
    /**
     * 阻塞的GET请求
     *
     * @param url 地址
     * @return 数据
     */
    String getRequest(String url, RequestParams params);

    /**
     * 异步的GET请求
     *
     * @param url 地址
     */
    void getRequestInBG(String url, RequestParams params, IRequestBack response);

    /**
     * 阻塞的POST请求
     *
     * @param url 地址
     * @return 数据
     */
    String postRequest(String url, RequestParams params);

    /**
     * 异步的POST请求
     *
     * @param url 地址
     */
    void postRequestInBG(String url, RequestParams params, IRequestBack response);

    /**
     * 阻塞的PUT请求
     *
     * @param url 地址
     * @return 数据
     */
    String putRequest(String url, RequestParams params);

    /**
     * 异步的PUT请求
     *
     * @param url 地址
     */
    void putRequestInBG(String url, RequestParams params, IRequestBack response);

    /**
     * 阻塞的DELETE请求
     *
     * @param url 地址
     * @return 数据
     */
    String deleteRequest(String url, RequestParams params);

    /**
     * 异步的DELETE请求
     *
     * @param url 地址
     */
    void deleteRequestInBG(String url, RequestParams params, IRequestBack response);

    /**
     * 取消回调
     */
    void cancelRequest();
}
