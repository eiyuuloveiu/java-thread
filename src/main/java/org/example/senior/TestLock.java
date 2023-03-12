package org.example.senior;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock与synchronized关键字的比较
 * 1、Lock是显性锁，synchronized是隐性锁
 * 2、Lock只有代码块锁，synchronized有代码块和方法锁
 * 3、Lock性能好，有更好的扩展性
 */
public class TestLock {

    public static void main(String[] args) {
        BuyTicket ticket = new BuyTicket();
        new Thread(ticket, "A").start();
        new Thread(ticket, "B").start();
        new Thread(ticket, "C").start();
    }
}

class BuyTicket implements Runnable {

    private int nums = 100;
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (nums <= 0) {
                    System.out.println("票已经卖完了");
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "-->买到了第" + nums-- + "张票");
            } finally {
                lock.unlock();
            }
        }

    }
}
