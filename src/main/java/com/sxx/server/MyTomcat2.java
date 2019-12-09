package com.sxx.server;

import com.sxx.config.ServletConfig;
import com.sxx.config.ServletConfigMapping;
import com.sxx.servlet.Request;
import com.sxx.servlet.Response;
import com.sxx.servlet.Servlet;
import com.sxx.util.HttpUtil;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * author sxx
 * Date 2019-12-09
 * desc WEB应用服务器 ，技术：网络通讯协议，IO，网络编程
 * 1.提供服务（ServerSocket）
 * 2.解析http协议 封装Request和Response对象（InputStream、OutputStream）
 * 3.实现servlet
 */
public class MyTomcat2 {
    private int port = 8088;
    /**
     * k-urlMapping
     * v-class类
     */
    private Map<String,Class<Servlet>> stringClassMap = new HashMap<String,Class<Servlet>>();

    public MyTomcat2(){

    }
    public MyTomcat2(int port){
        this.port = port;
    }

    /**
     * 初始化servlet
     */
    public void initServlet(){
        try {
            for (ServletConfig servletConfig : ServletConfigMapping.getConfigs()) {
                //反射获取class文件
                stringClassMap.put(servletConfig.getUrlMapping(), (Class<Servlet>) Class.forName(servletConfig.getClazz()));
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * desc 请求分发
     * @param request
     * @param response
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void dispatch(Request request,Response response) throws IllegalAccessException, InstantiationException {
        Class<Servlet> servletClass = stringClassMap.get(request.getUrl());
        if (servletClass !=null) {
            Servlet servlet = servletClass.newInstance();
            servlet.service(request,response);
        }else{
            response.write(HttpUtil.getHttpResponseContext(404,"",""));
        }

    }

    public void start() throws Exception{
        initServlet();
        //发布一个服务
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("my tomcat start on "+port);
        //阻塞式等待
        while (true){
            Socket accept = serverSocket.accept();
            Request request = new Request(accept.getInputStream());
            Response response = new Response(accept.getOutputStream());
            if (request.getUrl().equals("/")) {
                response.write(HttpUtil.getHttpResponseContext(404,"",""));
            }else if(stringClassMap == null || (stringClassMap.get(request.getUrl()) == null)){
                //请求的是静态页面
                response.writeHtml(request.getUrl());
            }else{
                //请求的是servlet动态页面
                dispatch(request,response);
            }
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
        MyTomcat2 myTomcat2 = new MyTomcat2();
        myTomcat2.start();
    }
}
