package org.example.demo1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 使用Thread多线程下图片
 */
public class TestThread2 extends Thread {

    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader downloader = new WebDownloader();
        downloader.download(url, name);
        System.out.println("file has already downloaded...:" + name);
    }


    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://img1.jiemian.com/101/original/20190410/155489501048199400_a700x398.jpeg", "1.jpeg");
        TestThread2 t2 = new TestThread2("https://img.soundofhope.org/2020-02/chiang_kai-shek_in_full_uniform.jpeg", "2.jpeg");
        TestThread2 t3 = new TestThread2("https://p4.itc.cn/images01/20230123/8a2e7c8cfaf541f694dda019cf553ccb.jpeg", "3.jpeg");

        t1.start();
        t2.start();
        t3.start();
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
