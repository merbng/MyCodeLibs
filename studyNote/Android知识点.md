
## [Lambda表达式](http://blog.csdn.net/ys743276112/article/details/51722047)
>lambda表达式，它将允许我们将行为传到函数里。
    在Java 8之前，如果想将行为传入函数，仅有的选择就是匿名类，需要6行代码。
    而定义行为最重要的那行代码，却混在中间不够突出。
    Lambda表达式取代了匿名类，取消了模板，允许用函数式风格编写代码。
    这样有时可读性更好，表达更清晰。
## [LeakCanary](http://www.tuicool.com/articles/3MVzAb)
>Lamdba示例
+ 用lambda表达式实现Runnable
使用lambda表达式可以替换匿名类，而实现Runnable接口是匿名类的最好示例。
Java 8之前的runnable实现方法，需要4行代码，而使用lambda表达式只需要一行代码。
只需要用() -> {}代码块替代整个匿名类。
```
// Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("在Java8之前, 需要写很多代码");
            }
        }).start();
 //Java 8方式：
 new Thread( () -> System.out.println("使用Java8, Lambda表达式一目了然") ).start();
```
>### 有如下几个明显优点：
+ 针对Android Activity组件完全自动化的内存泄漏检查。
+ 可定制一些行为（dump文件和leaktrace对象的数量、自定义例外、分析结果的自定义处理等）。
+ 集成到自己工程并使用的成本很低。
+ 友好的界面展示和通知。

>### 基本工作原理如下：
1. RefWatcher.watch() 创建一个 KeyedWeakReference 到要被监控的对象。
1. 然后在后台线程检查引用是否被清除，如果没有，调用GC。
1. 如果引用还是未被清除，把 heap 内存 dump 到 APP 对应的文件系统中的一个 .hprof 文件中。
1. 在另外一个进程中的 HeapAnalyzerService 有一个 HeapAnalyzer 使用HAHA 解析这个文件。
1. 得益于唯一的 reference key, HeapAnalyzer 找到 KeyedWeakReference，定位内存泄漏。
1. HeapAnalyzer 计算 到 GC roots 的最短强引用路径，并确定是否是泄漏。如果是的话，建立导致泄漏的引用链。
-引用链传递到 APP 进程中的 DisplayLeakService， 并以通知的形式展示出来。
---
## [常见的内存泄漏及解决方案](http://m.blog.csdn.net/article/details?id=51579080)
>### 单例造成的内存泄漏
+ 因为单例的静态特性使得单例的生命周期和应用的生命周期一样长，这就说明了如果一个对象已经不需要使用了，而单例对象还持有该对象的引用，那么这个对象将不能被正常回收，这就导致了内存泄漏。
>### 非静态内部类创建静态实例造成的内存泄漏
+ 在Java 中，非静态匿名内部类会持有其外部类的隐式引用，如果你没有考虑过这一点，那么存储该引用会导致Activity被保留，而不是被垃圾回收机制回收。Activity对象持有其View层以及相关联的所有资源文件的引用，换句话说，如果你的内存泄漏发生在Activity中，那么你将损失大量的内存空间。
>### Handler造成的内存泄漏
+ 当使用内部类（包括匿名类）来创建Handler的时候，Handler对象会隐式地持有一个外部类对象（通常是一个Activity）的引用（不然你怎么可能通过Handler来操作Activity中的View？）。而Handler通常会伴随着一个耗时的后台线程（例如从网络拉取图片）一起出现，这个后台线程在任务执行完毕（例如图片下载完毕）之后，通过消息机制通知Handler，然后Handler把图片更新到界面。然而，如果用户在网络请求过程中关闭了Activity，正常情况下，Activity不再被使用，它就有可能在GC检查时被回收掉，但由于这时线程尚未执行完，而该线程持有Handler的引用（不然它怎么发消息给Handler？），这个Handler又持有Activity的引用，就导致该Activity无法被回收（即内存泄露），直到网络请求结束（例如图片下载完毕）。
>### 资源对象没关闭造成的内存泄漏
+ 资源性对象比如（Cursor，File文件等）往往都用了一些缓冲，我们在不使用的时候，应该及时关闭它们，以便它们的缓冲及时回收内存。它们的缓冲不仅存在于java虚拟机内，还存在于java虚拟机外。如果我们仅仅是把它的引用设置为null,而不关闭它们，往往会造成内存泄漏。因为有些资源性对象，比如SQLiteCursor（在析构函数finalize(),如果我们没有关闭它，它自己会调close()关闭），如果我们没有关闭它，系统在回收它时也会关闭它，但是这样的效率太低了。因此对于资源性对象在不使用的时候，应该调用它的close()函数，将其关闭掉，然后才置为null.在我们的程序退出时一定要确保我们的资源性对象已经关闭。
>### Bitmap没有回收导致的内存溢出
+ Bitmap的不当处理极可能造成OOM，绝大多数情况都是因这个原因出现的。Bitamp位图是Android中当之无愧的胖小子，所以在操作的时候当然是十分的小心了。由于Dalivk并不会主动的去回收，需要开发者在Bitmap不被使用的时候recycle掉。使用的过程中，及时释放是非常重要的。同时如果需求允许，也可以去BItmap进行一定的缩放，通过BitmapFactory.Options的inSampleSize属性进行控制。如果仅仅只想获得Bitmap的属性，其实并不需要根据BItmap的像素去分配内存，只需在解析读取Bmp的时候使用BitmapFactory.Options的inJustDecodeBounds属性。最后建议大家在加载网络图片的时候，使用软引用或者弱引用并进行本地缓存
>### 构造Adapter时，没有使用缓存的convertView
+ 以构造ListView的BaseAdapter为例，在BaseAdapter中提供了方法：
  public View getView(int position, ViewconvertView, ViewGroup parent)
       来向ListView提供每一个item所需要的view对象。初始时ListView会从BaseAdapter中根据当前的屏幕布局实例化一定数量的view对象，同时ListView会将这些view对象缓存起来。当向上滚动ListView时，原先位于最上面的list item的view对象会被回收，然后被用来构造新出现的最下面的list item。这个构造过程就是由getView()方法完成的，getView()的第二个形参View convertView就是被缓存起来的list item的view对象(初始化时缓存中没有view对象则convertView是null)。由此可以看出，如果我们不去使用convertView，而是每次都在getView()中重新实例化一个View对象的话，即浪费资源也浪费时间，也会使得内存占用越来越大
## 预防OOM的几点建议
1. __合理使用static：__
>每一个非静态内部类实例都会持有一个外部类的引用，若该引用是Activity 的引用，那么该Activity在被销毁时将无法被回收。如果你的静态内部类需要一个相关Activity的引用以确保功能能够正常运行，那么你得确保你在对象中使用的是一个Activity的弱引用，否则你的Activity将会发生意外的内存泄漏。但是要注意，当此类在全局多处用到时在这样干，因为static声明变量的生命周期其实是和APP的生命周期一样的，有点类似与Application。如果大量的使用的话，就会占据内存空间不释放，积少成多也会造成内存的不断开销，直至挂掉。static的合理使用一般用来修饰基本数据类型或者轻量级对象，尽量避免修复集合或者大对象，常用作修饰全局配置项、工具类方法、内部类。
2. __善用SoftReference/WeakReference/LruCache__
>Java、Android中有没有这样一种机制呢，当内存吃紧或者GC扫过的情况下，就能及时把一些内存占用给释放掉，从而分配给需要分配的地方。答案是肯定的，java为我们提供了两个解决方案。如果对内存的开销比较关注的APP，可以考虑使用WeakReference，当GC回收扫过这块内存区域时就会回收；如果不是那么关注的话，可以使用SoftReference，它会在内存申请不足的情况下自动释放，同样也能解决OOM问题。同时Android自3.0以后也推出了LruCache类，使用LRU算法就释放内存，一样的能解决OOM，如果兼容3.0一下的版本，请导入v4包。关于第二条的无关引用的问题，我们传参可以考虑使用WeakReference包装一下。
3. __谨慎handler__
>在处理异步操作的时候，handler + thread是个不错的选择。但是相信在使用handler的时候，大家都会遇到警告的情形，这个就是lint为开发者的提醒。handler运行于UI线程，不断处理来自MessageQueue的消息，如果handler还有消息需要处理但是Activity页面已经结束的情况下，Activity的引用其实并不会被回收，这就造成了内存泄漏。解决方案，一是在Activity的onDestroy方法中调handler.removeCallbacksAndMessages(null);取消所有的消息的处理，包括待处理的消息；二是声明handler的内部类为static。
4. __不要总想着Java 的垃圾回收机制会帮你解决所有内存回收问题__
> 我们以为垃圾回收机制会帮我们将不需要使用的内存回收，例如：我们需要结束一个Activity，那么它的实例和相关的线程都该被回收。但现实并不会像我们剧本那样走。Java线程会一直存活，直到他们都被显式关闭，抑或是其进程被Android系统杀死。所以，为你的后台线程实现销毁逻辑是你在使用线程时必须时刻铭记的细节，此外，你在设计销毁逻辑时要根据Activity的生命周期去设计，避免出现Bug。
5. __ListView和GridView的item缓存__
> + __convertView重用__  

> ListView中的每一个Item显示都需要Adapter调用一次getView()的方法，这个方法会传入一个convertView的参数，这个方法返回的View就是这个Item显示的View。Android提供了一个叫做Recycler(反复循环)的构件，就是当ListView的Item从滚出屏幕视角之外，对应Item的View会被缓存到Recycler中，相应的会从生成一个Item，而此时调用的getView中的convertView参数就是滚出屏幕的缓存Item的View，所以说如果能重用这个convertView，就会大大改善性能。
> + __使用ViewHolder重用__  

> RecyclerView是经典的ListView的进化与升华，它比ListView更加灵活，但也因此引入了一定的复杂性。最新的v7支持包新添加了RecyclerView。RecyclerView提供了一种插拔式的体验，高度的解耦，异常的灵活，通过设置它提供的不同LayoutManager，ItemDecoration , ItemAnimator实现令人瞠目的效果。而且RecyclerView内部为我们处理了item缓存，所以用着效率更高，更安全

+ #### [升级gradle的方法](http://www.orzapp.com/?p=269)
+ #### [GreenDAO3.2初探](http://mp.weixin.qq.com/s/aEUSsSo3heKDrD2jeDLRmw)
> + [<font color=#FF8800 size=3>GreenDao 使用详解</font>](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0703/8144.html)
+ #### [Android模块化](http://www.jianshu.com/p/910911172243)
+ #### [20个学习Kotlin的优质资源](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0605/8036.html)
> + [<font color=#FF8800 size=3>Kotlin 资源大全 - 学 Kotlin 看这个就够了</font>](http://mp.weixin.qq.com/s/qtvELk-A2Fg0mxk3S41AAQ)
+ #### [进入退出动画（右进右出动画 ）](http://blog.csdn.net/jdsjlzx/article/details/43736707)
+ #### [酷炫的RecyclerView开源库整理](http://www.jianshu.com/p/154891851fe2)
+ #### [酷炫的RecyclerView开源库整理](http://www.jianshu.com/p/154891851fe2)
+ #### [颜色选择器](https://github.com/Merbn/colorpicker)
+ #### [扫描二维码、条形码、获取图片识别、生成带 Logo](https://github.com/bingoogolapple/BGAQRCode-Android)
+ #### [视差图片控件](https://github.com/flavioarfaria/KenBurnsView)
+ #### [材料设计搜索框](https://github.com/MiguelCatalan/MaterialSearchView)
+ #### [Android 文件下载引擎，稳定、高效、灵活、简单易用](https://github.com/lingochamp/FileDownloader)
+ #### [实现dialog的3D翻转](http://www.jianshu.com/p/65f50b6ecaca?utm_source=desktop&utm_medium=timeline)