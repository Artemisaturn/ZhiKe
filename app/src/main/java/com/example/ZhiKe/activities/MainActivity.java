package com.example.ZhiKe.activities;

import android.os.Bundle;


import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.adapters.PositionGridAdapter;
import com.example.ZhiKe.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvGrid;
    private PositionGridAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private  void initView (){

        initNavBar(false,"知课",true);
        mRvGrid=fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this,2));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.buildingMarginSize),mRvGrid));
        mAdapter=new PositionGridAdapter(this);
        mRvGrid.setAdapter(mAdapter);
        initDrawer();
    }

}
