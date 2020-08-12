package com.example.ZhiKe.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;

import com.example.ZhiKe.activities.LoginActivity;
import com.example.ZhiKe.R;


public class UserUtils {
    public static String address="http://106.54.216.80:9527/ZhiKe/";

    /**
     * 验证登录用户输入合法性
     */
    public static boolean validateLogin(Context context,String phone,String password){

        //RegexUtils.isMobileSimple(phone);//简单的
       // RegexUtils.isMobileExact(phone);//精确的
        if(!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 退出登录
     */

    public static void logout(Context context){

        //删除sp中保存的用户标记
        boolean isRemove=SPUtils.removeUser(context);

        if(!isRemove){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent =new Intent(context, LoginActivity.class);
        //添加intent标识符：清理task栈并新生成一个TASK栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
        //定义activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    public static boolean registerUser(Context context,String collegeName,String studentId,String phone,String password,String passwordConfirm){
        if(StringUtils.isEmpty((collegeName))){
            Toast.makeText(context,"请输入学校名称",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isEmpty((studentId))){
            Toast.makeText(context,"请输入学号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        //用户当前输入的手机号是否被注册

        return true;
    }
    /**
     * 验证是否存在已登录用户
     */
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }
    /**
     * 修改密码
     * 1、数据验证
     *      1、原密码是否输入
     *      2、新密码是否输入并且新密码与确定密码是否相同
     *      3、原密码是否正确
     * 2、修改密码
     */
    public static boolean changePassword(Context context,String phone,String oldPassword,String password,String passwordConfirm) {

        if (TextUtils.isEmpty(oldPassword)) {
            Toast.makeText(context, "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password) || !password.equals(passwordConfirm) || TextUtils.isEmpty(passwordConfirm)) {
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

