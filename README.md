# hello-thread
thread的相关学习

> Synchronized 和 Lock 区别
1. Synchronized 内置java关键字， Lock是一个java类。
2. Synchronized 无法判断获取锁的状态， Lock可以判断是否获取到了锁。
3. Synchronized 会自动释放锁，Lock 必须要手动释放锁。如果不释放锁，将会导致死锁。
4. Synchronized 线程1(获得锁,阻塞)、线程2(等待,傻傻的等);Lock锁就不一定会等待下去(lock.tryLock())。
5. Synchronized 可重入锁，不可以中断的，非公平；Lock，可重入锁，可以判断锁，非公平的(可以自己设置)。
6. Synchronized 适合锁少量的代码同步问题,Lock适合锁大量的同步代码块。

::: tip
this is tip
:::
