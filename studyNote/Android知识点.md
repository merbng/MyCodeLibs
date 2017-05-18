# __LeakCanary__
[原文](http://www.tuicool.com/articles/3MVzAb)
> 有如下几个明显优点：
+ 针对Android Activity组件完全自动化的内存泄漏检查。
+ 可定制一些行为（dump文件和leaktrace对象的数量、自定义例外、分析结果的自定义处理等）。
+ 集成到自己工程并使用的成本很低。
+ 友好的界面展示和通知。

> 基本工作原理如下：
1. RefWatcher.watch() 创建一个 KeyedWeakReference 到要被监控的对象。
1. 然后在后台线程检查引用是否被清除，如果没有，调用GC。
1. 如果引用还是未被清除，把 heap 内存 dump 到 APP 对应的文件系统中的一个 .hprof 文件中。
1. 在另外一个进程中的 HeapAnalyzerService 有一个 HeapAnalyzer 使用HAHA 解析这个文件。
1. 得益于唯一的 reference key, HeapAnalyzer 找到 KeyedWeakReference，定位内存泄漏。
1. HeapAnalyzer 计算 到 GC roots 的最短强引用路径，并确定是否是泄漏。如果是的话，建立导致泄漏的引用链。
-引用链传递到 APP 进程中的 DisplayLeakService， 并以通知的形式展示出来。
---