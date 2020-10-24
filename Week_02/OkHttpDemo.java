package com.geek.jvm.demo01.httpDemo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpDemo {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        Request.Builder requestBuilder = new Request.Builder();
        //配置url
        requestBuilder.url("http://localhost:8801");
        //配置请求方式
        requestBuilder.get();
        try {
            //发送网络请求
            Response response = client.newCall(requestBuilder.build()).execute();
            String result = response.body().string();
            System.out.println("响应时间：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
