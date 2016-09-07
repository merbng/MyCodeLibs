package com.app.merbng.mycodelibs.StudyBuildModel;

/**学习Build建造者模式
 * http://blog.csdn.net/bboyfeiyu/article/details/24375481?utm_source=tuicool&utm_medium=referral
 * Created by merbng on 2016/9/4.
 */
public class Test_Build_model {
    public static void main(String[] args) {
        Builder builder = new ApplePCBuilder();
        Director director = new Director(builder);
        director.construct(4, 2, "Mac Os 10.9.1");
        builder.create();
    }
}
