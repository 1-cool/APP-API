package com.example.dianming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class test extends AppCompatActivity  {
    private TextView textView;
    private String result;


    String nnnnn;
    private  Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String str=(String)msg.obj;
            textView.setText(str);


        }
    };

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final Xq xq=new Xq();
        textView=findViewById(R.id.text123);





            new Thread() {
                @Override
                public void run() {
                    requestData();

                    Message message=handler.obtainMessage();
                    message.obj=result;
                    handler.sendMessage(message);
                    Log.d("resultttt", "onCreate: "+message.obj.toString());

                }
            }.start();





    }




    private void requestData(){
        HttpURLConnection connection=null;
        InputStream stream=null;
        nnnnn="123123123123";
        try {
            URL url=new URL("https://www.baidu.com");
            //https://test.cookxk.com
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
                throw new IOException("失败");
            }

                    Log.d("TEXTT", "requestData: "+result);




        } catch (Exception e) {
            e.printStackTrace();
            Log.d("buggg",Log.getStackTraceString(e) );

        }
    }

    private String wryyyyyyy(InputStream inputStream) {
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
