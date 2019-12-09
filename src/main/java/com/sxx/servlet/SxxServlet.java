package com.sxx.servlet;

import com.sxx.util.HttpUtil;

public class SxxServlet extends Servlet {
    @Override
    public void doGet(Request request, Response response) {
        String content = "<h1>this is sxx request Get</h1>";
        response.write(HttpUtil.getHttpResponseContext(200,content,""));
    }

    @Override
    public void doPost(Request request, Response response) {
        String content = "<h1>this is sxx request Post</h1>";
        response.write(HttpUtil.getHttpResponseContext(200,content,""));
    }
}
