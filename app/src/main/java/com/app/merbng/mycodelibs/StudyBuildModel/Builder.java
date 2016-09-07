package com.app.merbng.mycodelibs.StudyBuildModel;

/**
 * Created by merbng on 2016/9/4.
 */
public abstract class Builder {
    //    设置CPU核心数
    public abstract void buildCPU(int core);

    //    设置内存
    public abstract void buildRAM(int gb);

    //    设置操作系统
    public abstract void buildOs(String os);

    //    创建电脑
    public abstract Computer create();

}
