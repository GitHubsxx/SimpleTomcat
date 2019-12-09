package com.sxx.servlet;

import java.io.InputStream;

public class Request {
    private String url;//请求的地址
    private String method;//Get、Post请求
    private InputStream inputStream;

    public Request(InputStream inputStream) throws Exception{
        this.inputStream = inputStream;
        int count = 0;
        while (count == 0){
            count = inputStream.available();
        }
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        extractFileds(new String(bytes));
    }

    /**
     * 封装请求头的信息
     * @param content
     */
    private void extractFileds(String content){
        if ("".equals(content)) {
            System.out.println("empty");
        }else {
            String firstLine = content.split("\\n")[0];
            String[] split = firstLine.split("\\s");
            setMethod(split[0]);
            setUrl(split[1]);
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
