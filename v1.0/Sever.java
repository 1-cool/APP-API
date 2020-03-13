package com.example.dianming;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
*********************************Notice********************************
*
*     You need to create a new Sever class and call the method add() to get
* the data from the Our sever When the "MainActivity" First start.
*   add():
*           1⃣：method will send a request to get a json type data from our sever
*           and analyze the json data and push to the static list;
*           2⃣  You can also call this method to refresh local data;
*          Notice: When return to the UI thread, the method did't get data yet.
*                  So,you can't use data right after call add() finish
*                  Suggest to judge the data after you call the add() method
*                  each time. If get data fail the list.size()'s value is 0;
*
*   public void change(String num,String modify,String modifydata):
*          Function: To send changed data to the sever and refresh local data
*          1⃣：You need to income 3 parameters:num、modify and modifydata
*           num:        StudentID of student who you want to change.
*           modify:     Item that you want to change.
*           modifydata: Item's data you want to change
*
*   getlist():
*           This method will return list;
* Other:
*   public void any(String jsonData):
*           This method will analyze jsonData and push it to the list;
*   public void getstr():
*           This method will return the raw json string;
*
*************************************************************************
*/
public class Sever {
    public static List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
    Map<String,Object> map=new HashMap<String, Object>();
    static String resd;
    static String flag;

    //Get number list from Sever  and flash data
    public void add() {
        list.clear();
        fun(new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                resd=response.body().string();
                Log.d("Sever.add()", "onResponse: "+resd);
                any(resd);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Sever.add()", "onNOResp: "+resd);
            }
        });
    }
        public static void fun(okhttp3.Callback callback) {
        Log.d("Sever.add()", "Start to get msg");
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("password","as?df?!kj!?129!??!0340aflasf")
                .build();
        Request request = new Request.Builder()
                .url("https://test.cookxk.com/")
                .post(requestBody)
                .build();
           //Post a request to get all the list
        Log.d("Sever.add()", "Recall back");
        client.newCall(request).enqueue(callback);
    }
            //Analyze the json data and push data to List with map type
            private void any(String jsonData) {
        list.clear();
        Log.d("Sever.any()", "run: "+jsonData);
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String num = jsonObject.getString("num");
                String work = jsonObject.getString("work");
                String phone = jsonObject.getString("phone");
                String teacher = jsonObject.getString("teacher");
                String qq = jsonObject.getString("qq");
                String sex = jsonObject.getString("sex");
                String cont = jsonObject.getString("cont");
                map=new HashMap<String, Object>();
                map.put("id",id);
                map.put("name",name);
                map.put("num",num);
                map.put("work",work);
                map.put("phone",phone);
                map.put("teacher",teacher);
                map.put("qq",qq);
                map.put("sex",sex);
                map.put("cont",cont);
                list.add(map);
                Log.d("fun", "List Size: "+list.size());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //return list
    public List<Map<String, Object>> getList() {
        return list;
    }

    //return src json data
    public String getstr() {
        Log.d("Sever.getstr()", "return: "+resd);
        return resd;
    }

    //change data and send it to sever
    public void change(String num,String modify,String modifydata) {
        chang(num,modify,modifydata,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                flag=response.body().string();
                add();
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Changer", "onFaile: "+resd);
            }
        });
    }
        public static void chang(String num,String modify,String modifydata,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("password","as?df?!kj!?129!??!0340aflasf")
                .add("number",num)
                .add("modify",modify)
                .add("modifydata",modifydata)
                .build();
        Request request = new Request.Builder()
                .url("https://test.cookxk.com/")
                .post(requestBody)
                .build();
        //Post a request to change the info
        client.newCall(request).enqueue(callback);
    }}