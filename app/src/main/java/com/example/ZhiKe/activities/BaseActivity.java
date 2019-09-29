package com.example.ZhiKe.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;

    import com.example.ZhiKe.R;

public class BaseActivity extends Activity {
    private ImageView mIvBack,mIvme;
    private TextView mTvTitle;

    /**
     * findViewById
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T fd (@IdRes int id){
        return findViewById(id);
    }

    /**
     * 初始化NavgationBar
     * @param isShowBack
     * @param title
     * @param isShowMe
     */
    protected  void initNavBar(boolean isShowBack,String title,boolean isShowMe){

        mIvBack=fd(R.id.iv_back);
        mTvTitle=fd(R.id.tv_title);
        mIvme=fd(R.id.iv_me);

        mIvBack.setVisibility(isShowBack ? View.VISIBLE:View.GONE);
        mIvme.setVisibility(isShowMe ? View.VISIBLE:View.GONE);
        mTvTitle.setText(title);

        mIvBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
       // mIvBack.setOnClickListener((v){onBackPressed();});

        mIvme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(BaseActivity.this,MeActivity.class));
            }
        });
    }
}
