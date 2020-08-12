package com.example.ZhiKe.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.adapters.TimeListAdapter;

public class SelectTimeActivity extends BaseActivity {

    private RecyclerView timeList;
    private TimeListAdapter timeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        Intent intent=getIntent();

        String position=intent.getStringExtra("position");
        initView(position);
    }

    private void initView(String position) {
        initNavBar(true, "知课", true);

        timeList = fd(R.id.time_list);
        timeList.setLayoutManager(new LinearLayoutManager(this));
        timeList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        timeList.setNestedScrollingEnabled(false);
        timeListAdapter = new TimeListAdapter(this,timeList,position);
        timeList.setAdapter(timeListAdapter);
    }
}
