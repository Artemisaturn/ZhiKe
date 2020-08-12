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
import com.example.ZhiKe.activities.ClassroomStatusActivity;
import com.example.ZhiKe.models.RoomItems;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {
    private Context mContext;
    private View mItemView;
    private List<RoomItems> items;
    private RecyclerView mRv;
    private boolean isCalculationRvHeight;
    public CourseListAdapter(Context context, RecyclerView recyclerView,List<RoomItems> rooms){
        mContext = context;
        mRv = recyclerView;
        items=rooms;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_room,parent,false);
        return new CourseListAdapter.ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListAdapter.ViewHolder holder, final int position) {
        setRecyclerViewHeight();

        final RoomItems item = items.get(position);
        holder.position.setText(item.getPosition());
        holder.roomShow.setText(item.getRoomName());
        holder.imageView.setImageResource(item.getRoomPicture());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ClassroomStatusActivity.class);
//                intent.putExtra("position",roomPosition);
//                intent.putExtra("classroomname",item.getRoomName());
//
//                mContext.startActivity(intent);
//            }
//        });
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
        TextView position;
        TextView roomShow;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            position = itemView.findViewById(R.id.position);
            roomShow = itemView.findViewById(R.id.room_name);
            imageView = itemView.findViewById(R.id.room_picture);
        }
    }
}

