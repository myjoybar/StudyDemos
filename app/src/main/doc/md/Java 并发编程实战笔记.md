#####  21.  Java 并发编程实战（https://blog.csdn.net/jeffleo/article/details/53767743）
> 1. 线程安全性
    1. 原子性：子性从字面意思上看就是，要么全部都做，要么全部都不做。具有原子性的操作是线程安全的，例如 i = 1；不具有原子性的操作不是线程安全的，例如i++。因为i++实际上分为三步，读取i，将值加1，写回i。（读取—修改—写入）
    2. concurrent.atomic包实现原子操作
        1. - 基本类：AtomicInteger， AtomicLong，AtomicBoolean 
        2.  引用类型：AtomicReference 
        3. 数组类型：AtomicIntegerArray，AtomicLongArray，AtomicReferenceArra

> 2. 线程安全性
    1.  内置锁（隐式锁）
        1.  Java提供了一种内置的锁机制来支持原子性：同步代码块（Synchronized），同步代码块包括两部分：一个作为锁的对象引用，一个作为由这个锁保护的代码块。
    2. 内置锁的特性   
        1. 自动获得和释放：
        2. 互斥性
        3. 可重入性： 可重入是指对于同一个线程，它可以重新获得已有它占用的锁。    

> 3. 内存可见性和指令重排序
    1. Java内存模型，规定了jvm有主内存（Main Memory）和工作内存（Working Memory），主内存主要包括堆和方法区，而工作内存主要包括栈和寄存器
    2. 并发编程中的三个概念
        1. 原子性:原子性：即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
        2. 可见性:见性是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
        3. 有序性:即程序执行的顺序按照代码的先后顺序执行。举个简单的例子，看下面这段代码：
        4. 也就是说，要想并发程序正确地执行，必须要保证原子性、可见性以及有序性。只要有一个没有被保证，就有可能会导致程序运行不正确。
    3. 指令重排序不会影响单个线程的执行，但是会影响到线程并发执行的正确性。
    4. Java内存模型
        1. 　Java内存模型规定所有的变量都是存在主存当中（类似于前面说的物理内存），每个线程都有自己的工作内存（类似于前面的高速缓存）。线程对变量的所有操作都必须在工作内存中进行，而不能直接对主存进行操作。并且每个线程不能访问其他线程的工作内存。
    5. 深入剖析volatile关键字
        1. 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
        2. 禁止进行指令重排序。
        3. 记住：valatile仅仅能保证内存可见性，而不能保证原子性
    6. 使用volatile关键字的场景
        1. 　synchronized关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，而volatile关键字在某些情况下性能要优于synchronized，但是要注意volatile关键字是无法替代synchronized关键字的，因为volatile关键字无法保证操作的原子性。通常来说，使用volatile必须具备以下2个条件
           1. 对变量的写操作不依赖于当前值
           2. 该变量没有包含在具有其他变量的不变式中
           3. 我的理解就是上面的2个条件需要保证操作是原子性操作，才能保证使用volatile关键字的程序在并发时能够正确执行。
           4.   （简单得说：volatile适合get或set场景，而不适合getAndOperate场景，因为这种不是原子场景）
           
> 4. 发布与逸出
    1. 发布：发布一个对象的意思是，使对象能够在当前作用域之外的代码中使用
    2. 逸出：当某个不应该发布的对象被发布时，这种情况就是逸出。 
       1. 最常见的逸出是this引用在构造时逸出，导致this引用逸出的常见错误有：
          1. 内部类的实例包含了对封装实例的隐含引用（深入理解 内部类），可能导致隐式this逸出   
       2. 为什么需要内部类
          1. 我认为内部类存在最重要的意义在于，它完善了Java的多重继承机制 
没有内部类的情况下，我么使用多重继承实现只能通过接口，但是接口的局限在于，实现接口，就必须实现它所有的方法，这就是什么在能使用接口和类的情况下，优先使用类。 
假如一个人，他想要拥有吃饭和睡觉两种能力，但只能通过继承类来获得这种能力，只能通过内部类。 

            
> 5. 线程封闭
   1. 当访问共享的可变数据时，通常需要同步。一种避免使用同步的方式就是不同享数据，这叫做线程封闭。java提供了一些机制来维持线程封闭性，例如局部变量和ThreadLocal类。 
   2. 栈封闭（局部变量）：
   3. ThreadLocal类：ThreadLocal线程本地变量是一种规范化的维护线程限制的方式，它允许将每个线程与持有数值的对象关联在一起，为每个使用它的线程维护一份单独的拷贝。ThreadLocal提供了set和get访问器，get总是返回由当前线程通过set设置的最新值。
   4. 不可变对象  
   
> 6. 构建线程安全
   1. 用组合来实现线性安全
   2. Java类库的基础并发构建模块 ：
      1. 同步容器类包括Vector和HashTable，以及使用Collections.synchronizedXxx（例如Collections.synchronizedList（new ArrayList< T>()））等工厂方法创建的同步类。
      2. 这些类实现线程安全的方式是：把他们的状态封装起来，并对每一个共有方法都进行同步，使得每次只有一个线程能访问容器的状态。（从这个描述，可以看出，在高并发情况下可能效率是一个问题）
      3. 并发容器类：同步容器类将所有对容器状态访问都加了锁，以实现线程安全，代价就是严重降低了并发性，当多个线程竞争容器的锁时，吞吐量严重降低，而为了改善同步容器的性能，Java针对多个线程并发访问，提供了并发容器类。 
例如：
      4. ConcurrentMap，用来替代同步且基于散列的Map； 
CopyOnWriteArrayList，用于在遍历操作为主要操作的情况下代替同步的List。 
Queue和BlockingQueue等。
      5. 闭锁（Latch），FutureTask，信号量（Semaphore），栅栏（Barrier）
   3. 线程安全总结
      1. 可变状态越少，越容易保证线程安全性
      2. 尽量将域声明为final，除非需要它们是可变的
      3. 不可变对象一定是线程安全的
      4. 封装有利于管理复杂性
      5. 用锁来保护每一个可变变量
      6. 当保护同一个不变性条件的所有变量时，要使用同一个锁（最容易忽略）
      7. 在执行复合操作时，要持有锁
> 7. 使用Executor框架
> 8. 活跃性和性能，死锁和显示锁
      1. 活跃性危险：死锁：
      2. 定义：当一个线程永远占有一个锁, 而其他线程尝试去获得这个锁, 那么它们将永远被阻塞。 
      3. 死锁的避免与分析: 
         1. 有界线程池/资源池与相互依赖的任务不能一起使用
         2. 如果要获取多个锁，必须考虑锁的顺序，尽可能使用开放调用
         3. 使用显示锁检测死锁，并且可以从死锁中恢复过来
         4. 通过线程转储信息来分析死锁
         4.public class Test {
    private final Object left = new Object();
    private final Object right = new Object();
    public void leftRight(){
        synchronized (left) {
            synchronized (right) {
                //do
            }
        }
    }
    public void rightLeft(){
        synchronized (right) {
            synchronized (left) {
                //do
            }
        }
    }
}

> 9. 性能和可伸缩性
    1. 使用线程的性能开销
       1. 上下文切换 :如果可运行的线程数大于CPU属相，那么操作系统会将某个正在运行的线程调度出来，保存当前线程执行的上下文（以便下次再次调度能保存进度），调度其他线程使用CPU，并将新调度的线程的上下文设置为当前的上下文。
       2. 内存同步
       3. 阻塞 
    2. 减少锁竞争提升性能
       1. 减少锁的持有时间 
       2. 降低锁的请求频率 
       3. 使用其它带有协调机制的锁
       4. 显式锁（Lock）
          1. 与内置锁不同，Lock提供了一种无条件的，可轮询的，定时的以及可中断的锁获取操作，所有加锁和解锁都是显式的
          2. ReentrantLock和synchronized的比较
             1. ReentrantLock实现了Lock接口，提供了与synchronized同样的互斥性和可见性，也同样提供了可重入性。
             2. 内置锁存在一些功能限制：无法中断一个正在等待获取锁的线程，无法获取一个锁时无限得等待下去。ReentrantLock更加灵活，能提供更好的活跃性和性能
             3. 置锁的释放时自动的，而ReentrantLock的释放必须在finally手动释放
    3. 总结 ：
       1. ReentrantLock在性能上优于内置锁，但是内置所为更多工作人员熟悉，并且代码简洁，而ReentrantLock的危险性比synchronized更高（通常是忘记调用unlock），因此仅当需要一些高级功能（可定时，可轮询与可中断的锁获取操作，公平队列，以及非块结构的锁），synchronized不能满足需求时，使用ReentrantLock，否则还是应该使用synchronized。
       2. lockInterruptibly（）:lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果其他线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
    4. ReentrantLock的高级功能
      1. 可中断的锁获取操作:void lockInterruptibly() throws InterruptedException;
      2. 带有时间限制的加锁
    5.  公平锁和非公平锁
       1. 在ReentrantLock的构造函数中提供了两种公平性选择：创建一个非公平的锁（默认），或者创建一个公平锁。
       2.      //默认不公平锁
    public ReentrantLock() {
        sync = new NonfairSync();
    }
    //选择公平或不公平
    public ReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
    } 
       2. 公平锁：线程按照它们发出请求的顺序来获得锁  
       3. 非公平锁：允许插队，当一个线程请求非公平锁时，如果在发出请求的同时该锁的状态变为可用，那么这个线程将跳过队列中所有等待线程并获得该锁
读—写锁
   6. 读—写锁
      1. ReentrantLock是一种互斥的操作，虽然能避免“写—写”冲突，也避免了“写—读”冲突，但也避免了“读—读”冲突。在多数情况下，操作都是读操作，那么如果允许“读—读”操作，将会大幅度得提升性能。 
      2. 这时，可以使用读—写锁：一个资源可以被多个读操作访问，或者被一个写操作访问，但两者不能同时访问。
      3. public interface ReadWriteLock {
            Lock readLock();
            Lock writeLock();
        }
      4. 读—写锁有一下需要考虑的点
      5. 一个用读—写锁来包装map
> 10. 深入理解同步实现
    1. 构建自定义的同步工具
       1. 内置的条件队列:wait notify
       2.  Condition对象
          1. 内置的条件队列有一些缺陷，每一个内置锁都只能由一个相关联的条件队列。如果想要编写一个带有多个条件谓语的并发对象，可以使用Lock和Condition。
          2. 一个Condition和一个单独的Lock相关联, 调用Lock.newCondition()方法, 可以创建一个Condition。每个Lock可以有任意数量的Condition对象. wait, notify, notifyAll在Condition中都有对应的：await, signal, signalAll, 而且一定要使用后者!
      3.  AbstractQueuedSynchronizer（AQS）
      4. 同步的实现都是基于AbstractQueuedSynchronizer（AQS），AQS是一个用于构建锁和同步器的框架，例如ReentrantLock，Semaphore，CountDownLatch等都是基于AQS构建的。
         1. AQS负责同步容器类中的状态，它管理了一个整数状态信息，可以通过getState，setState以及compareAndSetState来设置和获取。例如ReentrantLock用它来表示线程已经重复获取该锁的次数，Semaphore用它来表示剩余的许可数量，FutureTask用它来表示任务的状态（尚未开始，正在运行，已完成以及以取消）
      5. java.util.concurrent同步类中的AQS
          1. java.util.concurrent同步类都没有直接扩展AQS，而是将他们的功能委托给AQS子类实现。
          2.  java.util.concurrent中基于AQS开发的类有：ReentrantLock，Semaphore，ReentrantReadWriteLock，CountDownLatch，SynchronousQueue和FutureTask等。
      6. 原子变量和非阻塞同步机制
          1. 非阻塞算法被广泛用于操作系统和JVM中实现线程/进程调度机制，垃圾回收机制以及锁和其它并发数据结构 
      7. 比较并交换（CAS）   
         1. 比较并交换有三个操作数: 内存位置V，进行比较的值A，新值B 
         2. 当且仅当V的值等于旧值A时, CAS才会用新值B原子化地更新V的值, 否则它什么都不会做。  
      8. 非阻塞算法
         1. 非阻塞算法：如果在某种算法中，一个线程的失败或者挂起不会导致其他线程也失败或挂起，那么这种算法就被称为非阻塞算法。
         2. 无锁算法：如果在算法的每一个步骤都存在某个线程能够执行下去，那么这种算法称为无锁算法。
         3. 无阻塞，无锁算法：如果算法中仅将CAS用于协调线程之间的操作，并且能够正确地实现，那么它是一种无阻塞，无锁算法。
         4. 上面基于CAS实现的线程安全的计数器就是无阻塞算法实现
         

 

       

 