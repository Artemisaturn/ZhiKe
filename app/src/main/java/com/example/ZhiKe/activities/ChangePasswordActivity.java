package com.example.ZhiKe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ZhiKe.R;
import com.example.ZhiKe.helps.UserHelper;
import com.example.ZhiKe.message.L;
import com.example.ZhiKe.utils.HttpUtil;
import com.example.ZhiKe.utils.UserUtils;
import com.example.ZhiKe.views.inputView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChangePasswordActivity extends BaseActivity {

    private inputView mOldPassword,mPassword,mPasswordConfirm;
    //private boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();

    }

    private void initView(){
        initNavBar(true,"修改密码",false);

        mOldPassword=fd(R.id.input_old_password);
        mPassword=fd(R.id.input_password);
        mPasswordConfirm=fd(R.id.input_password_confirm);
    }
    /**
     * 跳转到LoginActivity
     */
    private void toLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 修改密码的点击事件
     * @param v
     */
    public void onChangePasswordClick(View v){
        String oldPassword = mOldPassword.getInputStr();
        String password = mPassword.getInputStr();
        String passwordConfirm = mPasswordConfirm.getInputStr();
        String phone= UserHelper.getInstance().getPhone();

        boolean rs=UserUtils.changePassword(this,phone,oldPassword,password,passwordConfirm);

        if(rs){
            String address="http://27090p86e3.qicp.vip:27797/ZhiKe/changePassword.do";//"http://10.16.149.3:9527/ZhiKe/changePassword.do";//"; //服务器用:http://117.136.38.153:9527/ZhiKe/changePassword.do
            HttpUtil.changePasswordWithOkHttp(address, phone, oldPassword, passwordConfirm, new Callback() {
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
                                Toast.makeText(ChangePasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();

                                toLogin();
                            }
                            else{
                                Toast.makeText(ChangePasswordActivity.this,"原密码不正确",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            });


        }


    }
}
