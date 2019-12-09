package com.sxx.servlet;

import com.sxx.util.FileUtil;
import com.sxx.util.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private OutputStream outputStream;

    public Response(OutputStream outputStream){

        this.outputStream = outputStream;
    }
    public void write(String content){
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 找到资源并响应出去
     * @param path
     */
    public void writeHtml(String path){
        String resourcePath = FileUtil.getResourcePath(path);
        File file = new File(resourcePath);
        if (file.exists()) {
            try {
                FileUtil.writeFile(file,outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            write(HttpUtil.getHttpResponseContext(404,"",""));
        }
    }
}
