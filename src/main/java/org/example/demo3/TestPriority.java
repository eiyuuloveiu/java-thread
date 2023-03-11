package org.example.demo3;

/**
 * 线程优先级
 * 要点：
 * 1、范围在1～10，默认为5
 * 2、优先级高表示被cpu调度的概率高，并不一定，概率问题
 */
public class TestPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());

        MyPriority runnable = new MyPriority();
        Thread t1 = new Thread(runnable);
        t1.setPriority(10);
        t1.start();

        Thread t2 = new Thread(runnable);
        t2.setPriority(8);
        t2.start();

        Thread t3 = new Thread(runnable);
        t3.setPriority(6);
        t3.start();

        Thread t4 = new Thread(runnable);
        t4.setPriority(4);
        t4.start();

        Thread t5 = new Thread(runnable);
        t5.setPriority(2);
        t5.start();
    }
}

class MyPriority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
    }
}


