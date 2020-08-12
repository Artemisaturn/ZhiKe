package com.example.ZhiKe.helps;

import com.blankj.utilcode.util.StringUtils;

/**
 * 用户自动登录
 * 1 用户登录
 *   1、当用户登录时，利用SharedPreferences 保存登录用户的用户标记（手机号码）
 *   2、利用全局单例类UserHelper保存登录用户信息
 *      1、用户登录之后保存
 *      2、用户重新打开应用程序，检测SharedPreferences是否存在登录用户标记，若存在则为UserHelp赋值，并且进入主页，若不存在则进入登录页面
 *
 * 2 用户退出
 *    1、删除SharedPreferences保存的用户标记，退出登录页面。
 */

//将UserHelper指定为全局单例模式
public class UserHelper {

    private static UserHelper instance;

    private UserHelper(){};

    public static UserHelper getInstance(){
        if(instance==null){
            synchronized (UserHelper.class){
                if(instance==null){
                    instance=new UserHelper();
                }
            }
        }
        return instance;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
