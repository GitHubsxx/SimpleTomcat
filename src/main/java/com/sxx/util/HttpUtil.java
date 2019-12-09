package com.sxx.util;

/**
 * Http响应头的文本信息
 */
public class HttpUtil {
    public static String getHttpResponseContext(int code,String content,String errorMsg){
        if(200 == code){
            return "HTTP/1.1 200 OK \n"+
                    "Content-type: text/html\n"+
                    "\r\n"+ content;
        }else if(500 == code){
            return "HTTP/1.1 500 Interval Error="+errorMsg+" \n"+
                    "Content-type: text/html\n"+
                    "\r\n";
        }
        return "HTTP/1.1 404 NOT Found \n"+
                "Content-type: text/html\n"+
                "\r\n"+
                "<h1>404 not found</h1>";
    }
}
