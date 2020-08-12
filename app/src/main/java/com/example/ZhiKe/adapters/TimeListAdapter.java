package com.example.ZhiKe.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ZhiKe.R;
import com.example.ZhiKe.activities.ChooseRoomActivity;
import com.example.ZhiKe.models.TimeItems;

import java.util.ArrayList;
import java.util.List;

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private List<TimeItems> items;
    private RecyclerView mRv;
    private boolean isCalculationRvHeight;
    public  String roomPosition;

    public TimeListAdapter(Context context,RecyclerView recyclerView,String Position){
        mContext = context;
        mRv = recyclerView;
        roomPosition=Position;

        items = new ArrayList<TimeItems>()
        {{
           add(new TimeItems("第一节课","8:00-9:35",R.mipmap.one));
           add(new TimeItems("第二节课","9:55-11:30",R.mipmap.two));
           add(new TimeItems("第三节课","13:30-15:05",R.mipmap.three));
           add(new TimeItems("第四节课","15:20-16:55",R.mipmap.four));
           add(new TimeItems("第五节课","17:10-18:45",R.mipmap.five));
           add(new TimeItems("第六节课","19:30-21:05",R.mipmap.six));
        }};
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_time,parent,false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        setRecyclerViewHeight();

        final TimeItems item = items.get(position);
        holder.number.setText(item.getNumber());
        holder.timeShow.setText(item.getTimeShow());
        holder.imageView.setImageResource(item.getTimePicture());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChooseRoomActivity.class);
                intent.putExtra("position",roomPosition);
                intent.putExtra("freeTime",item.getNumber());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

     private void setRecyclerViewHeight(){
        if(isCalculationRvHeight || mRv == null) return;
        isCalculationRvHeight = true;

        //获取itemView的高度
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        //获取itemView的数量
        int itemCount = getItemCount();

        int recyclerViewHeight = itemViewLp.height * itemCount;
        //设置RecyclerView高度
         LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
         rvLp.height = recyclerViewHeight;
         mRv.setLayoutParams(rvLp);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView number;
        TextView timeShow;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            number = itemView.findViewById(R.id.number);
            timeShow = itemView.findViewById(R.id.time_show);
            imageView = itemView.findViewById(R.id.time_picture);
        }
    }
}
