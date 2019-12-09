package com.sxx.servlet;

/**
 * abstract servlet
 */
public abstract class Servlet {
    public abstract void doGet(Request request,Response response);
    public abstract void doPost(Request request,Response response);

    public void service(Request request,Response response){
        if ("GET".equals(request.getMethod())) {
            doGet(request,response);
        }else if("POST".equals(request.getMethod())){
            doPost(request,response);
        }
    }
}
