package com.example.ZhiKe.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

//AppCompatImageView
public class WEqualsHImageview extends AppCompatImageView {
    public WEqualsHImageview(Context context) {
        super(context);
    }

    public WEqualsHImageview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
//        //获取 view宽度
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        //获取view模式 match_parent、wrap_content、具体dp
//        int mode=MeasureSpec.getMode(widthMeasureSpec);
    }
}
