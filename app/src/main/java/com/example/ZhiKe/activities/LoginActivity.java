package com.example.ZhiKe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ZhiKe.R;
import com.example.ZhiKe.helps.UserHelper;
import com.example.ZhiKe.message.L;
import com.example.ZhiKe.utils.HttpUtil;
import com.example.ZhiKe.utils.SPUtils;
import com.example.ZhiKe.utils.UserUtils;
import com.example.ZhiKe.views.inputView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//NavigationBar
public class LoginActivity extends BaseActivity {

    private inputView mInputPhone,mInputPassword;

    private String mUrl="http://27090p86e3.qicp.vip:27797/ZhiKe/login.do";//"http://10.16.149.3:9527/ZhiKe/login.do";//10.16.149.3  服务器用:http://106.54.216.80:9527/ZhiKe/login.do
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView(){

        initNavBar(false,"登录",false);

        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
    }

    /**
     * 注册页面点击事件
     */

    public void onRegisterClick(View v){

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 登录的点击事件
     */
    public void onCommitClick (View v){

        String loginAddress=mUrl;
        String phone = mInputPhone.getInputStr();
        String password =mInputPassword.getInputStr();
        //验证用户输入是否合法
        if(!UserUtils.validateLogin(this,phone,password)){

            return;//跳转到方法
        }
        //进行登录操作
        //gotoMain();
        //gotoClassroomStatus();
        login(loginAddress,phone,password);

//         跳转到应用主页




    }

    public void gotoClassroomStatus(){
        Intent intent = new Intent(this,ClassroomStatusActivity.class);
        startActivity(intent);
        finish();
    }
    public void gotoMain()
    {
        //保存用户登录标记
        boolean isSave=SPUtils.saveUser(LoginActivity.this,mInputPhone.getInputStr());
        if(!isSave){
            Toast.makeText(LoginActivity.this,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return;
        }
//      保存用户标记，在全局单例类中
        UserHelper.getInstance().setPhone(mInputPhone.getInputStr());

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void login(String address,String phone,String password){

        HttpUtil.loginWithOkHttp(address, phone, password, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                L.e("onFailure："+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String responseData = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(responseData.equals("true")){
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                            gotoMain();
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


}
