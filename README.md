# Java多线程讲解

## 一、进程、线程、多线程

多任务：可以一边看手机，一边吃饭，看着像是同时进行多个任务。但是只有一颗大脑，其实一个时刻只能做一件事情

多线程：原来只有一条路，慢慢因为车多了，道路拥堵，效率低下。为了提高效率，充分利用道路，于是加了多个车道。如生活、游戏、编程

进程与线程的关系

## 二、线程创建

继承Tread类、实现Runnable接口、实现Callable接口

不建议使用继承Thread类方式，存在OOP单继承局限性

建议使用实现Runnable接口，灵活方便，方便同一个对象被多个线程使用

两个线程对应同一个对象runnable  两者的任务相同。

两个线程对应两个Runnable对象 两者的任务有所不同。所以在共享资源类中对应着两个不同方法供使用

## 三、静态代理

## 四、Lambda表达式

## 五、线程终止

## 六、线程休眠

## 七、线程礼让

## 八、线程强制执行

## 九、观察线程状态

## 十、线程优先级

## 十一、守护线程

## 十二、线程同步机制

## 十三、3大不安全案例

## 十四、同步方法与同步方法块

## 十五、死锁

## 十六、Lock

## 十七、线程协作-生产者与消费者

## 十八、线程池


