package com.example.ZhiKe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

    import com.example.ZhiKe.R;
import com.example.ZhiKe.views.inputView;

//NavigationBar
public class LoginActivity extends BaseActivity {

    private inputView mInputPhone,mInputPassword;

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

        String phone = mInputPhone.getInputStr();
        String password =mInputPassword.getInputStr();
        //验证用户输入是否合法
//        if(!UserUtils.validateLogin(this,phone,password)){
//
//            return;//跳转到方法
//        }
//         跳转到应用主页
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
