package com.sxx.util;

import java.io.*;

/**
 * Author sxx
 * 文件写入流
 */
public class FileUtil {

    public static boolean writeFile(InputStream inputStream, OutputStream outputStream){
        boolean success = false;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(HttpUtil.getHttpResponseContext(200,"","").getBytes());
            int count = 0 ;
            //避免空请求报错
            while (count == 0){
                count = inputStream.available();
            }
            int fileSize = inputStream.available();//该方法可以预估一次请求的大小
            long written = 0;
            int byteSize = 1024;
            byte[] bytes = new byte[byteSize];
            while (written < fileSize){
                if(written+byteSize > fileSize){
                   byteSize = (int) (fileSize - written);
                   bytes = new byte[byteSize];
                }
                bufferedInputStream.read(bytes);
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                written += byteSize;
            }
            success = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }
    public static boolean writeFile(File file,OutputStream outputStream) throws IOException{
        return writeFile(new FileInputStream(file),outputStream);

    }
    public static String getResourcePath(String path){
        String resource = FileUtil.class.getResource("/").getPath();
        return resource+"\\"+path;
    }
}
