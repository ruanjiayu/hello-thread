package com.fun.uncle.hello.thread.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Description: 练习Thread,实现多线程同步下载
 * @Author: Xian
 * @CreateDate: 2020/2/15  21:18
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestThread02 extends Thread{
    class A{

    }

    private String url;

    private String name;

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("【下载文件完成，文件名为】" + name);
    }


    public TestThread02(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
      TestThread02 t01 =  new TestThread02("http://ketech-dmn.oss-cn-hangzhou.aliyuncs.com/domino/avatar/2019/10/11/d00043bacf2356baa1e6fc393046ad5f.png?Expires=2201928794&OSSAccessKeyId=LTAIkflYi6vT01yg&Signature=MI0N5C8nQ9jT1Nz0tKGQ54KCPEs%3D", "1.jpg");
      TestThread02 t02 =  new TestThread02("http://ketech-dmn.oss-cn-hangzhou.aliyuncs.com/domino/avatar/2019/10/11/d00043bacf2356baa1e6fc393046ad5f.png?Expires=2201928794&OSSAccessKeyId=LTAIkflYi6vT01yg&Signature=MI0N5C8nQ9jT1Nz0tKGQ54KCPEs%3D", "2.jpg");
      TestThread02 t03 =  new TestThread02("http://ketech-dmn.oss-cn-hangzhou.aliyuncs.com/domino/avatar/2019/10/11/d00043bacf2356baa1e6fc393046ad5f.png?Expires=2201928794&OSSAccessKeyId=LTAIkflYi6vT01yg&Signature=MI0N5C8nQ9jT1Nz0tKGQ54KCPEs%3D", "3.jpg");

      t01.start();
      t02.start();
      t03.start();
    }
}

/**
 * 下载器
 */
class WebDownloader{
    /**
     * 下载图片
     * @param url
     * @param name
     */
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}