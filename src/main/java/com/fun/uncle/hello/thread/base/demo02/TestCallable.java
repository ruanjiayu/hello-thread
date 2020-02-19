package com.fun.uncle.hello.thread.base.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @Description: 案例三。实现callable接口。好处：1.可以定义返回值，2.可以抛出异常
 * @Author: Xian
 * @CreateDate: 2020/2/15  22:22
 * @Version: 0.0.1-SNAPSHOT
 */
public class TestCallable implements Callable<String> {
    private String url;

    private String name;

    @Override
    public String call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("【下载文件完成，文件名为】" + name);
        return "success";
    }


    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
        // 创建目标对象
        TestCallable t01 =  new TestCallable("http://ketech-dmn.oss-cn-hangzhou.aliyuncs.com/domino/avatar/2019/10/11/d00043bacf2356baa1e6fc393046ad5f.png?Expires=2201928794&OSSAccessKeyId=LTAIkflYi6vT01yg&Signature=MI0N5C8nQ9jT1Nz0tKGQ54KCPEs%3D", "1.jpg");
        TestCallable t02 =  new TestCallable("http://ketech-dmn.oss-cn-hangzhou.aliyuncs.com/domino/avatar/2019/10/11/d00043bacf2356baa1e6fc393046ad5f.png?Expires=2201928794&OSSAccessKeyId=LTAIkflYi6vT01yg&Signature=MI0N5C8nQ9jT1Nz0tKGQ54KCPEs%3D", "2.jpg");
        TestCallable t03 =  new TestCallable("http://ketech-dmn.oss-cn-hangzhou.aliyuncs.com/domino/avatar/2019/10/11/d00043bacf2356baa1e6fc393046ad5f.png?Expires=2201928794&OSSAccessKeyId=LTAIkflYi6vT01yg&Signature=MI0N5C8nQ9jT1Nz0tKGQ54KCPEs%3D", "3.jpg");
        // 创建执行服务
        ExecutorService es = Executors.newFixedThreadPool(3);
        /*提交执行*/
        Future<String> r1 = es.submit(t01);
        Future<String> r2 = es.submit(t02);
        Future<String> r3 = es.submit(t03);
        try {
            /*获取结果*/
            System.out.println(r1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            /*关闭服务*/
            es.shutdownNow();
        }
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