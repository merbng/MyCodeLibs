package com.app.merbng.mycodelibs.interfaces;

import java.util.List;

/**
 * Created by taojunbin on 2015/9/11.
 */
public class GetCallBack {
    public interface CallBack {
        void onSuccess(String result);

        void onFail(String msg);
    }

    public interface FindCallBackInterface<T> {
        void onSuccess(List<T> list);

        void onFail(String s);
    }

    public interface GetCallBackInterface<T> {
        void onSuccess(T t);

        void onFail(String s);
    }

    public interface CommonInterface {
        void onSuccess();

        void onFail(String s);
    }

    public interface CommentInterface {
        void onSuccess(String userId, String userCardCommentId);

        void onFail();
    }

    public interface CountIntCallBack {
        void onSuccess(int count);

        void onFail(String e);
    }

    public interface GetCallBackInterfacePro<T> {
        void onSuccess(T t);

        void onFail(String s);

        void onProgress(int currentPro, int maxPro);
    }

    public interface CallBackPro {
        void onSuccess(String result);

        void onFail(String msg);

        void onProgress(int currentPro, int maxPro);
    }
    public interface  CallBackPIC{
        void onSuceess(String fileId, String Url);

        void onfail(String msg);

        void onProgress(long currentPro, long maxPro);
    }
    public interface CallBackMethod{
        void onSuccess();
    }
}
