package org.example.senior;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 好处：
 * 1、提高响应速度，节省创建线程时间
 * 2、降低资源消耗（重复利用）
 * 3、便于管理（核心线程数、最大线程数等）
 *
 * Java API ExecutorService、Executors
 * ExecutorService.submit(Callable<T> task)
 * ExecutorService.execute(Runnable command)
 */
public class TestPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(new ReadThreadName());
        service.execute(new ReadThreadName());
        service.execute(new ReadThreadName());
        service.execute(new ReadThreadName());
        service.execute(new ReadThreadName());
        service.execute(new ReadThreadName());
        service.execute(new ReadThreadName());
        service.shutdown();
    }
}

class ReadThreadName implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
