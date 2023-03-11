package org.example.demo3;

/**
 * 守护线程
 * 要点：
 * 1、线程分为用户线程和守护线程
 * 2、虚拟机必须确保用户线程执行完毕（类似main线程）
 * 3、虚拟机不必等待守护线程执行完毕（类似gc线程）
 * 4、守护线程主要后台记录操作日志，监控内存、垃圾回收等工作
 */
public class TestDaemon {
    public static void main(String[] args) {
        // god
        God god = new God();
        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();

        // me
        My my = new My();
        new Thread(my).start();
    }
}

class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you");
        }
    }
}


class My implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 3650; i++) {
            System.out.println("day -->" + i);
        }
        System.out.println("=====goodbye! World!=====");
    }
}
