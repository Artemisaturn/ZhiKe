package com.example.ZhiKe.activities;

import android.os.Bundle;

import com.example.ZhiKe.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }
    private void initView(){
        initNavBar(true,"注册",false);
    }
}
