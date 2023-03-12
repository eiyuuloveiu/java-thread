package org.example.sync;

/**
 * 死锁：彼此持有对方想要的资源
 * 例子：两个女生化妆，都需要口红和镜子，如果A有口红，想要镜子了；而B有镜子，想要口红；如果两个人都不妥协，就会出现僵持的情况
 * 产生死锁的条件：
 * 1、互斥条件
 * 2、请求与保持条件
 * 3、不剥夺条件
 * 4、循环等待条件
 */
public class TestDeadLock {
    public static void main(String[] args) {
        LipStick lipStick = new LipStick();
        Mirror mirror = new Mirror();
        new Thread(new MakeUp(lipStick, mirror, true), "A").start();
        new Thread(new MakeUp(lipStick, mirror, false), "B").start();
    }
}

class LipStick {

}

class Mirror {

}

class MakeUp implements Runnable {
    private LipStick lipStick;
    private Mirror mirror;
    private boolean flag;

    public MakeUp(LipStick lipStick, Mirror mirror, boolean flag) {
        this.lipStick = lipStick;
        this.mirror = mirror;
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (lipStick) {
                System.out.println(Thread.currentThread().getName() + "--> 拿到了口红");
                synchronized (mirror) {
                    System.out.println(Thread.currentThread().getName() + "--> 拿到了镜子");
                }
            }
//            synchronized (mirror) {
//                System.out.println(Thread.currentThread().getName() + "--> 拿到了镜子");
//            }
        } else {
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "--> 拿到了镜子");
                synchronized (lipStick) {
                    System.out.println(Thread.currentThread().getName() + "--> 拿到了口红");
                }
            }
//            synchronized (lipStick) {
//                System.out.println(Thread.currentThread().getName() + "--> 拿到了口红");
//            }
        }
    }
}

