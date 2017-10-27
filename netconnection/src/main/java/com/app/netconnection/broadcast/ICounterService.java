package com.app.netconnection.broadcast;

/**计数器的服务接口
 * Created by merbng on 2017/10/28.
 */

public interface ICounterService {
    void startCounter(int initVal);

    void stopCounter();
}
