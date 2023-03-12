package org.example.senior;

/**
 * <h1>线程协作</h1>
 * 如何让线程之间产生协作（之前有的例子讲到了可以设置标志位），就产生了生产者消费者问题<br/><br/>
 * 应用场景：假设仓里面有且只有一件产品，生产者将生产的产品放入仓库，消费者将产品从仓库消费掉；
 *          如果仓库没有产品，生产者生产，否则停止生产并等待，直到仓库中产品被消费者取走为止；
 *          如果仓库有产品，消费者消费，否则停止消费并等待，直到仓库中再次放入产品为止
 *
 * 分析：这是一个线程同步的问题，因为操作同一个资源，并且生产者与消费者之间相互依赖，互为条件
 *      对于生产者，在没有生产产品之前，要通知消费者等待，而生产了产品之后，要马上通知消费者消费
 *      对于消费者，在消费之后，要通知生产者已经结束消费，需要提供新的产品以供消费
 *      在生产者和消费者的问题中，仅有synchronized是不够的，synchronized只能保证并发同步，不能保证线程间的通信
 *
 * Java提供了方法实现线程间的通信。Object类中的wait、notify、notifyAll。这些方法只能在同步方法或同步代码块中使用，否则会抛出IllegalMonitorStateException
 *
 * 解决方法：
 * 1、管程法。就是一个缓存区
 * 2、信号灯法。设置一个标志位
 *
 *
 *
 * 生产者消费者模型---管程法
 *
 * 重点：两个线程对应两个Runnable对象（生产者，消费者） 两者的任务有所不同（push、pop）。所以在共享资源类（ChickenContainer）中对应着两个不同方法供使用
 */
public class TestPC1 {
    public static void main(String[] args) {
        ChickenContainer container = new ChickenContainer();
        new Thread(new Producer(container)).start();
        new Thread(new Consumer(container)).start();
    }

}

class Producer implements Runnable {

    private ChickenContainer container;

    public Producer(ChickenContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了第" + i + "只鸡");
            container.push(new Chicken(i));
        }
    }
}

class Consumer implements Runnable {
    private ChickenContainer container;

    public Consumer(ChickenContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Chicken chicken = container.pop();
            System.out.println("消费了第" + chicken.getId() + "只鸡");
        }
    }
}


class Chicken {
    private int id;

    public Chicken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class ChickenContainer {
    private Chicken[] chickens = new Chicken[10];
    private int count;

    /**
     * 生产者生产，生产者线程调用，所以这里的this表示生产者
     * @param chicken
     */
    public synchronized void push(Chicken chicken) {
        // 当容器满了，生产者等待
        if (count == chickens.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果没有满，就丢入产品
        chickens[count++] = chicken;

        // 通知消费者消费
        this.notifyAll();
    }

    /**
     * 消费者消费，消费者线程调用，所以这里的this表示消费者
     * @return
     */
    public synchronized Chicken pop() {
        // 如果容器为空，消费者等待，通知生产者生产
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 消费产品
        Chicken chicken = chickens[--count];

        // 吃完了，通知生产者生产
        this.notifyAll();
        return chicken;
    }
}