package org.example.senior;

/**
 * 生产者消费者问题 -- 信号灯法
 *
 * 要点：
 * 1、this.notifyAll(); 需要在更改flag标志为之前
 * 2、注意Director中等待判断中需要是!flag，确保导演先生产
 * 3、这个可以看做缓存区是1的管程法
 */
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Thread(new Director(tv)).start();
        new Thread(new Watcher(tv)).start();
    }
}

// 导演生产者
class Director implements Runnable {
    private TV tv;

    public Director(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            tv.perform(String.valueOf(i));
        }
    }
}

// 观众消费者
class Watcher implements Runnable {
    private TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            tv.watch();
        }
    }
}

// 节目
class TV {
    // 节目名称
    private String name;

    private boolean flag = true;

    // 表演
    public synchronized void perform(String name) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("导演制作了片子：" + name);
        // 注意这个方式是错误的
//        System.out.println("导演制作了片子：" + this.name);
        this.notifyAll();
        this.name = name;
        this.flag = !this.flag;
    }

    // 观看
    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了节目：" + this.name);
        this.notifyAll();
        this.flag = !this.flag;
    }
}
