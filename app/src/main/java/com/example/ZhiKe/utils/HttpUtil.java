package com.example.ZhiKe.utils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    /**
     * 注册
     * @param address
     * @param collegeName
     * @param studentId
     * @param phone
     * @param password
     * @param callback
     */
   public static void registerWithOkHttp(String address,String collegeName,String studentId,String phone,String password,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("collegeName",collegeName)
                .add("studentId",studentId)
                .add("phone",phone)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void loginWithOkHttp(String address,String phone,String password,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("phone",phone)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 修改密码
     */
    public static void changePasswordWithOkHttp(String address,String phone,String oldPassword,String passwordConfirm,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("phone",phone)
                .add("oldPassword",oldPassword)
                .add("passwordConfirm",passwordConfirm)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 查看无课教室
     * @param address
     * @param dayOfWeek
     * @param freeTime
     * @param position
     * @param callback
     */
    public static void checkAvailableRoomWithOkHttp(String address,String dayOfWeek,String freeTime,String position ,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("dayOfWeek",dayOfWeek)
                .add("freeTime",freeTime)
                .add("position",position)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 查询当前教室人数
     * @param address
     * @param position
     * @param classroomname
     * @param recordtime
     * @param callback
     */
    public static void checkPeopleWithOkhttp(String address,String position,String classroomname,String recordtime,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("position",position)
                .add("classroomname",classroomname)
                .add("recordtime",recordtime)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);

    }

    /**
     * 请求预测结果
     * @param address
     * @param position int
     * @param classroomname int
     * @param dayofweek int 当前周几
     * @param callback
     */
    public static void predictPeopleWithOkhttp(String address,String position,String classroomname,String dayofweek,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("position",position)
                .add("classroomname",classroomname)
                .add("dayofweek",dayofweek)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 查看当日课表
     * @param address
     * @param dayofweek
     * @param callback
     */
    public  static void checkCourseToday(String address,String dayofweek,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("dayOfWeek",dayofweek)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
