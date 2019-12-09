package com.sxx.server;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * simple Tomcat demo
 */
public class MyTomcat {
    private int port = 8080;

    public MyTomcat(){

    }
    public MyTomcat(int port){
        this.port = port;
    }

    public void start() throws Exception{
        //发布一个服务
        ServerSocket serverSocket = new ServerSocket(port);
        //阻塞式等待
        while (true){
            Socket accept = serverSocket.accept();
            //得http请求的内容格式
            InputStream inputStream = accept.getInputStream();
            int count = 0;
            //避免空请求报错
            while (count == 0){
                count = inputStream.available();//该方法可以预估一次请求的大小
            }
            byte[] bytes = new byte[inputStream.available()];
            //向浏览器响应
            accept.getOutputStream().write("hello word!".getBytes());
            accept.close();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public static void main(String[] args) throws Exception {
        MyTomcat myTomcat = new MyTomcat();
        myTomcat.start();
    }
}
