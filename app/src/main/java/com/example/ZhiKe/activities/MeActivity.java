package com.example.ZhiKe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.helps.UserHelper;
import com.example.ZhiKe.utils.UserUtils;

import java.util.List;

public class MeActivity extends BaseActivity {

    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView(){
        initNavBar(true,"个人中心",false);

        mTvUser=fd(R.id.tv_user);
        mTvUser.setText("用户名："+ UserHelper.getInstance().getPhone());

    }

    /**
     * 修改密码点击事件
     */
    public void onChangeClick(View v){
        startActivity(new Intent(this,ChangePasswordActivity.class));

    }
    /**
     * 查看课表点击事件
     */
    public void onCheckClick(View v){
        startActivity(new Intent(this, ListCourseActivity.class));
    }

    /**
     * 退出登录点击事件
     */

    public void onLogoutClick (View v){

        UserUtils.logout(this);
    }
}
