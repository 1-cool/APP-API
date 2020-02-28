package com.example.dianming;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Xq {
    public String result;

    public void requestData(){
        HttpURLConnection connection=null;
        InputStream stream=null;

        try {
            URL url=new URL("https://test.cookxk.com");
            connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();
            Log.d("ooo", "requestData: 链接成功");

            int code= connection.getResponseCode();
            if(code!=HttpURLConnection.HTTP_OK){
                throw  new IOException("异常"+code);
            }
            result=wryyyyyyy(connection.getInputStream());
            if(result==null){
                throw new IOException("失败");}

            Log.d("TEXTT", "requestData: "+result);




        } catch (Exception e) {
            e.printStackTrace();
            Log.d("buggg",Log.getStackTraceString(e) );

        }
    }



    public String wryyyyyyy(InputStream inputStream) {
        try {
            Reader reader=new InputStreamReader(inputStream,"UTF-8");
            char[] rawBuffer=new char[999999];
            StringBuffer buffer=new StringBuffer();
            int lon;
            while((lon=reader.read(rawBuffer))!=-1){
                buffer.append(rawBuffer,0,lon);
            }
            return  buffer.toString();




        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
