package org.example.demo2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * 创建线程方式三 -- 实现Callable
 * 步骤：
 * 1、实现Callable接口，需要指定返回值
 * 2、重写call方法，需要抛出异常
 * 3、创建目标对象
 * 4、创建执行服务：ExecutorService ser  = Executor.newFixedThreadPool(1);
 * 5、提交执行：Future<Boolean> res = ser.submit(t1);
 * 6、获取结果。boolean rs1 = res.get();
 * 7、关闭服务:ser.shutdownNow();
 */
public class TestCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloader downloader = new WebDownloader();
        downloader.download(url, name);
        System.out.println("file has already downloaded...:" + name);
        return true;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建对象
        TestCallable t1 = new TestCallable("https://img1.jiemian.com/101/original/20190410/155489501048199400_a700x398.jpeg", "4.jpeg");
        TestCallable t2 = new TestCallable("https://img.soundofhope.org/2020-02/chiang_kai-shek_in_full_uniform.jpeg", "5.jpeg");
        TestCallable t3 = new TestCallable("https://p4.itc.cn/images01/20230123/8a2e7c8cfaf541f694dda019cf553ccb.jpeg", "6.jpeg");

        // 创建线程池服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 提交服务
        Future<Boolean> r1 = service.submit(t1);
        Future<Boolean> r2 = service.submit(t2);
        Future<Boolean> r3 = service.submit(t3);
        //获取结果
        Boolean rs1 = r1.get();
        Boolean rs2 = r2.get();
        Boolean rs3 = r3.get();
        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);
        // 关闭服务
        service.shutdownNow();
    }
}


/**
 * 下载器
 */
class WebDownloader {
    /**
     * 下载到文件
     * @param url 路径
     * @param name 文件名
     */
    public void download(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

