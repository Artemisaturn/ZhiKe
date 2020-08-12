package com.example.ZhiKe.activities;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.example.ZhiKe.R;
import com.example.ZhiKe.message.L;
import com.example.ZhiKe.utils.HttpUtil;
import com.example.ZhiKe.utils.UserUtils;
import com.example.ZhiKe.views.inputView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.Response;

public class RegisterActivity extends BaseActivity {

    private inputView mInputPhone,mInputPassword,mInputPasswordConfirm,mCollegeName,mStudentId;

    private String mUrl="http://27090p86e3.qicp.vip:27797/ZhiKe/register.do";//"http://10.16.149.3:9527/ZhiKe/register.do";     //10.16.149.3  服务器用:http://106.54.216.80:9527/ZhiKe/register.do

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }
    private void initView(){

        initNavBar(true,"注册",false);


        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
        mInputPasswordConfirm=fd(R.id.input_password_confirm);
        mCollegeName=fd(R.id.collegeName);
        mStudentId=fd(R.id.studentId);


    }

    /**
     * 注册按钮点击事件
     * 1、用户输入合法性验证
     *      1、用户输入的手机号是不是合法的
     *      2、用户是否已经输入密码和确定密码，以及两次内容是不是一致的
     *      3、用户输入的手机号是否已经被注册
     * 2、保存用户输入的手机号和密码（md5加密）
     */

    public void onRegisterClick(View v){

        String registerAddress=mUrl;
        String phone=mInputPhone.getInputStr();
        String password=mInputPassword.getInputStr();
        String passwordConfirm=mInputPasswordConfirm.getInputStr();
        String collegeName=mCollegeName.getInputStr();
        String studentId=mStudentId.getInputStr();
        boolean result= UserUtils.registerUser(this,collegeName,studentId,phone,password,passwordConfirm);

        if(!result) return;
        //后退页面

        register(registerAddress,collegeName,studentId,phone,password);

    }

    /**
     * 注册用户
     * @param address
     * @param phone
     * @param password
     */
    public void register(String address,String collegeName,String studentId,String phone,String password){
        HttpUtil.registerWithOkHttp(address, collegeName,studentId,phone, password, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                L.e("onFailure："+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String responseData=response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(responseData.equals("true")){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"注册失败,此手机号已被注册",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
