#####  1. Exception和Error有什么区别
> 1. Exception 和 Error 都是继承了 Throwa...
> 2. Error 是指在正常情况下，不大可能出现的情况，常见的比如 OutOfMemoryError 之类，都是它的子类
> 3. Exception 又分为可检查（checked）异常和不检查
> 4. 不检查异常就是所谓的运行时异常，类似 NullPointerException、ArrayIndexOutOfBoundsException...

#####  2. LeakCannary 
> 1. 监听 Activity 的生命周期
> 2. 在 onDestroy 的时候，创建相应的 Refrence 和 RefrenceQueue，并启动后台进程去检测
> 3. 一段时间之后，从 RefrenceQueue 读取，若读取不到相应 activity 的 Refrence，有可能发生泄露了，这个时候，再促发 gc，一段时间之后，再去读取，若在从 RefrenceQueue 还是读取不到相应 activity 的 refrence，可以断定是发生内存泄露了
> 4. 发生内存泄露之后，dump，分析 hprof 文件，找到泄露路径（使用 haha 库分析），发送到通知栏


#####  3. View的绘制流程
> 1. 结论： 在Activity onResume后，调用了View onAttachedToWindow 才会开始View measure
> 

#####  4. View事件分发
> 1. 结论： 在Activity onResume后，调用了View onAttachedToWindow 才会开始View measure
> 


#####  5. databinding
> 1. 单向数据绑定：实现数据变化自动驱动UI刷新的方式有三种：BaseObservable，ObservableField，ObservableCollection
> 2. 双向数据绑定：双向绑定的意思即为当数据改变时同时使视图刷新，而视图改变时也可以同时改变数据。

看以下例子，当EditText的输入内容改变时，会同时同步到变量goods，绑定变量的方式比单向绑定多了一个等号：android:text="@={goods.name}"

#####  6. List
>1. ArrayList的细节
 1. ArrayList的细节:细节1：ArrayList基于数组实现，访问元素效率快，插入删除元素效率慢
 2. ArrayList支持快速随机访问
 3. 大多数情况下，我们都应该指定ArrayList的初始容量
>2. Vector和Stack的细节
   1. Vector也是基于数组实现，同样支持快速访问，并且线程安全
   2. Stack继承于Vector，在其基础上扩展了栈的方法

>3. LinkedList
   1. LinkedList内部维护一个双端链表，可以从头开始检索，也可以从尾开始检索。同样的，得益于链表这一数据结构，LinkedList在插入和删除元素效率非常快。
   2. LinkedList可以当作队列和栈来使用
>4. 总结
   1. ArrayList和LinkedList适用于不同使用场景，应根据具体场景从优选择
   2. 根据ArrayList的扩容机制，我们应该开始就指定其初始容量，避免资源浪费
   3. LinkedList可以当作队列和栈使用，当然我们也可以进一步封装
   4. 尽量不使用Vector和Stack，同步场景下，使用SynchronizedList替代
>5. 总结
   1. 底层是双向链表存储数据,并且记录了头节点和尾节点
   2. 添加元素非常快,如果是添加到头部和尾部的话更快,因为已经记录了头节点和尾节点,只需要链接一下就行了. 如果是添加到链表的中间部分的话,那么多一步操作,需要先找到添加索引处的元素(因为需要链接到这里),才能进行添加.
   3. 遍历的时候,建议采用forEach()进行遍历,这样可以在每次获取下一个元素时都非常轻松(next = next.next;). 然后如果是通过fori和get(i)的方式进行遍历的话,效率是极低的,每次get(i)都需要从最前面(或者最后面)开始往后查找i索引处的元素,效率很低.
   4. 删除也是非常快,只需要改动一下指针就行了,代价很小.
  
#####  7. 线程池
>1. LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列
>2. SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等上一个元素被移除之后，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
>3.  CacheThreadPool 相比，FixedThreadPool 注释里描述的特性有几个不同的地方
   1. 因为 corePoolSize == maximumPoolSize ，所以FixedThreadPool只会创建核心线程。
   2. 因为 corePoolSize 为0的关系，CacheThreadPool不创建核心线程。 
   3. 在 getTask() 方法，如果队列里没有任务可取，线程会一直阻塞在 LinkedBlockingQueue.take() ，线程不会被回收。
   4. 由于线程不会被回收，会一直卡在阻塞，所以没有任务的情况下， FixedThreadPool 占用资源更多
   
#####  8. String
> 1. CharSequence是一个接口，它只包括length(), charAt(int index), subSequence(int start, int end)这几个API接口。除了String实现了CharSequence之外，StringBuffer和StringBuilder也实现了CharSequence接口
> 2. CharSequence就是字符序列，String, StringBuilder和StringBuffer本质上都是通过字符数组实现的！
> 
  
#####  9. android屏幕刷新显示机制(https://www.jianshu.com/p/0d00cb85fdf3)
>1. 显示过程，简单的说就是CPU/GPU准备好数据，存入buffer，display每隔一段时间去buffer里取数据，然后显示出来。display读取的频率是固定的，比如每个16ms读一次，但是CPU/GPU写数据是完全无规律的。
>2. 大体意思就是说，屏幕的刷新包括三个步骤：CPU 计算屏幕数据、GPU 进一步处理和缓存、最后 display 再将缓存中（buffer）的屏幕数据显示出来。
>3. QA
   1. Q1：Android 每隔 16.6 ms 刷新一次屏幕到底指的是什么意思？是指每隔 16.6ms 调用 onDraw() 绘制一次么？ 
Q2：如果界面一直保持没变的话，那么还会每隔 16.6ms 刷新一次屏幕么？ 
答：我们常说的 Android 每隔 16.6 ms 刷新一次屏幕其实是指底层会以这个固定频率来切换每一帧的画面，而这个每一帧的画面数据就是我们 app 在接收到屏幕刷新信号之后去执行遍历绘制 View 树工作所计算出来的屏幕数据。而 app 并不是每隔 16.6ms 的屏幕刷新信号都可以接收到，只有当 app 向底层注册监听下一个屏幕刷新信号之后，才能接收到下一个屏幕刷新信号到来的通知。而只有当某个 View 发起了刷新请求时，app 才会去向底层注册监听下一个屏幕刷新信号。
也就是说，只有当界面有刷新的需要时，我们 app 才会在下一个屏幕刷新信号来时，遍历绘制 View 树来重新计算屏幕数据。如果界面没有刷新的需要，一直保持不变时，我们 app 就不会去接收每隔 16.6ms 的屏幕刷新信号事件了，但底层仍然会以这个固定频率来切换每一帧的画面，只是后面这些帧的画面都是相同的而已。
   2. Q3：界面的显示其实就是一个 Activity 的 View 树里所有的 View 都进行测量、布局、绘制操作之后的结果呈现，那么如果这部分工作都完成后，屏幕会马上就刷新么？ 
答：我们 app 只负责计算屏幕数据而已，接收到屏幕刷新信号就去计算，计算完毕就计算完毕了。至于屏幕的刷新，这些是由底层以固定的频率来切换屏幕每一帧的画面。所以即使屏幕数据都计算完毕，屏幕会不会马上刷新就取决于底层是否到了要切换下一帧画面的时机了。
   3. Q4：网上都说避免丢帧的方法之一是保证每次绘制界面的操作要在 16.6ms 内完成，但如果这个 16.6ms 是一个固定的频率的话，请求绘制的操作在代码里被调用的时机是不确定的啊，那么如果某次用户点击屏幕导致的界面刷新操作是在某一个 16.6ms 帧快结束的时候，那么即使这次绘制操作小于 16.6 ms，按道理不也会造成丢帧么？这又该如何理解？ 
答：之所以提了这个问题，是因为之前是以为如果某个 View 发起了刷新请求，比如调用了 invalidte()，那么它的重绘工作就马上开始执行了，所以以前在看网上那些介绍屏幕刷新机制的博客时，经常看见下面这张图：
image.png
那个时候就是不大理解，为什么每一次 CPU 计算的工作都刚刚好是在每一个信号到来的那个瞬间开始的呢？毕竟代码里发起刷新屏幕的操作是动态的，不可能每次都刚刚好那么巧。
梳理完屏幕刷新机制后就清楚了，代码里调用了某个 View 发起的刷新请求，这个重绘工作并不会马上就开始，而是需要等到下一个屏幕刷新信号来的时候才开始，所以现在回过头来看这些图就清楚多了。
   4. Q5：大伙都清楚，主线程耗时的操作会导致丢帧，但是耗时的操作为什么会导致丢帧？它是如何导致丢帧发生的？ 
答：造成丢帧大体上有两类原因，一是遍历绘制 View 树计算屏幕数据的时间超过了 16.6ms；二是，主线程一直在处理其他耗时的消息，导致遍历绘制 View 树的工作迟迟不能开始，从而超过了16.6 ms 底层切换下一帧画面的时机。
第一个原因就是我们写的布局有问题了，需要进行优化了。而第二个原因则是我们常说的避免在主线程中做耗时的任务。
针对第二个原因，系统已经引入了同步屏障消息的机制，尽可能的保证遍历绘制 View 树的工作能够及时进行，但仍没办法完全避免，所以我们还是得尽可能避免主线程耗时工作。
其实第二个原因，可以拿出来细讲的，比如有这种情况， message 不怎么耗时，但数量太多，这同样可能会造成丢帧。如果有使用一些图片框架的，它内部下载图片都是开线程去下载，但当下载完成后需要把图片加载到绑定的 view 上，这个工作就是发了一个 message 切到主线程来做，如果一个界面这种 view 特别多的话，队列里就会有非常多的 message，虽然每个都 message 并不怎么耗时，但经不起量多啊。后面有时间的话，看看要不要专门整理一篇文章来讲卡顿和丢帧的事。
   
#####  10. Android系统各个版本的区别
> 1. 从 Android P 开始，系统会限制非 SDK 接口的使用。
> 
> 
   
#####  11. Https
> 1. HTTPS是如何保障安全的
    1. HTTPS相对于HTTP有哪些不同呢？其实就是在HTTP跟TCP中间加多了一层加密层TLS/SSL。
    2. 原先是应用层将数据直接给到TCP进行传输，现在改成应用层将数据给到TLS/SSL，将数据加密后，再给到TCP进行传输。
> 2. HTTPS是如何加密数据的
    1. 公钥如何获取:CA证书颁发机构
> 3. 流程
    1. 握手：证书下发，密钥协商（这个阶段都是明文的）
    2. 数据传输：这个阶段才是加密的，用的就是握手阶段协商出来的对称密钥
> 4. 整个简化的加密通信的流程就是
    1. 小明访问XX，XX将自己的证书给到小明（其实是给到浏览器，小明不会有感知）
    2. 浏览器从证书中拿到XX的公钥A
    3. 浏览器生成一个只有自己自己的对称密钥B，用公钥A加密，并传给XX（其实是有协商的过程，这里为了便于理解先简化）
    4. XX通过私钥解密，拿到对称密钥B
    5. 浏览器、XX 之后的数据通信，都用密钥B进行加密
> 5. Android Https
    1. Retrofit绑定证书实现HTTPS单项认证：对于上述两种情况中存在的安全隐患，我们应该如何应对？最简单的解决方案就是在客户端内置服务器的证书，我们在校验服务端证书的时候只比对和App内置的证书是否完全相同，如果不同则断开连接。那么此时再遭遇中间人攻击劫持我们的请求时由于黑客服务器没有相应的证书，此时HTTPS请求校验不通过，则无法与黑客的服务器建立起连接。
    2. HTTPS双项认证：单项认证保证了我们自己的客户端只能访问我们自己的服务器，但并不能保证我们自己的服务器只能被我们自己的客户端访问（第三方客户端忽略证书校验即可）。那么双向认证则保证了我们的客户端只能访问我们自己的服务器，同时我们的服务器也只能被我们自己的客户端访问。因此双向认证可以说相比单项认证安全性足足提高一个等级。
    
#####  12. RecyclerView中的DiffUtil
> 1. DiffUtil就是专门用于RecyclerView中数据有局部变化。使用DiffUtil时会计算出新旧集合中的最长公共子串，之后对数据集合进行增减，再进行局部变化。
> 2. Myers差分算法（https://www.jianshu.com/p/7f1473c2e521）
> 3. 通过分析可能大家已经知道了RecyclerView相对于ListView的区别，如果我们需要频繁的刷新列表数据以及添加动画的话，我们还是采用RecyclerView，对于前后数据量变化不大的新旧集合，还可以通过DiffUtil来进行差分，这样就能够实现定向刷新以及局部刷新，否则建议使用ListView，因为ListView内置了很多Adapter，类似ArrayAdapter，SimpleAdapter,CursorAdapter。
> 4. ListView缓存机制
    1. mCurrentScrap:ArrayList类型，用于存储离屏的 View
    2. mActiveView:ArrayList 类型，被 layoutChildren() 用于缓存屏幕上的 View。
    3. mScrapViews：
       1. ArrayList[] 类型
       2. 数组中每个元素都是 ArrayList类型，效果同 mCurrentScra
       3. mScrapViews[0] 就是 mCurrentScrap
       4. 其数组长度应为 ViewTypeCount。因为针对不同的 ViewType，ListView 都要有一个专门的 ArrayList 链表来缓存它对应的 View
       5. RecycleBin 机制中的 mCurrentScrap/mScrapViews[0] 的大小永远是1（只要被复用）。因为它至多只需要缓存一个 View，因为屏幕内容数量的最大值必定为初始时屏幕内容数量值 + 1，拿笔者的手机举例来说，初始值为4，屏幕内容数量的最大值则为 5。

#####  12.  EventBus 3.1.1
> 1. 编译时注解
> 2. sticky event，中文名为粘性事件。普通事件是先注册，然后发送事件才能收到；而粘性事件，在发送事件之后再订阅该事件也能收到。此外，粘性事件会保存在内存中，每次进入都会去内存中查找获取最新的粘性事件，除非你手动解除注册。

#####  13.  Android动画框架原理解析
> 1. 每个窗口是一颗View树. RootView是DecorView,在布局文件中声明的布局都是DecorView的子View.是通过setContentView来设置进入窗口内容的. 因为View的布局就是一棵树.所以绘制的时候也是按照树形结构来遍历每个View进行绘制.ViewRoot.java中 draw函数准备好Canvas后 调用 mView.draw(canvas),这里的mView是DecorView. 

#####  14. Android中的SystemClock类
> 1. System.currentTimeMillis() :是自1970-1-01 00:00:00.000 到当前时刻的时间距离,类型为long
> 2. uptimeMillis()：表示自系统启动时开始计数，以毫秒为单位。返回的是从系统启动到现在这个过程中的处于非休眠期的时间。当系统进入深度睡眠时(CPU关闭，设备变黑，等待外部输入装置)该时钟会停止。Handler
> 3. elapsedRealtime() and elapsedRealtimeNanos()：返回系统启动到现在的时间，包含设备深度休眠的时间。该时钟被保证是单调的，即使CPU在省电模式下，该时间也会继续计时。该时钟可以被使用在当测量时间间隔可能跨越系统睡眠的时间段。 AlarmManager，CountDownTimer

#####  15. Notification
> 1. RemoteView
> 2. RemoteView 理解成对一个 View 的封装,然后把 RemoteView 提交给其他线程。其他线程接收到 RemoteView 并且解析里面 View 的信息把它显示出来.
> 3. 在使用系统自带的 Notification 系统会创建一个默认的 RemoteView !
#####  16. MVP
> 1. 缺陷：冗杂，回调的时候View已经消失，异步耗时，大概率的出现context泄露
> 2.回调的时候View已经消失解决：
      1. View 用弱引用
      2. 在attatchView()的时候, 生成Activity的代理类, 在每个Activity方法被调用之前判空下 Activity, 如果Activity存在, 就让这个代理类执行更新ui的方法（https://www.jianshu.com/p/7bdb4d121c8c）
      
#####  15.  int和Integer有什么区别
> 1. ..
>
      
#####  16.  动态代理
> 1. retrofit 和 p中的应用，实现的更偏向一种拦截功能，只不过使用的非拦截器模式
> 2.  动态代理类的方法，都是清一色的动作，比如说 login，getUserInfo等等，而P中的动作则是多View 操作的动作，比如说refresh，showToas……
> 3. SpringMVC 中的Interceptor 拦截器也是相当重要和相当有用的，它的主要作用是拦截用户的请求并进行相应的处理。比如通过它来进行权限验证，或者是来判断用户是否登陆，或者是像12306 那样子判断当前时间是否是购票时间。

#####  17.  java API中Iterator接口

```
public Interface Iterator<E>{
    boolean hasNext();  
　　 Object next();  
　　 void remove(); 
　　 void forEachRemaining(Consumer<? super E> action)// 这是Java 8为Iterator新增的默认方法，该方法可使用Lambda表达式来遍历集合元素
}

```

>1. ArrayList 实现了iterator（） 
>2. LinkedList继承AbstractList ，由AbstractList实现iterator（） 
>

#####  18.  Java NIO
> 1. ..

#####  19.  设计原则SOLID
> 1. 单一职责：一个类应该有且只有一个去改变它的理由，这意味着一个类应该只有一项工作。
> 2. 开放封闭原则：对象或实体应该对扩展开放，对修改封闭。
> 3. 里氏替换原则：每一个子类或派生类应该可以替换它们基类或父类
> 4. 接口隔离原则：不应强迫客户端实现一个它用不上的接口，或是说客户端不应该被迫依赖它们不使用的方法
> 5. 依赖反转原则：实体必须依靠抽象而不是具体实现。它表示高层次的模块不应该依赖于低层次的模块，它们都应该依赖于抽象。

#####  20.  设计模式
> 1. 创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
> 2. 结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
> 3. 行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。


#####  21.  synchronized和ReentrantLock有什么区别
> 1. ReentrantLock提供了一个newCondition的方法，以便用户在同一锁的情况下可以根据不同的情况执行等待或唤醒的动作。
> 2. 这里我特别想强调条件变量（java.util.concurrent.Condition) 如果说 ReentrantLock 是 synchronized 的替代选择，Condition 则是将 wait、notify、notifyAll 等操作转化为相应的对象，将复杂而晦涩的同步操作转变为直观可控的对象行为。
> 3. 条件变量最为典型的应用场景就是标准类库中的 ArrayBlockingQueue 等。
> 4. ArrayBlockingQueue使用了两个condition来分别控制put和take的阻塞与唤醒

#####  22.并发包中的ConcurrentLinkedQueue和LinkedBlockingQueue有什么区别？
> 1. java.util.concurrent 包提供的容器（Queue、List、Set）、Map，从命名上可以大概区分为 Concurrent、CopyOnWrite和 Blocking* 等三类，同样是线程安全容器，可以简单认为：
> 2. 常见的集合中如 LinkedList 是个 Deque，只不过不是线程安全的
> 3. 从基本的数据结构的角度分析，有两个特别的Deque实现，ConcurrentLinkedDeque 和 LinkedBlockingDeque。Deque 的侧重点是支持对队列头尾都进行插入和删除，所以提供了特定的方法，如:
    1. 尾部插入时需要的addLast(e)、offerLast(e)。
    2. 尾部删除所需要的removeLast()、pollLast()。
> 4. ArrayBlockingQueue 是最典型的的有界队列，其内部以 final 的数组保存数据，数组的大小就决定了队列的边界，所以我们在创建 ArrayBlockingQueue 时，都要指定容量
> 5. LinkedBlockingQueue，容易被误解为无边界，但其实其行为和内部代码都是基于有界的逻辑实现的，只不过如果我们没有在创建队列时就指定容量，那么其容量限制就自动被设置为 Integer.MAX_VALUE，成为了无界队列
> 6. SynchronousQueue，这是一个非常奇葩的队列实现，每个删除操作都要等待插入操作，反之每个插入操作也都要等待删除动作。那么这个队列的容量是多少呢？是 1 吗？其实不是的，其内部容量是 0。
> 7. PriorityBlockingQueue 是无边界的优先队列，虽然严格意义上来讲，其大小总归是要受系统资源影响
> 8. DelayedQueue 和 LinkedTransferQueue 同样是无边界的队列。对于无边界的队列，有一个自然的结果，就是 put 操作永远也不会发生其他 BlockingQueue 的那种等待情况。
> 9. 通用场景中，LinkedBlockingQueue 的吞吐量一般优于 ArrayBlockingQueue，因为它实现了更加细粒度的锁操作。

#####  23.Listview 观察者模式
> 1. setAdapter时注册：mAdapter.registerDataSetObserver(mDataSetObserver);
> 2. 在BaseAdapter中，有个DataSetObservable，被观察者
> 3. DataSetObservable的内部维护着一个观察者集合，即源码中的mObservers。当我们的ListView绑定了Adapter，调用BaseAdapter的registerDataSetObserver方法时，实际上是在这个观察者集合mObservers里将该观察者添加进来。对ListView来说，这个观察者就是AdapterDataSetObserver 
> 4. BaseAdapter的notifyDataSetChanged() 会调用 mDataSetObservable.notifyChanged();再调用   mObservers.get(i).onChanged();
> 5. AbsListView的父类AdapterView的AdapterDataSetObserver
> 6. 在AdapterDataSetObserver的onChanged方法里面，调用了 checkFocus()，  requestLayout();
> 7. 可以换个角度理解，ListView 调用 setAdapter 方法，将一个观察者注入 Adapter 中维护的观察者序列，这样 ListView 和 Adapter 就建立起了关联，当有了数据变化，我们调用了 Adapter 的 notifyDataSetChanged 方法，内部会遍历出观察者，ListView是观察者，Adapter 是被观察者。ListView对Adapter订阅。有了数据更新后，Adapter通知ListView，告诉 ListView ，你可以更新视图了，之后 ListView调用 requestLayout 方法重新绘制界面，完成视图的更新。

#####  24.Java并发类库提供的线程池有哪几种？ 分别有什么特点？
> 1. 线程池大小的选择策略
     1. 果我们的任务主要是进行计算，那么就意味着 CPU 的处理能力是稀缺的资源，我们能够通过大量增加线程数提高计算能力吗？往往是不能的，如果线程太多，反倒可能导致大量的上下文切换开销。所以，这种情况下，通常建议按照 CPU 核的数目 N 或者 N+1。
     2. 如果是需要较多等待的任务，例如 I/O 操作比较多，可以参考 Brain Goetz 推荐的计算方法： 线程数 线程数 = CPU 核数 × （1 + 平均等待时间 / 平均工作时间）

#####  25.浅谈Java两种并发类型——计算密集型与IO密集型
> 1. 计算密集型任务：
     1. 计算密集型任务的特点是要进行大量的计算，消耗CPU资源，比如计算圆周率、对视频进行高清解码等等，全靠CPU的运算能力。这种计算密集型任务虽然也可以用多任务完成，但是任务越多，花在任务切换的时间就越多，CPU执行任务的效率就越低，所以，要最高效地利用CPU，计算密集型任务同时进行的数量应当等于CPU的核心数。
     2. 计算密集型任务由于主要消耗CPU资源，因此，代码运行效率至关重要。Python这样的脚本语言运行效率很低，完全不适合计算密集型任务。对于计算密集型任务，最好用C语言编写。
> 2. IO密集型
     1. 第二种任务的类型是IO密集型，涉及到网络、磁盘IO的任务都是IO密集型任务，这类任务的特点是CPU消耗很少，任务的大部分时间都在等待IO操作完成（因为IO的速度远远低于CPU和内存的速度）。对于IO密集型任务，任务越多，CPU效率越高，但也有一个限度。常见的大部分任务都是IO密集型任务，比如Web应用。
     2. IO密集型任务执行期间，99%的时间都花在IO上，花在CPU上的时间很少，因此，用运行速度极快的C语言替换用Python这样运行速度极低的脚本语言，完全无法提升运行效率。对于IO密集型任务，最合适的语言就是开发效率最高（代码量最少）的语言，脚本语言是首选，C语言最差。

#####  26.AtomicInteger底层实现原理是什么？如何在自己的产品代码中应用CAS操作？
> 1. AtomicIntger 是对 int 类型的一个封装，提供原子性的访问和更新操作，其原子性操作的实现是基于 CAS（compare-and-swap）技术
> 
#####  27.请介绍类加载过程，什么是双亲委派模型？
> 1. 

#####  28.Java 内存模型中的 happen-before 是什么
> 1. Happen-before 关系，是 Java 内存模型中保证多线程操作可见性的机制，也是对早期语言规范中含糊的可见性概念的一个精确定义。 它的具体表现形式，包括但远不止是我们直觉中的 synchronized、volatile、lock 操作顺序等方面，例如： 
     1. 线程内执行的每个操作,都保证 happen-before 后面的操作,这就保证了基本的程序顺序规则,这是开发者在书写程序时的基本约定。
     2. 对于 volatile 变量,对它的写操作,保证 happen-before 在随后对该变量的读取操作
     3. 对于一个锁的解锁操作,保证 happen-before 加锁操作。
     4. 对象构建完成,保证 happen-before 于 finalizer 的开始动作。
     5. 甚至是类似线程内部操作的完成,保证 happen-before 其他 Thread.join() 的线程等


#####  29.可从四个维度去理解JMM
> 1. 从JVM运行时视角来看，JVM内存可分为JVM栈、本地方法栈、PC计数器、方法区、堆；其中前三区是线程所私有的，后两者则是所有线程共有的
> 2. 从JVM内存功能视角来看，JVM可分为堆内存、非堆内存与其他。其中堆内存对应于上述的堆区；非堆内存对应于上述的JVM栈、本地方法栈、PC计数器、方法区；其他则对应于直接内存
> 3. 从线程运行视角来看，JVM可分为主内存与线程工作内存。Java内存模型规定了所有的变量都存储在主内存中；每个线程的工作内存保存了被该线程使用到的变量，这些变量是主内存的副本拷贝，线程对变量的所有操作（读取、赋值等）都必须在工作内存中进行，而不能直接读写主内存中的变量
> 4. 从垃圾回收视角来看，JVM中的堆区=新生代+老年代。新生代主要用于存放新创建的对象与存活时长小的对象，新生代=E+S1+S2；老年代则用于存放存活时间长的对象


#####  30.Lambda
> 1. 虽然 Lambda/Stream 为 Java 提供了强大的函数式编程能力，但是也需要正视其局限性：
    1. 一般来说,我们可以认为 Lambda/Stream 提供了与传统方式接近对等的性能,但是如果对于性能非常敏感,就不能完全忽视它在特定场景的性能差异了,例如:初始化的开销。 Lambda 并不算是语法糖,而是一种新的工作机制,在首次调用时,JVM 需要为其构建CallSite实例。这意味着,如果 Java 应用启动过程引入了很多Lambda 语句,会导致启动过程变慢。其实现特点决定了 JVM 对它的优化可能与传统方式存在差异。
    2. 增加了程序诊断等方面的复杂性,程序栈要复杂很多,Fluent 风格本身也不算是对于调试非常友好的结构,并且在可检查异常的处理方面也存在着局限性等。
     
#####  31.JVM 优化 Java 代码时都做了什么？
> 1. JVM 在对代码执行的优化可分为运行时(runtime)优化和即时编译器(JIT)优化。运行时优化主要是解释执行和动态编译通用的一些机制,比如说锁机制(如偏斜锁)、内存分配机制(如 TLAB)等。除此之外,还有一些专门用于优化解释执行效率的,比如说模版解释器、内联缓存(inline cache,用于优化虚方法调用的动态绑定)。
> 2. JVM 的即时编译器优化是指将热点代码以方法为单位转换成机器码,直接运行在底层硬件之上。它采用了多种优化方式,包括静态编译器可以使用的如方法内联、逃逸分析,也包括基于程序运行 profile 的投机性优化(speculative/optimistic optimization)。这个怎么理解呢?比如我有一条 instanceof 指令,在编译之前的执行过程中,测试对象的类一直是同一个,那么即时编译器可以假设编译之后的执行过程中还会是这一个类,并且根据这个类直接返回 instanceof 的结果。如果出现了其他类,那么就抛弃这段编译后的机器码,并且切换回解释执行。
> 3. 当然,JVM 的优化方式仅仅作用在运行应用代码的时候。如果应用代码本身阻塞了,比如说并发时等待另一线程的结果,这就不在 JVM 的优化范畴啦。
>  
 
#####  32. 零碎知识
> 1. Deque 接口继承自 Queue接口，但 Deque 支持同时从两端添加或移除元素，因此又被成为双端队列。鉴于此，Deque 接口的实现可以被当作 FIFO队列使用，也可以当作LIFO队列（栈）来使用。官方也是推荐使用 Deque 的实现来替代 Stack。
> 2. ArrayDeque 是 Deque 接口的一种具体实现，是依赖于可变数组来实现的。ArrayDeque 没有容量限制，可根据需求自动进行扩容。ArrayDeque不支持值为 null 的元素。
> 3. execute(Runnable x) 没有返回值。可以执行任务，但无法判断任务是否成功完成。——实现Runnable接口
submit(Runnable x) 返回一个future。可以用这个future来判断任务是否成功完成。——实现Callable接口
> 4. submit不管是Runnable还是Callable类型的任务都可以接受，但是Runnable返回值均为void，所以使用Future的get()获得的还是null
> 5. 这个问题问的好，Android的四大组件都建立在系统Binder机制之上，当启动一个组件的时候并不是单纯的建立一个组件对象，而是一系列的framework层初始化、Binder机制注册等工作，这也是为什么四大组件有别于其它普通类有生命周期的实现。如果你查看四大组件源码，四大组件类都是没有构造器的，也就是说默认构造器是无法完成组件初始化及启动工作的。直接new Activity或Service就好比new Thread而没有后续start，仅仅只是生成一个对象而已。这只是很肤浅的理解了，更深层次的理解可以去看Binder机制和研读组件代码了。> 6. 要知道因为 Activity 的启动，涉及到 Android 系统对 ActivityManager 的调度，会关联许多资源和进行诸多复杂运算.
> 7. inflate(resouce, root)
> 8. java中class.forName和classLoader加载类的区分:
    1.    java中class.forName和classLoader都可用来对类进行加载。前者除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。而classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象 
> 9. 报错的原因并不是因为他们拿到的Context资源不同，拿到的都是一个Resoucres对象，但是在创建Dialog的时候会使用到Context对象去获取当前主题信息，但是我们知道Application和Service是继承自ContextWrapper，没有实现关于主题的功能，然而Activity是继承自ContextThemeWrapper，该类是实现了关于主题功能的，因此创建Dialog的时候必须依附于Activity的Context引用。
> 10. 一个应用不管有多少个Activity，都只有一个WindowManager窗口管理器，也就只有一个WindowManagerGlobal对象通过维护三个数组 mViews，mRoots，mParams来管理所有窗口的添加，更新，删除。
> 11. 　CPU和GPU之所以大不相同，是由于其设计目标的不同，它们分别针对了两种不同的应用场景。CPU需要很强的通用性来处理各种不同的数据类型，同时又要逻辑判断又会引入大量的分支跳转和中断的处理。这些都使得CPU的内部结构异常复杂。而GPU面对的则是类型高度统一的、相互无依赖的大规模数据和不需要被打断的纯净的计算环境。

#####  33.AsyncTask>   
> 1. dobackGround 是在woker的call中执行的，Callable
> 2. 执行excute时，会执行execute时，SerialExecutor排队线程中的execute会被调用，然后把task包装成runnable一个一个的放入THREAD_POOL_EXECUTOR执行，THREAD_POOL_EXECUTOR执行时，实际上执行的是mFuture的run，而future中run又会调用woker的call方法，当call方法执行完毕之后，把结果返回，通过innerhannder抛到主线层。

#####  34.recyclerview
> 1. recyclerview性能并不比 ListView 要好多少，它最大的优势在于其扩展性，如果有大量局部数据更新时，建议用recyclerview。
> 2. recyclerview四级缓存，recyclerviewPool，RecyclerViewPool用于多个RecyclerView之间共享View。只需要创建一个RecyclerViewPool实例。
#####  35. 启动模式
> 1. standard 模式：再次startActivity(intent)，onCreate，onStart，onResume会执行
> 2. singleTop 模式：再次startActivity(intent)，onNewIntent，onResume会执行
> 3. singleTask 模式：再次startActivity(intent)，onNewIntent，onResume会执行
> 4. singleInstance 模式：再次startActivity(intent)，onNewIntent，onResume会执行
#####  36. try catch
> 1.  所以得到结论：线程方法的异常只能自己来处理。主线程catch不到这个异常   
> 2. 类会跟随一张 异常表（exception table），每一个try catch都会在这个表里添加行记录，每一个记录都有4个信息（try catch的开始地址，结束地址，异常的处理起始位，异常类名称）。
> 3. 当代码在运行时抛出了异常时，首先拿着抛出位置到异常表中查找是否可以被catch（例如看位置是不是处于任何一栏中的开始和结束位置之间），如果可以则跑到异常处理的起始位置开始处理，如果没有找到则原地return，并且copy异常的引用给父调用方，接着看父调用的异常表。。。以此类推。

#####  37. window
> 1. Token：窗口令牌，是一种特殊的Binder令牌，WMS用它唯一标示系统中的一个窗口。
> 2. Dialog跟Activity对应的窗口一样，有一个phonewindow实例，Dialog的类型是TYPE_APPLICATION，属于应用窗口类型。Dialog最终也是通过系统的WindowManager把自己的Window添加到WMS上，在addView前，Dialog的token是null，Dialog初始化时是通过Context.getSystemServer来获取WindowManager，而如果用Application或者Service的Context去获取这个WindowManager服务的话，会得到一个WindowManagerImpl的实例，这个实例里token也是空的，之后在Dialog的show方法中将Dialog的View添加到WindowManager时会给token设置默认值还是null。如果这个Context是Activity，则直接返回Activity的mWindowManager，这个mWindowManager在Activity的attach方法被创建，token指向此Activity的token。系统对TYPE_APPLICATION类型的窗口，要求必须是Activity的token，不是的话系统会抛出badtokenexception异常，Dialog是应用窗口类型，token必须是Activity的token。
> 3. 如果Dialog弹出来之前Activity已经被销毁了，则这个Dialog在弹出的时候就会抛出异常，因为token不可用了。在Dialog的构造函数中我们关联了新Window的callback事件监听处理，所以当Dialog显示时Activity无法消费当前的事件。
#####  38. inflate
> 1. root不为null，attachToRoot为true
    1. 当root不为null，attachToRoot为true时，表示将resource指定的布局添加到root中，添加的过程中resource所指定的的布局的根节点的各个属性都是有效的
    2. 当第三个参数为true时，会自动将第一个参数所指定的View添加到第二个参数所指定的View中。
    3. 不需要addview
> 2. root不为null，attachToRoot为false
    1. 如果root不为null，而attachToRoot为false的话，表示不将第一个参数所指定的View添加到root中，那么这个时候有的小伙伴可能就有疑问了，既然不添加到root中，那我还写这么多干嘛？我第二个参数直接给null不就可以了？其实不然，这里涉及到另外一个问题：我们在开发的过程中给控件所指定的layout_width和layout_height到底是什么意思？该属性的表示一个控件在容器中的大小，就是说这个控件必须在容器中，这个属性才有意义，否则无意义。这就意味着如果我直接将linearlayout加载进来而不给它指定一个父布局，则inflate布局的根节点的layout_width和layout_height属性将会失效（因为这个时候linearlayout将不处于任何容器中，那么它的根节点的宽高自然会失效）。如果我想让linearlayout的根节点有效，又不想让其处于某一个容器中，那我就可以设置root不为null，而attachToRoot为false。这样，指定root的目的也就很明确了，即root会协助linearlayout的根节点生成布局参数，只有这一个作用。
    2. 需要addview
>3. root为null
    1. 当root为null时，不论attachToRoot为true还是为false，显示效果都是一样的。当root为null表示我不需要将第一个参数所指定的布局添加到任何容器中，同时也表示没有任何容器来来协助第一个参数所指定布局的根节点生成布局参数。我还是使用上文提到的linearlayout，

    
