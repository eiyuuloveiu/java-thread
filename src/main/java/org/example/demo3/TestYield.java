package org.example.demo3;

/**
 * 线程礼让
 * 要点：
 * 1、会让当前线程暂停，但是不会阻塞（即不会进入阻塞状态）
 * 2、线程状态从运行状态转变为就绪状态（不是进入阻塞状态）
 * 3、礼让不一定会成功，取决于cpu调度
 */
public class TestYield {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();

        Thread thread1 = new Thread(runnable, "a");
//        thread1.setPriority(10);
        thread1.start();
        Thread thread2 = new Thread(runnable, "b");
//        thread2.setPriority(1);
        thread2.start();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " 结束");
    }
}
