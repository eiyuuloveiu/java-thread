package org.example.sync;

/**
 * 模拟不安全的买票
 * 解决方案：在buy方法上加上synchronized关键字
 */
public class UnsafeTicket {
    public static void main(String[] args) {
        TicketRunnable runnable = new TicketRunnable();
        new Thread(runnable, "a").start();
        new Thread(runnable, "b").start();
        new Thread(runnable, "c").start();
    }
}

class TicketRunnable implements Runnable {
    private int num  = 100;
    // 使用标志位来停止线程
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 这里抽离出来的好处是可以将synchronized加在buy方法上，而不是run上
    // synchronized可以实现排队+锁的效果
    // public synchronized void buy() throws InterruptedException {
    public void buy() throws InterruptedException {
        if (num <= 0) {
            flag = false;
            return;
        }

        // 放大问题
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "买到了第" + num-- + "票");
    }
}
