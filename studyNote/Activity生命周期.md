#Study Note 


##[Activity生命周期](http://blog.csdn.net/liuhe688/article/details/6733407)
 基础总结篇之一：Activity生命周期
标签： androidservletanimationjavaeebutton
2011-09-02 17:37 325126人阅读 评论(96) 举报
 分类： Android（31）  
版权声明：本文为博主原创文章，转载请注明出处。
子曰：溫故而知新，可以為師矣。《論語》
学习技术也一样，对于技术文档或者经典的技术书籍来说，指望看一遍就完全掌握，那基本不大可能，所以我们需要经常回过头再仔细研读几遍，以领悟到作者的思想精髓。
近来回顾了一下关于Activity的生命周期，参看了相关书籍和官方文档，也有了不小的收获，对于以前的认知有了很大程度上的改善，在这里和大家分享一下。
熟悉javaEE的朋友们都了解servlet技术，我们想要实现一个自己的servlet，需要继承相应的基类，重写它的方法，这些方法会在合适的时间被servlet容器调用。其实android中的Activity运行机制跟servlet有些相似之处，Android系统相当于servlet容器，Activity相当于一个servlet，我们的Activity处在这个容器中，一切创建实例、初始化、销毁实例等过程都是容器来调用的，这也就是所谓的“Don't call me, I'll call you.”机制。
我们来看一下这一张经典的生命周期流程图：

相信不少朋友也已经看过这个流程图了，也基本了解了Activity生命周期的几个过程，我们就来说一说这几个过程。
1.启动Activity：系统会先调用onCreate方法，然后调用onStart方法，最后调用onResume，Activity进入运行状态。
2.当前Activity被其他Activity覆盖其上或被锁屏：系统会调用onPause方法，暂停当前Activity的执行。
3.当前Activity由被覆盖状态回到前台或解锁屏：系统会调用onResume方法，再次进入运行状态。
4.当前Activity转到新的Activity界面或按Home键回到主屏，自身退居后台：系统会先调用onPause方法，然后调用onStop方法，进入停滞状态。
5.用户后退回到此Activity：系统会先调用onRestart方法，然后调用onStart方法，最后调用onResume方法，再次进入运行状态。
6.当前Activity处于被覆盖状态或者后台不可见状态，即第2步和第4步，系统内存不足，杀死当前Activity，而后用户退回当前Activity：再次调用onCreate方法、onStart方法、onResume方法，进入运行状态。
7.用户退出当前Activity：系统先调用onPause方法，然后调用onStop方法，最后调用onDestory方法，结束当前Activity。
但是知道这些还不够，我们必须亲自试验一下才能深刻体会，融会贯通。
下面我们就结合实例，来演示一下生命周期的几个过程的详细情况。我们新建一个名为lifecycle的项目，创建一个名为LifeCycleActivity的Activity，如下：
[java] view plain copy
package com.scott.lifecycle;  
  
import android.app.Activity;  
import android.content.Context;  
import android.content.Intent;  
import android.os.Bundle;  
import android.util.Log;  
import android.view.View;  
import android.widget.Button;  
  
public class LifeCycleActivity extends Activity {  
      
    private static final String TAG = "LifeCycleActivity";  
    private Context context = this;  
    private int param = 1;  
      
    //Activity创建时被调用  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        Log.i(TAG, "onCreate called.");  
          
        setContentView(R.layout.lifecycle);  
          
        Button btn = (Button) findViewById(R.id.btn);  
        btn.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                Intent intent = new Intent(context, TargetActivity.class);  
                startActivity(intent);  
            }  
        });  
    }  
      
    //Activity创建或者从后台重新回到前台时被调用  
    @Override  
    protected void onStart() {  
        super.onStart();  
        Log.i(TAG, "onStart called.");  
    }  
      
    //Activity从后台重新回到前台时被调用  
    @Override  
    protected void onRestart() {  
        super.onRestart();  
        Log.i(TAG, "onRestart called.");  
    }  
      
    //Activity创建或者从被覆盖、后台重新回到前台时被调用  
    @Override  
    protected void onResume() {  
        super.onResume();  
        Log.i(TAG, "onResume called.");  
    }  
      
    //Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后  
    /*@Override 
    public void onWindowFocusChanged(boolean hasFocus) { 
        super.onWindowFocusChanged(hasFocus); 
        Log.i(TAG, "onWindowFocusChanged called."); 
    }*/  
      
    //Activity被覆盖到下面或者锁屏时被调用  
    @Override  
    protected void onPause() {  
        super.onPause();  
        Log.i(TAG, "onPause called.");  
        //有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据  
    }  
      
    //退出当前Activity或者跳转到新Activity时被调用  
    @Override  
    protected void onStop() {  
        super.onStop();  
        Log.i(TAG, "onStop called.");     
    }  
      
    //退出当前Activity时被调用,调用之后Activity就结束了  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        Log.i(TAG, "onDestory called.");  
    }  
      
    /** 
     * Activity被系统杀死时被调用. 
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死. 
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态. 
     * 在onPause之前被调用. 
     */  
    @Override  
    protected void onSaveInstanceState(Bundle outState) {  
        outState.putInt("param", param);  
        Log.i(TAG, "onSaveInstanceState called. put param: " + param);  
        super.onSaveInstanceState(outState);  
    }  
      
    /** 
     * Activity被系统杀死后再重建时被调用. 
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity. 
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后. 
     */  
    @Override  
    protected void onRestoreInstanceState(Bundle savedInstanceState) {  
        param = savedInstanceState.getInt("param");  
        Log.i(TAG, "onRestoreInstanceState called. get param: " + param);  
        super.onRestoreInstanceState(savedInstanceState);  
    }  
}  
大家注意到，除了几个常见的方法外，我们还添加了onWindowFocusChanged、onSaveInstanceState、onRestoreInstanceState方法：
1.onWindowFocusChanged方法：在Activity窗口获得或失去焦点时被调用，例如创建时首次呈现在用户面前；当前Activity被其他Activity覆盖；当前Activity转到其他Activity或按Home键回到主屏，自身退居后台；用户退出当前Activity。以上几种情况都会调用onWindowFocusChanged，并且当Activity被创建时是在onResume之后被调用，当Activity被覆盖或者退居后台或者当前Activity退出时，它是在onPause之后被调用，如图所示：

这个方法在某种场合下还是很有用的，例如程序启动时想要获取视特定视图组件的尺寸大小，在onCreate中可能无法取到，因为窗口Window对象还没创建完成，这个时候我们就需要在onWindowFocusChanged里获取；如果大家已经看过我写的Android动画之Frame Animation这篇文章就会知道，当时试图在onCreate里加载frame动画失败的原因就是因为窗口Window对象没有初始化完成，所以最后我将加载动画的代码放到了onWindowFocusChanged中，问题迎刃而解。不过大家也许会有疑惑，为什么我在代码里将它注释掉了，因为对当前Activity每一个操作都有它的执行log，我担心这会影响到整个流程的清晰度，所以将它注掉，大家只要了解它应用的场合和执行的顺序就可以了。
2.onSaveInstanceState：(1)在Activity被覆盖或退居后台之后，系统资源不足将其杀死，此方法会被调用；(2)在用户改变屏幕方向时，此方法会被调用；(3)在当前Activity跳转到其他Activity或者按Home键回到主屏，自身退居后台时，此方法会被调用。第一种情况我们无法保证什么时候发生，系统根据资源紧张程度去调度；第二种是屏幕翻转方向时，系统先销毁当前的Activity，然后再重建一个新的，调用此方法时，我们可以保存一些临时数据；第三种情况系统调用此方法是为了保存当前窗口各个View组件的状态。onSaveInstanceState的调用顺序是在onPause之前。
3.onRestoreInstanceState：(1)在Activity被覆盖或退居后台之后，系统资源不足将其杀死，然后用户又回到了此Activity，此方法会被调用；(2)在用户改变屏幕方向时，重建的过程中，此方法会被调用。我们可以重写此方法，以便可以恢复一些临时数据。onRestoreInstanceState的调用顺序是在onStart之后。
以上着重介绍了三个相对陌生方法之后，下面我们就来操作一下这个Activity，看看它的生命周期到底是个什么样的过程：
1.启动Activity：

在系统调用了onCreate和onStart之后，调用了onResume，自此，Activity进入了运行状态。
2.跳转到其他Activity，或按下Home键回到主屏：

我们看到，此时onSaveInstanceState方法在onPause之前被调用了，并且注意，退居后台时，onPause后onStop相继被调用。
3.从后台回到前台：

当从后台会到前台时，系统先调用onRestart方法，然后调用onStart方法，最后调用onResume方法，Activity又进入了运行状态。
4.修改TargetActivity在AndroidManifest.xml中的配置，将android:theme属性设置为@android:style/Theme.Dialog，然后再点击LifeCycleActivity中的按钮，跳转行为就变为了TargetActivity覆盖到LifeCycleActivity之上了，此时调用的方法为：

注意还有一种情况就是，我们点击按钮，只是按下锁屏键，执行的效果也是如上。
我们注意到，此时LifeCycleActivity的OnPause方法被调用，并没有调用onStop方法，因为此时的LifeCycleActivity没有退居后台，只是被覆盖或被锁屏；onSaveInstanceState会在onPause之前被调用。
5.按回退键使LifeCycleActivity从被覆盖回到前面，或者按解锁键解锁屏幕：

此时只有onResume方法被调用，直接再次进入运行状态。
6.退出：

最后onDestory方法被调用，标志着LifeCycleActivity的终结。
大家似乎注意到，在所有的过程中，并没有onRestoreInstanceState的出现，这个并不奇怪，因为之前我们就说过，onRestoreInstanceState只有在杀死不在前台的Activity之后用户回到此Activity，或者用户改变屏幕方向的这两个重建过程中被调用。我们要演示第一种情况比较困难，我们可以结合第二种情况演示一下具体过程。顺便也向大家讲解一下屏幕方向改变的应对策略。
首先介绍一下关于Activity屏幕方向的相关知识。
我们可以为一个Activity指定一个特定的方向，指定之后即使转动屏幕方向，显示方向也不会跟着改变：
1.指定为竖屏：在AndroidManifest.xml中对指定的Activity设置android:screenOrientation="portrait"，或者在onCreate方法中指定：
[java] view plain copy
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //竖屏  
2.指定为横屏：在AndroidManifest.xml中对指定的Activity设置android:screenOrientation="landscape"，或者在onCreate方法中指定：
[java] view plain copy
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //横屏  
为应用中的Activity设置特定的方向是经常用到的办法，可以为我们省去不少不必要的麻烦。不过，我们今天讲的是屏幕方向改变时的生命周期，所以我们并不采用固定屏幕方向这种办法。
下面我们就结合实例讲解一下屏幕转换的生命周期，我们新建一个Activity命名为OrientationActivity，如下：
[java] view plain copy
package com.scott.lifecycle;  
  
import android.app.Activity;  
import android.content.res.Configuration;  
import android.os.Bundle;  
import android.util.Log;  
  
public class OrientationActivity extends Activity {  
      
    private static final String TAG = "OrientationActivity";  
    private int param = 1;  
      
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.orientation_portrait);  
        Log.i(TAG, "onCreate called.");  
    }  
      
    @Override  
    protected void onStart() {  
        super.onStart();  
        Log.i(TAG, "onStart called.");  
    }  
      
    @Override  
    protected void onRestart() {  
        super.onRestart();  
        Log.i(TAG, "onRestart called.");  
    }  
      
    @Override  
    protected void onResume() {  
        super.onResume();  
        Log.i(TAG, "onResume called.");  
    }  
      
    @Override  
    protected void onPause() {  
        super.onPause();  
        Log.i(TAG, "onPause called.");  
    }  
      
    @Override  
    protected void onStop() {  
        super.onStop();  
        Log.i(TAG, "onStop called.");  
    }  
      
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        Log.i(TAG, "onDestory called.");  
    }  
  
    @Override  
    protected void onSaveInstanceState(Bundle outState) {  
        outState.putInt("param", param);  
        Log.i(TAG, "onSaveInstanceState called. put param: " + param);  
        super.onSaveInstanceState(outState);  
    }  
      
    @Override  
    protected void onRestoreInstanceState(Bundle savedInstanceState) {  
        param = savedInstanceState.getInt("param");  
        Log.i(TAG, "onRestoreInstanceState called. get param: " + param);  
        super.onRestoreInstanceState(savedInstanceState);  
    }  
      
    //当指定了android:configChanges="orientation"后,方向改变时onConfigurationChanged被调用  
    @Override  
    public void onConfigurationChanged(Configuration newConfig) {  
        super.onConfigurationChanged(newConfig);  
        Log.i(TAG, "onConfigurationChanged called.");  
        switch (newConfig.orientation) {  
        case Configuration.ORIENTATION_PORTRAIT:  
            setContentView(R.layout.orientation_portrait);  
            break;  
        case Configuration.ORIENTATION_LANDSCAPE:  
            setContentView(R.layout.orientation_landscape);  
            break;  
        }  
    }  
}  
首先我们需要进入“Settings->Display”中，将“Auto-rotate Screen”一项选中，表明可以自动根据方向旋转屏幕，然后我们就可以测试流程了，当我们旋转屏幕时，我们发现系统会先将当前Activity销毁，然后重建一个新的：

系统先是调用onSaveInstanceState方法，我们保存了一个临时参数到Bundle对象里面，然后当Activity重建之后我们又成功的取出了这个参数。
为了避免这样销毁重建的过程，我们需要在AndroidMainfest.xml中对OrientationActivity对应的<activity>配置android:configChanges="orientation"，然后我们再测试一下，我试着做了四次的旋转，打印如下：

可以看到，每次旋转方向时，只有onConfigurationChanged方法被调用，没有了销毁重建的过程。
以下是需要注意的几点：
1.如果<activity>配置了android:screenOrientation属性，则会使android:configChanges="orientation"失效。
2.模拟器与真机差别很大：模拟器中如果不配置android:configChanges属性或配置值为orientation，切到横屏执行一次销毁->重建，切到竖屏执行两次。真机均为一次。模拟器中如果配置android:configChanges="orientation|keyboardHidden"（如果是Android4.0，则是"orientation|keyboardHidden|screenSize"），切竖屏执行一次onConfigurationChanged，切横屏执行两次。真机均为一次。
Activity的生命周期与程序的健壮性有着密不可分的关系，希望朋友们能够认真体会、熟练应用。
顶
68
 
踩
7
上一篇Android中SQLite应用详解
下一篇基础总结篇之二：Activity的四种launchMode
我的同类文章
Android（31）
猜你在找
查看评论
73楼 代码运行异常 2016-07-18 19:06发表 [回复] 自从Android 3.2（API 13），在设置Activity的android:configChanges="orientation|keyboardHidden"后，还是一样 会重新调用各个生命周期的。这是因为screen size也开始跟着设备的横竖切换而改变。所以此时要加上ScreenSize。希望文章的博主更新此文时加上。
72楼 syzhugh 2016-07-15 15:31发表 [回复] 请问生命周期那个图是楼主自己画的吗，用的什么软件啊
71楼 newbie_zhu 2016-06-28 18:37发表 [回复] 很详细，转载了，会注明出处的。Re: liuhe688 2016-07-15 16:26发表 [回复] 回复newbie_zhu：借鉴了Android官方文档里图~
70楼 f360898864 2016-06-18 12:40发表 [回复] 楼主写的很好，不够全面，可以看看这篇 http://blog.csdn.net/amazing7/article/details/51244219Re: JackingNo1 2016-07-21 12:34发表 [回复] 回复f360898864：是讲的更细了
69楼 guangzhouxiaodidi 2016-06-16 14:41发表 [回复] onSaveInstanceState()屏幕翻转是不会被调用吧？
我的机子是android 5.1.1，android studio2.1写的demo死活都不会调用。。。。。。
我android 小白一个。
难道onSaveInstanceState调用机制发生变化了？Re: guangzhouxiaodidi 2016-06-16 16:34发表 [回复] 回复guangzhouxiaodidi：f88k,知道怎么回事了。
onSaveInstanceState有两个版本一个onSaveInstanceState(Bundle),还有一个是onSaveInstanceState(Bundle,PersistableBundle)
不注意的话，重写了第二个是不行的。
68楼 wyj463647828 2016-05-20 15:21发表 [回复] 五年了啊，前辈还在呢Re: liuhe688 2016-05-20 21:28发表 [回复] 回复wyj463647828：还健在呢，哈哈
67楼 随遇而安- 2016-05-15 14:26发表 [回复] 写的挺好的，读后获益匪浅！
66楼 初-学者 2016-05-10 09:15发表 [回复] 赞~
65楼 fasterry 2016-04-29 21:20发表 [回复] 面试问道了，现在来看下，希望下次能够有把握来回答
64楼 fasterry 2016-04-29 21:19发表 [回复] 面试问道了，过来复习下，有前任铺路的感觉真好
63楼 Eternity_Wan 2016-04-26 20:19发表 [回复] 收藏，关注，写的非常详细，初学者非常适合多看看，我要有这么细心的导师就好学多了。
62楼 airport135 2016-04-26 16:30发表 [回复] 好文章，顶一下
61楼 CcodeM 2016-03-29 00:28发表 [回复] 楼主，我是一个初学菜鸟，看了这个很受启发，我能转到自己的博客吗？Re: liuhe688 2016-03-29 09:40发表 [回复] 回复qq_30554199：可以，欢迎转载~Re: CcodeM 2016-03-30 23:46发表 [回复] 回复liuhe688：感谢
60楼 沐风雨木 2016-03-24 20:50发表 [回复] 楼主，这个可以转载吗？我想转到自己的博客里面Re: liuhe688 2016-03-25 09:43发表 [回复] 回复zuanshisi：可以的Re: 沐风雨木 2016-03-25 11:06发表 [回复] 回复liuhe688：嗯，谢谢，十分感谢。
59楼 rich刘 2016-03-02 16:49发表 [回复] 写的清楚明白，以前一些模糊的概念看得很明白。楼主太牛了
58楼 Nronaldo 2015-11-26 10:08发表 [回复] 1请问TargetActivity是什么。。Re: 钓鱼君 2015-12-22 14:45发表 [回复] 回复Nronaldo：当前类的标签，这样用log打印日志信息就通过这个标签找到所在类就行了。Re: Nronaldo 2015-12-24 12:03发表 [回复] 回复corey_jk：谢谢。我太水了。。
57楼 Nronaldo 2015-11-26 10:08发表 [回复] TargetActivity是啥。。初学者不懂啊。这个是我自己定义的？还是用一个手机里有的代替？谢谢
56楼 玖珈 2015-11-25 16:16发表 [回复] onDestory 与onDestroy傻傻分不清楚！！
55楼 SeraphZXZ 2015-10-27 11:06发表 [回复] 感谢分享，受教了。
54楼 许小乖 2015-10-09 16:29发表 [回复] 感谢分享，学习了
53楼 Java949942167 2015-10-01 13:57发表 [回复] onCreate的时候有些东西没有加载出来，不确定恢复到哪里
52楼 奔袭一万里 2015-09-07 09:01发表 [回复] 有点没懂啊 
2.当前Activity被其他Activity覆盖其上或被锁屏：系统会调用onPause方法，暂停当前Activity的执行。
4.当前Activity转到新的Activity界面或按Home键回到主屏……
第2个中的被其他Activity覆盖其上和第4个中转到新的Activity界面有啥不同啊？Re: 奔袭一万里 2015-09-07 10:33发表 [回复] 回复LZQ729089549：看了看又懂了，醉了Re: xconcise 2015-12-16 11:53发表 [回复] 回复LZQ729089549：就是可见状态， 比如在那个activity上弹出dialog，
51楼 伤陌1991 2015-08-19 15:23发表 [回复] mark
50楼 初-学者 2015-07-21 17:03发表 [回复] 赞赞~
49楼 coder_lz 2015-07-16 22:34发表 [回复] 测试时发现几个错误的地方：
1.onStop在跳到另外的界面以及锁屏和返回到主界面时都会调用
2.onSaveInstanceState是在onPause之后，onSop之前调用的Re: songjipo 2015-08-19 10:51发表 [回复] 回复mybaby525：3.0之前是在onPause之前 之后是在onPause之后
48楼 baobeinet 2015-06-03 11:31发表 [回复] 楼主写得太好了，但是我自己写程序验证了一下，当切换横竖屏的时候是会分别调用onSaveInstanceState和onRestoreInstanceState方法，但是呢我初始化变量param有时候是0有时候是1，这是为什么？还有就是你在微博里面说 * 在onPause之前被调用. */ onSaveInstanceState，但是我调用都是在onPause之后哦Re: wang_xiao_ping 2016-03-17 18:21发表 [回复] 回复baobeinet：回复baobeinet：任玉刚的《Android开发艺术探索》第一章第9页中写到：“onSaveInstanceState的调用时机是在onStop之前，和onPause没有既定的时序关系，有可能在onPause之前调用，也有可能在onPause之后调用”。我比较倾向于任玉刚书中的说法。
47楼 qq_15642057 2015-05-18 14:39发表 [回复] 好文章，收藏当笔记
46楼 还得重新来过 2015-05-12 22:13发表 [回复] 写得太好了，顶起。
45楼 Wilshion 2015-04-10 19:46发表 [回复] 好文· 
第二条，genymotion测试，锁屏的时候调用了 onPause，再调onStop方法，不知道是不是楼主后半句没说。。。
44楼 僤尐僤 2015-03-24 16:10发表 [回复] 貌似调用系统锁屏时，写错了吧会调用onPause------onStop
屏幕解锁时会调用onRestart-----onstart----------onResume滴Re: 1054512666 2015-10-17 15:28发表 [回复] 模拟器版本问题
43楼 广工三国 2015-03-18 15:14发表 [回复] niu!
42楼 tansuangai2008 2015-03-12 17:41发表 [回复] 正好，工作上需要接触到这块，正好看看。
41楼 qq_18431221 2015-03-09 11:47发表 [回复] 写的真他妈好
40楼 gaijw_csdn 2015-03-06 10:50发表 [回复] 写的真他妈好
39楼 樱花_殇 2015-02-25 14:22发表 [回复] 收藏了，以后常拿出来看看，写的真心不错...谢谢楼主
38楼 shuyumac 2015-01-19 20:29发表 [回复] 真心不错。
37楼 sarsscofy 2014-12-08 15:09发表 [回复] 感谢分享。。。
36楼 lianlei0913 2014-12-08 12:51发表 [回复] 
35楼 lyl16 2014-10-31 12:03发表 [回复] 楼主，好流弊，菜鸟得多读几遍您的文章，希望能继续更新使我们学习之，，，跪谢额。。。。
34楼 跳跳张 2014-10-30 10:09发表 [回复] 写的太好了 我得看十遍才行
33楼 laowang2 2014-10-25 14:48发表 [回复] 点击了按钮后停止响应了
32楼 fysl 2014-10-22 16:03发表 [回复] lz写到很好，看过让我理解的更透彻！非常感谢！顶起！
31楼 dongrideshui 2014-09-29 05:18发表 [回复] 请问第一次安装后按home键进入后台，再进入前台后调用了oncreate()方法，且之前没有调用ondestory()的现象怎么理解呢？
30楼 缘痕 2014-09-10 11:40发表 [回复] 继承Activity之后，执行构造方法后，没有执行oncreate方法怎么解？
29楼 游手好贤 2014-08-29 09:10发表 [回复] 真好。
28楼 luoliang19941106 2014-08-23 13:58发表 [回复] 很棒！
27楼 JmqK 2014-08-21 21:56发表 [回复] 锁屏时候是调用onPause,onStop, 然后点击解锁的时候调用onRestart,onStart,onResume....LZ可以测试下...Re: 酒_剑仙 2014-08-29 00:21发表 [回复] 回复jack522703074：博主先说锁屏时执行onpause onstop,接着又说解锁时会执行onresume，这之间不是onrestart onstart吗，看来你和我一样有时候做事急躁没有把握到事情的重点，自己给自己找麻烦
26楼 springhui520 2014-08-19 22:26发表 [回复] 博主可以此系列博文出版书籍，非常之赞Re: xuexingyang 2014-10-10 11:04发表 [回复] 回复springhui520：比起那些指南指北，深入。。啥的的不知道好多少倍。
25楼 牛气大志 2014-08-17 22:55发表 [回复] 帖子写的好，太好了。
24楼 MVT_Mr_zeng 2014-08-12 11:04发表 [回复] 很不错的文章，已收藏
23楼 李瑞_0822 2014-08-11 16:51发表 [回复] 你好！我有个问题，我测试时，当锁屏时也调用了onstop方法啊！
22楼 C_Cora 2014-08-04 23:29发表 [回复] 不错很详细。
21楼 zengyuanjun 2014-08-04 14:34发表 [回复] 楼主是雷锋啊，写的简单明了，非常给力。
20楼 qq247890212 2014-08-01 14:24发表 [回复] 3.当前Activity由被覆盖状态回到前台或解锁屏：系统会调用onResume方法，再次进入运行状态。

这句话不太正确啊。我在onStart()方法里面加了一个动作。当我直接锁屏再解开的时候是会调用那个动作的。和你说系统会调用onResume()方法进入运行状态是相悖的。
19楼 caoniquanjiaariniday 2014-07-16 18:24发表 [回复] 今天又看了一遍，有个疑问如下：onRestoreInstanceState和onCreate方法参数都是savedInstanceState，而且onRestoreInstanceState调用在onStart方法之后，而onCreate方法在onStart之前，所以不是很理解onRestoreInstanceState这个方法在什么时候最有用？如果activity被系统killed，重启会onCreate，onStart。。。。数据恢复的功能直接写在onCreate里面即可。。干嘛还要onRestoreInstanceState方法呢？？
18楼 caoniquanjiaariniday 2014-07-15 10:39发表 [回复] 卤煮讲解的很好，全文都是字字珠玑。。学习了，膜拜中。。。
17楼 u014525108 2014-07-12 00:25发表 [回复] 在加载动画的时候为什么不在onresume()方法里面进行呢？
16楼 lml1010402004 2014-07-08 14:40发表 [回复] 写的很详细
15楼 疯狂小强 2014-06-24 19:54发表 [回复] 很好 学习了
14楼 何达丰8q 2014-06-12 15:17发表 [回复] 博主，为什么我测试的结果是onSaveInstanceState在onPause之后被调用
13楼 苏破尔 2014-04-08 10:38发表 [回复] 解释的很清楚，谢谢，已转
12楼 super_luo 2014-03-18 17:23发表 [回复] 温故而知新，这些问题在之前的开发中都遇到过，今天看到博主的文章，再次温习了下，深有体会，博主写的很详细，有头有尾，真心写的好，不得不顶！~
11楼 罗小成 2014-03-10 21:42发表 [回复] 博主，你好。看完你的博客总结，受益匪浅。非常感谢。
但是我在试最后一个android:configChanges时。用真机测试，当不设置的时候，横竖都会一遍生命周期。
android:configChanges:="orientation"时会和第三种情况一样。横竖只会调用一遍onConfigurationChanged。我测试的项目是2.2版本的。所以和博主写的不相符，能否问下这是什么原因？
10楼 慕容渊 2014-01-17 23:21发表 [回复] 最后有句话有问题吧：切竖屏执行一次onConfigurationChanged，切竖屏执行两次。

哪个是一次?哪个是两次Re: liuhe688 2014-01-18 14:37发表 [回复] 回复ggdsn：模拟器与真机可能有差别，其实只要了解这一点就行了，还是要以真机为准的，谢谢。
9楼 LeeJ90 2013-11-28 14:30发表 [回复] 我测试的时候为什么onSaveInstanceState是在onPause之后，onStop之前呢？Re: 晕代码 2015-05-12 21:41发表 [回复] 回复lzt623459815：我也是，应该没什么大问题把
8楼 王子的秘书 2013-08-02 18:43发表 [回复] 谢谢分享，我已经转载了，供以后自己查看。
7楼 王子的秘书 2013-08-02 16:55发表 [回复] 如何转载？
6楼 singsong 2013-05-10 16:30发表 [回复] 写的很好, 有个问题是 
android4.0 禁止横竖屏切换使用 android:configChanges="orientation|keyboardHidden"无效 
需要加上 "screenSize" 
修改为:android:configChanges="orientation|keyboardHidden|screenSize" 希望博主更新~Re: liuhe688 2013-07-26 16:44发表 [回复] 回复sing_song：谢谢，已更新
5楼 mowangsheng 2013-03-06 15:52发表 [回复] 非常感谢，这篇文章对我有帮助，所以决定搬走它，谢谢。
4楼 syy19930112 2012-09-26 10:51发表 [回复] 很好啊
3楼 zhangwxly 2012-06-07 08:16发表 [回复] 终于明白了，感谢！
2楼 MyIriya001 2012-05-08 15:35发表 [回复] 您好，我申请将您的基础总结篇（目前9篇）转载到我的新浪博客上。
我会标注每篇博客对应的网址和作者名。如果您有意见的话，请在博客留言，我会删除的。
感谢！
我的博客地址：
http://blog.sina.com.cn/sc83419201Re: liuhe688 2012-05-10 09:37发表 [回复] 回复MyIriya001：欢迎转载，这样有利于技术交流，我怎么会有意见呢，呵呵。
1楼 sd379548557 2011-09-19 17:52发表 [回复] 太好了，顶了
发表评论
用 户 名：
merbn
评论内容：
插入代码

提交  
* 以上用户言论只代表其个人观点，不代表CSDN网站的观点或立场
核心技术类目
全部主题 Hadoop AWS 移动游戏 Java Android iOS Swift 智能硬件 Docker OpenStack VPN Spark ERP IE10 Eclipse CRM JavaScript 数据库 Ubuntu NFC WAP jQuery BI HTML5 Spring Apache .NET API HTML SDK IIS Fedora XML LBS Unity Splashtop UML components Windows Mobile Rails QEMU KDE Cassandra CloudStack FTC coremail OPhone  CouchBase 云计算 iOS6 Rackspace  Web App SpringSide Maemo Compuware 大数据 aptech Perl Tornado Ruby Hibernate ThinkPHP HBase Pure Solr Angular Cloud Foundry Redis Scala Django Bootstrap
个人资料
访问我的空间  
liuhe688
 
 1
访问：2353457次
积分：9539
等级： 
排名：第1223名
原创：87篇转载：0篇译文：1篇评论：1476条
文章分类
Front End(28)
Android(32)
Algorithm(14)
English(6)
Java(9)
Others(4)
阅读排行
Android中SQLite应用详解(349206)
基础总结篇之一：Activity生命周期(324901)
详解Android中AsyncTask的使用(220848)
Android中解析XML(162404)
基础总结篇之二：Activity的四种launchMode(156603)
基础总结篇之五：BroadcastReceiver应用详解(152369)
Android中处理崩溃异常(84487)
SQLite数据库简介(66105)
Android中使用HTTP服务(58660)
Android中ListView分页加载数据(54749)
评论排行
Android中SQLite应用详解(189)
基础总结篇之五：BroadcastReceiver应用详解(153)
基础总结篇之二：Activity的四种launchMode(151)
详解Android中AsyncTask的使用(123)
Android中处理崩溃异常(108)
基础总结篇之一：Activity生命周期(96)
Android中解析XML(91)
浅析Android中的消息机制(55)
使用ANT打包Android应用(39)
Android中ListView分页加载数据(34)
最新评论
Android中SQLite应用详解
fengding123456: 牛逼
基础总结篇之四：Service完全解析
太阳中的信仰: 写的非常好，作者很厉害！
Android中处理崩溃异常
zhuobattle: 现在基本上都集成第三方的crash捕捉平台了.否则在用户手机上崩了，你还得自己收集。
几种swap算法简介
GnakIewiy: 楼主都试过么？最后一种真的可行么？
浅析Android中的消息机制
timjx: 谢谢博主的分享，一直在看android系类的文章，学到很多您的分析的。祝福您工作顺利，博客人气杠杠的...
基础总结篇之一：Activity生命周期
JackingNo1: @f360898864:是讲的更细了
基础总结篇之五：BroadcastReceiver应用详解
Bulls23丶: 不错 学习了
使用AIDL实现进程间的通信
nowhere___boy: 讲解非常精彩,言简意赅，自己敲一遍就马上明白了，感想楼主
基础总结篇之五：BroadcastReceiver应用详解
坚持是成功唯一的法宝: reew
基础总结篇之一：Activity生命周期
代码运行异常: 自从Android 3.2（API 13），在设置Activity的android:configCh...
公司简介|招贤纳士|广告服务|银行汇款帐号|联系方式|版权声明|法律顾问|问题报告|合作伙伴|论坛反馈
网站客服杂志客服微博客服webmaster@csdn.net400-600-2320|北京创新乐知信息技术有限公司 版权所有|江苏乐知网络技术有限公司 提供商务支持
京 ICP 证 09002463 号|Copyright © 1999-2014, CSDN.NET, All Rights Reserved GongshangLogo
